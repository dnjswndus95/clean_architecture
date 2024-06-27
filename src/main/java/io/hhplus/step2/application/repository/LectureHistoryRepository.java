package io.hhplus.step2.application.repository;

import io.hhplus.step2.domain.LectureHistory;

// @Repository 안써도됨? 왜? interface라 생성자가 없어서 빈으로 등록 못함
public interface LectureHistoryRepository {

    public LectureHistory save(Long userId, Long lectureId, Integer historyType);
}
