package io.hhplus.step2.lecture.dto.request;

import lombok.Builder;

@Builder
public record ApplyLecturesRequest(
        Long userId,
        Long lectureId
) {
}
