package io.hhplus.step2.application.service;

import io.hhplus.step2.domain.LectureInfo;
import io.hhplus.step2.application.enumerator.LectureHistoryTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LectureValidator {

    // lecture의 신청날짜, 정원을 validate 하는 함수
    public Integer validateLecture(LectureInfo lectureInfo) {
        if(lectureInfo.getCurrentPersonCount() >= lectureInfo.getLimitPersonCount())
            return LectureHistoryTypeEnum.CAPACITY_FAIL.getValue();

        if(lectureInfo.getApplicationDate().after(new Date()))
            return LectureHistoryTypeEnum.APPLICATION_DATE_FAIL.getValue();


        return LectureHistoryTypeEnum.SUCCESS.getValue();
    }
}
