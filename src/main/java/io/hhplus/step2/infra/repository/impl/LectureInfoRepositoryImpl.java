package io.hhplus.step2.infra.repository.impl;

import io.hhplus.step2.domain.LectureInfo;
import io.hhplus.step2.application.repository.LectureInfoRepository;
import io.hhplus.step2.infra.repository.jpa.JpaLectureInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureInfoRepositoryImpl implements LectureInfoRepository {

    private final JpaLectureInfoRepository jpaLectureInfoRepository;

    @Override
    public Optional<LectureInfo> findById(Long lectureInfoId) {
        return this.jpaLectureInfoRepository.findById(lectureInfoId);
    }

    @Override
    public List<LectureInfo> findAllAvailableLectureInfoWithLecture() {
        List<LectureInfo> allAvailableLectureInfoWithLecture = this.jpaLectureInfoRepository.findAllAvailableLectureInfoWithLecture();

        return allAvailableLectureInfoWithLecture;
    }

    @Override
    public List<LectureInfo> findAllByIdList(List<Long> idList) {
        return this.jpaLectureInfoRepository.findAllByIdIn(idList);
    }
}
