package io.hhplus.step2.controller.dto.lecture;

import io.hhplus.step2.application.model.LectureInfoWithLectureName;
import lombok.Builder;

import java.util.List;

public class GetLectureListDto {

    public record Request() {}

    @Builder
    public record Response(
            List<LectureInfoWithLectureName> lectureInfoWithLectureNameList
    ) {}
}
