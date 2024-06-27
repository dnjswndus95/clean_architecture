package io.hhplus.step2.application.service;

import io.hhplus.step2.application.model.UserLectureReservationInfo;
import io.hhplus.step2.domain.*;
import io.hhplus.step2.application.model.LectureInfoWithLectureName;
import io.hhplus.step2.application.repository.LectureHistoryRepository;
import io.hhplus.step2.application.repository.LectureInfoRepository;
import io.hhplus.step2.application.repository.LectureRepository;
import io.hhplus.step2.application.repository.LectureReservationRepository;
import io.hhplus.step2.controller.dto.lecture.ApplyLecturesDto;
import io.hhplus.step2.controller.dto.lecture.GetLectureListDto;
import io.hhplus.step2.controller.dto.lecture.GetUserLectureApplicationWhetherDto;
import io.hhplus.step2.application.enumerator.LectureHistoryTypeEnum;
import io.hhplus.step2.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureValidator lectureValidator;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final LectureHistoryRepository lectureHistoryRepository;
    private final LectureReservationRepository lectureReservationRepository;

    @Override
    public ApplyLecturesDto.Response applyLectures(ApplyLecturesDto.Request request) {
        // userId로 유저 조회
        User findUser = this.userRepository.findById(request.userId()).orElseThrow(
                () -> new IllegalArgumentException("user가 존재하지 않습니다.")
        );
        // lectureId로 강의 조회
        Lecture findLecture = this.lectureRepository.findById(request.lectureId()).orElseThrow(
                () -> new IllegalArgumentException("강의가 존재하지 않습니다.")
        );
        // lecture Info 조회
        LectureInfo findLectureInfo = this.lectureInfoRepository.findById(request.lectureInfoId()).orElseThrow(
                () -> new IllegalArgumentException("강의 정보가 존재하지 않습니다.")
        );

        // 강의 validator
        Integer historyType = this.lectureValidator.validateLecture(findLectureInfo);

        if(LectureHistoryTypeEnum.CAPACITY_FAIL.getValue() == historyType) {
            // 강의 신청 실패 히스토리 insert
            this.lectureHistoryRepository.save(findUser.getId(), findLecture.getId(), historyType);

            return ApplyLecturesDto.Response.builder()
                    .isSuccess(false)
                    .reason(LectureHistoryTypeEnum.CAPACITY_FAIL.getValue())
                    .build();
        }


        if(LectureHistoryTypeEnum.APPLICATION_DATE_FAIL.getValue() == historyType) {
            // 강의 신청 실패 히스토리
            LectureHistory savedLectureHistoryId = this.lectureHistoryRepository.save(findUser.getId(), findLecture.getId(), historyType);

            return ApplyLecturesDto.Response.builder()
                    .isSuccess(false)
                    .reason(LectureHistoryTypeEnum.APPLICATION_DATE_FAIL.getValue())
                    .build();
        }

        // 강의 정원수 ++
        findLectureInfo.addCurrentPersonCount();

        // 강의-유저 바운딩 테이블에 데이터 insert
        this.lectureReservationRepository.save(findLectureInfo.getLectureId(), findUser.getId());

        // 강의 신청 성공 히스토리 insert
        this.lectureHistoryRepository.save(findUser.getId(), findLecture.getId(), LectureHistoryTypeEnum.SUCCESS.getValue());

        // res 리턴
        return ApplyLecturesDto.Response.builder()
                .isSuccess(true)
                .reason(LectureHistoryTypeEnum.SUCCESS.getValue())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public GetLectureListDto.Response getLectureList() {
        // 날짜와 정원이 안 차있는 강의 정보 리스트 조회
        List<LectureInfo> findLectureInfoList = this.lectureInfoRepository.findAllAvailableLectureInfoWithLecture();

        List<LectureInfoWithLectureName> lectureInfoWithLectureNameList = findLectureInfoList.stream().map(li -> li.toLectureInfoWithLectureName()).collect(Collectors.toList());


        return GetLectureListDto.Response.builder()
                .lectureInfoWithLectureNameList(lectureInfoWithLectureNameList)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public GetUserLectureApplicationWhetherDto.Response getUserLectureApplicationWhether(GetUserLectureApplicationWhetherDto.Request request) {
        // userId로 유저 조회
        User findUser = this.userRepository.findById(request.userId()).orElseThrow(
                () -> new IllegalArgumentException("user가 존재하지 않습니다.")
        );

        // userId로 신청된 강의가 있는지
        List<LectureUserBounding> findLectureReservation
                = this.lectureReservationRepository.findAllReservationByUserId(request.userId());

        // 강의가 없을경우 빈리스트 반환
        if(findLectureReservation.isEmpty())
            return GetUserLectureApplicationWhetherDto.Response.builder()
                    .userLectureReservationInfoList(null)
                    .build();

        List<Long> userApplyLectuerInfoIdList = findLectureReservation.stream()
                .map(LectureUserBounding::getLectureInfoId)
                .collect(Collectors.toList());

        // 유저가 신청한 강의 리스트 조회
        List<LectureInfo> userApplyLectuerInfoList = this.lectureInfoRepository.findAllByIdList(userApplyLectuerInfoIdList);

        // userApplyLectuerInfoList와 findLectureReservation 두 리스트를
        // lectuerInfoId가 같은 객체끼리 매핑
        List<UserLectureReservationInfo> userLectureReservationInfoList = new ArrayList<>();

        for (LectureUserBounding lectureUserBounding : findLectureReservation) {
            Long lectureInfoId = lectureUserBounding.getLectureInfoId();
            for (LectureInfo lectureInfo : userApplyLectuerInfoList) {
                if(lectureInfoId == lectureInfo.getId()) {
                    userLectureReservationInfoList.add(UserLectureReservationInfo.builder()
                                    .lectureInfoId(lectureInfoId)
                                    .lectureName(lectureInfo.getLecture().getName())
                                    .userId(request.userId())
                                    .lectuerClassDate(lectureInfo.getClassDate())
                                    .lectuerReservationDate(lectureUserBounding.getCreateDate()).build());
                }
            }
        }


        // response 반환
        return GetUserLectureApplicationWhetherDto.Response.builder()
                .userLectureReservationInfoList(userLectureReservationInfoList)
                .build();
    }
}
