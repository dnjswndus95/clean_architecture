package io.hhplus.step2.infra.repository.jpa;

import io.hhplus.step2.domain.LectureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaLectureInfoRepository extends JpaRepository<LectureInfo, Long> {

    @Query("SELECT li FROM LectureInfo li JOIN FETCH li.lecture WHERE li.id = :lectureId")
    Optional<LectureInfo> findByLectureIdWithLectureName(Long lectureId);


    @Query("SELECT li FROM LectureInfo li JOIN FETCH li.lecture WHERE li.currentPersonCount < li.limitPersonCount AND li.applicationDate < current_timestamp")
    List<LectureInfo> findAllAvailableLectureInfoWithLecture();

    List<LectureInfo> findAllByIdIn(List<Long> idList);
}