package io.hhplus.step2.application.model;

import lombok.Builder;

import java.util.Date;

@Builder
public record LectureInfoWithLectureName(
        Long lectureId,
        Long lectureInfoId,
        String lectureName,
        Integer limitPersonCount,
        Integer currentPersonCount,
        Date classDate,
        Date applicationDate
) {
}
