package io.hhplus.step2.infra.repository.jpa;

import io.hhplus.step2.domain.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureHistoryRepository extends JpaRepository<LectureHistory, Long> {

    //public LectureHistoryEntity save(LectureHistoryEntity entity);
}
