package io.hhplus.step2.lecture.dto.response;

import lombok.Builder;

@Builder
public record ApplyLecturesResponse(
        Boolean isSuccess
) {
}
