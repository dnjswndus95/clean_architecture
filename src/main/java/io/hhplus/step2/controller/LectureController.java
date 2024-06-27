package io.hhplus.step2.controller;

import io.hhplus.step2.application.service.LectureService;
import io.hhplus.step2.controller.dto.lecture.ApplyLecturesDto;
import io.hhplus.step2.controller.dto.lecture.GetLectureListDto;
import io.hhplus.step2.controller.dto.lecture.GetUserLectureApplicationWhetherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/application")
    public GetUserLectureApplicationWhetherDto.Response GetUserLectureApplicationWhether(
            @RequestBody GetUserLectureApplicationWhetherDto.Request request
            ) {

        return this.lectureService.getUserLectureApplicationWhether(request);
    }

    @PostMapping("/apply")
    public ApplyLecturesDto.Response ApplyLectures(@RequestBody ApplyLecturesDto.Request request) {
        return this.lectureService.applyLectures(request);
    }

    @GetMapping("")
    public GetLectureListDto.Response getLectureList() {
        return this.lectureService.getLectureList();
    }

}
