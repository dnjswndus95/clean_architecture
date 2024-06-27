package io.hhplus.step2.application.repository;

import io.hhplus.step2.domain.LectureInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureInfoRepository {

    Optional<LectureInfo> findById(Long lectureInfoId);

    List<LectureInfo> findAllAvailableLectureInfoWithLecture();

    List<LectureInfo> findAllByIdList(List<Long> idList);

}
