package io.hhplus.step2.infra.repository.impl;

import io.hhplus.step2.domain.LectureUserBounding;
import io.hhplus.step2.application.repository.LectureReservationRepository;
import io.hhplus.step2.infra.repository.jpa.JpaLectureReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class LectureReservationRepositoryImpl implements LectureReservationRepository {

    private final JpaLectureReservationRepository jpaLectureReservationRepository;

    @Override
    public List<LectureUserBounding> findAllReservationByUserId(Long userId) {
        return this.jpaLectureReservationRepository.findLectureReservationByUserId(userId);
    }

    @Override
    public Optional<LectureUserBounding> findByUserIdAndLectureId(Long userId) {
        return this.jpaLectureReservationRepository.findByIdAndLectureId(userId);
    }

    @Override
    public LectureUserBounding save(Long lectureInfoId, Long userId) {
        return this.jpaLectureReservationRepository.save(new LectureUserBounding(lectureInfoId, userId));
    }
}
