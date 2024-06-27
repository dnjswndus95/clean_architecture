package io.hhplus.step2.application.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
public record UserLectureReservationInfo(
        Long userId,
        String lectureName,
        Long lectureInfoId,
        Date lectuerClassDate,
        Date lectuerReservationDate
) {
}
