package io.hhplus.step2.infra.repository.impl;

import io.hhplus.step2.domain.LectureHistory;
import io.hhplus.step2.application.repository.LectureHistoryRepository;
import io.hhplus.step2.infra.repository.jpa.JpaLectureHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LectureHistoryRepositoryImpl implements LectureHistoryRepository {

    private final JpaLectureHistoryRepository jpaLectureHistoryRepository;
    @Override
    public LectureHistory save(Long userId, Long lectureId, Integer historyType) {
        return this.jpaLectureHistoryRepository.save(new LectureHistory(userId, lectureId, historyType));
    }
}
