package io.hhplus.step2.application.repository;

import io.hhplus.step2.domain.LectureUserBounding;

import java.util.List;
import java.util.Optional;

public interface LectureReservationRepository {

    public List<LectureUserBounding> findAllReservationByUserId(Long userId);

    public Optional<LectureUserBounding> findByUserIdAndLectureId(Long userId);

    public LectureUserBounding save(Long lectureInfoId, Long userId);
}
