package io.hhplus.step2.lecture.application.feature;

import java.util.Date;

public interface LectureFeature {

    // 강의 정원 검증
    public void validateLectureCapacity(Integer currentNumber, Integer capacityNumber) throws Exception;

    // 강의 신청 날짜 검증
    public void validateLectureApplicationDate(Date currentTime, Date lectureApplicationDate) throws Exception;
}
