package io.hhplus.step2.lecture.presenters.controller;

import io.hhplus.step2.lecture.application.service.LectureService;
import io.hhplus.step2.lecture.dto.LectureListElement;
import io.hhplus.step2.lecture.dto.request.ApplyLecturesRequest;
import io.hhplus.step2.lecture.dto.request.GetUserLectureApplicationWhetherRequest;
import io.hhplus.step2.lecture.dto.response.ApplyLecturesResponse;
import io.hhplus.step2.lecture.dto.response.GetLectureListResponse;
import io.hhplus.step2.lecture.dto.response.GetUserLectureApplicationWhetherResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/application")
    public GetUserLectureApplicationWhetherResponse GetUserLectureApplicationWhether(
            @RequestBody GetUserLectureApplicationWhetherRequest request
            ) {
        return this.lectureService.getUserLectureApplicationWhether(request);

        /*return GetUserLectureApplicationWhetherResponse.builder()
                .isApplicationLecture(true)
                .build();*/
    }

    @PostMapping("/apply")
    public ApplyLecturesResponse ApplyLectures(@RequestBody ApplyLecturesRequest request) {

        return this.lectureService.applyLectures(request);

        /*return ApplyLecturesResponse.builder()
                .isSuccess(true)
                .build();*/
    }

    @GetMapping("")
    public GetLectureListResponse getLectureList() {
        LectureListElement element1 = new LectureListElement(1L, "강의 이름1", 29, 30);
        LectureListElement element2 = new LectureListElement(2L, "강의 이름2", 28, 30);

        List<LectureListElement> lectures = new ArrayList<>();
        lectures.add(element1);
        lectures.add(element2);

        return GetLectureListResponse.builder()
                .lectures(lectures)
                .build();
    }

}
