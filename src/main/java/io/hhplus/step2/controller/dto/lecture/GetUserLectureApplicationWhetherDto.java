package io.hhplus.step2.controller.dto.lecture;

import io.hhplus.step2.application.model.UserLectureReservationInfo;
import lombok.Builder;

import java.util.List;

public class GetUserLectureApplicationWhetherDto {

    @Builder
    public record Request(
            Long userId
    ) {}

    @Builder
    public record Response(
            List<UserLectureReservationInfo> userLectureReservationInfoList
    ) {}
}
