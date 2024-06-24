package io.hhplus.step2.lecture.application.feature;


import java.util.Date;

public class LectureFeatureImpl implements LectureFeature {
    @Override
    public void validateLectureCapacity(Integer currentNumber, Integer capacityNumber) throws Exception {
        if(capacityNumber < currentNumber + 1)
            throw new Exception("강의 정원을 초과했습니다.");
    }

    @Override
    public void validateLectureApplicationDate(Date currentTime, Date lectureApplicationDate) throws Exception {
        if(currentTime.after(lectureApplicationDate))
            throw new Exception("강의 신청일자가 아닙니다.");
    }
}
