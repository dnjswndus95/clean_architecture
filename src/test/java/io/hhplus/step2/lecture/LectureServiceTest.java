package io.hhplus.step2.lecture;

import io.hhplus.step2.application.enumerator.LectureHistoryTypeEnum;
import io.hhplus.step2.application.repository.*;
import io.hhplus.step2.application.service.LectureService;
import io.hhplus.step2.application.service.LectureServiceImpl;
import io.hhplus.step2.application.service.LectureValidator;
import io.hhplus.step2.controller.dto.lecture.ApplyLecturesDto;
import io.hhplus.step2.controller.dto.lecture.GetLectureListDto;
import io.hhplus.step2.controller.dto.lecture.GetUserLectureApplicationWhetherDto;
import io.hhplus.step2.domain.LectureInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class LectureServiceTest {

    @Autowired
    private LectureValidator lectureValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LectureInfoRepository lectureInfoRepository;
    @Autowired
    private LectureHistoryRepository lectureHistoryRepository;
    @Autowired
    private LectureReservationRepository lectureReservationRepository;
    @Autowired
    private LectureService lectureService;


    @Test
    @DisplayName("특강의 인원이 다 찼다면 실패해야 한다.")
    void 인원이_꽉찬_강의_신청() {
        // given
        ApplyLecturesDto.Request request = ApplyLecturesDto.Request.builder()
                .userId(1L)
                .lectureId(2L)
                .lectureInfoId(2L)
                .build();

        // when
        ApplyLecturesDto.Response res = this.lectureService.applyLectures(request);

        // then
        Assertions.assertThat(res.isSuccess()).isEqualTo(false);
        Assertions.assertThat(res.reason()).isEqualTo(LectureHistoryTypeEnum.CAPACITY_FAIL.getValue());
    }

    @Test
    @DisplayName("아직 특강신청 날짜가 아니라면 실패해야 한다.")
    void 특강신청날짜가_아닌_강의신청() {
        // given
        ApplyLecturesDto.Request request = ApplyLecturesDto.Request.builder()
                .userId(1L)
                .lectureId(1L)
                .lectureInfoId(1L)
                .build();

        // when
        ApplyLecturesDto.Response res = this.lectureService.applyLectures(request);

        // then
        Assertions.assertThat(res.isSuccess()).isEqualTo(false);
        Assertions.assertThat(res.reason()).isEqualTo(LectureHistoryTypeEnum.APPLICATION_DATE_FAIL.getValue());
    }

    @Test
    @DisplayName("특강 신청 성공")
    void 특강신청_성공() {
        // given
        ApplyLecturesDto.Request request = ApplyLecturesDto.Request.builder()
                .userId(1L)
                .lectureId(3L)
                .lectureInfoId(3L)
                .build();

        // when
        ApplyLecturesDto.Response res = this.lectureService.applyLectures(request);

        // then
        Assertions.assertThat(res.isSuccess()).isEqualTo(true);
        Assertions.assertThat(res.reason()).isEqualTo(LectureHistoryTypeEnum.SUCCESS.getValue());
    }

    @Test
    @DisplayName("신청가능한 모든 강의 조회")
    void 신청가능한_강의조회() {
        // given
        // request 객체 없음

        // when
        GetLectureListDto.Response response = this.lectureService.getLectureList();

        // then
        Assertions.assertThat(response.lectureInfoWithLectureNameList().get(0).lectureName()).isEqualTo("덜차있는 강의");
        Assertions.assertThat(response.lectureInfoWithLectureNameList().get(0).lectureId()).isEqualTo(3L);
        Assertions.assertThat(response.lectureInfoWithLectureNameList().get(0).lectureInfoId()).isEqualTo(3L);
    }

    @Test
    @DisplayName("내가 신청한 강의 조회")
    void 내가_신청한_강의조회() {
        // given
        GetUserLectureApplicationWhetherDto.Request request = GetUserLectureApplicationWhetherDto.Request.builder()
                .userId(1L)
                .build();

        //when
        GetUserLectureApplicationWhetherDto.Response response = this.lectureService.getUserLectureApplicationWhether(request);

        //then
        Assertions.assertThat(response.userLectureReservationInfoList().get(0).lectureName()).isEqualTo("꽉차있는 강의");
        Assertions.assertThat(response.userLectureReservationInfoList().get(1).lectureName()).isEqualTo("덜차있는 강의");
        Assertions.assertThat(response.userLectureReservationInfoList().get(0).lectureInfoId()).isEqualTo(2L);
        Assertions.assertThat(response.userLectureReservationInfoList().get(1).lectureInfoId()).isEqualTo(3L);

    }
}
