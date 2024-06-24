package io.hhplus.step2.lecture.presenters.controller;

import io.hhplus.step2.lecture.dto.request.ApplyLecturesRequest;
import io.hhplus.step2.lecture.dto.response.ApplyLecturesResponse;
import io.hhplus.step2.lecture.dto.response.GetUserLectureApplicationWhetherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    @GetMapping("/application/{userId}")
    public GetUserLectureApplicationWhetherResponse GetUserLectureApplicationWhether(
            @PathVariable Long userId
    ) {
        return GetUserLectureApplicationWhetherResponse.builder()
                .isApplicationLecture(true)
                .build();
    }

    @PostMapping("/apply")
    public ApplyLecturesResponse ApplyLectures(@RequestBody ApplyLecturesRequest request) {

        return ApplyLecturesResponse.builder()
                .isSuccess(true)
                .build();
    }

}
