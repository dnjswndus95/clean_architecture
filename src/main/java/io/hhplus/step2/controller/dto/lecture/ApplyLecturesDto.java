package io.hhplus.step2.controller.dto.lecture;

import lombok.Builder;

public class ApplyLecturesDto {

    @Builder
    public record Request(
            Long userId,
            Long lectureId,
            Long lectureInfoId
    ) {}

    @Builder
    public record Response(
            Boolean isSuccess,
            Integer reason
    ) {}
}
