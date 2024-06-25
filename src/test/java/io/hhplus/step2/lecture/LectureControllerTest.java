package io.hhplus.step2.lecture;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.step2.lecture.dto.LectureListElement;
import io.hhplus.step2.lecture.dto.request.ApplyLecturesRequest;
import io.hhplus.step2.lecture.dto.request.GetUserLectureApplicationWhetherRequest;
import io.hhplus.step2.lecture.dto.response.ApplyLecturesResponse;
import io.hhplus.step2.lecture.dto.response.GetLectureListResponse;
import io.hhplus.step2.lecture.dto.response.GetUserLectureApplicationWhetherResponse;
import io.hhplus.step2.lecture.presenters.controller.LectureController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LectureController.class)
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 특강신청_테스트() throws Exception{
        // given
        Long userId = 1L;
        GetUserLectureApplicationWhetherResponse expectedResponse = GetUserLectureApplicationWhetherResponse.builder()
                .isApplicationLecture(true)
                .build();

        GetUserLectureApplicationWhetherRequest request = GetUserLectureApplicationWhetherRequest.builder()
                .userId(1L)
                .lectureId(1L)
                .build();

        String requestJson = this.objectMapper.writeValueAsString(request);

        // when
        // then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/lectures/application")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(jsonPath("$.isApplicationLecture").value(expectedResponse.isApplicationLecture()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 특강신청조회() throws Exception {
        // given
        ApplyLecturesRequest request = ApplyLecturesRequest.builder()
                .userId(1L)
                .lectureId(1L)
                .build();

        ApplyLecturesResponse expectedResponse = ApplyLecturesResponse.builder()
                .isSuccess(true)
                .build();

        String requestJson = this.objectMapper.writeValueAsString(request);

        // when
        // then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/lectures/apply")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(expectedResponse.isSuccess()));
    }

    @Test
    void 특강리스트조회() throws Exception {
        // given
        LectureListElement element1 = new LectureListElement(1L, "강의 이름1", 29, 30);
        LectureListElement element2 = new LectureListElement(2L, "강의 이름2", 28, 30);

        List<LectureListElement> lectures = new ArrayList<>();
        lectures.add(element1);
        lectures.add(element2);

        GetLectureListResponse expectedResponse = GetLectureListResponse.builder()
                .lectures(lectures)
                .build();

        // when
        // then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/lectures"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.lectures").isArray())
                .andExpect(jsonPath("$.lectures[0].id").value(1))
                .andExpect(jsonPath("$.lectures[0].lectureName").value("강의 이름1"))
                .andExpect(jsonPath("$.lectures[0].currentPersonCount").value(29))
                .andExpect(jsonPath("$.lectures[0].limitPersonCount").value(30))
                .andExpect(jsonPath("$.lectures[1].id").value(2))
                .andExpect(jsonPath("$.lectures[1].lectureName").value("강의 이름2"))
                .andExpect(jsonPath("$.lectures[1].currentPersonCount").value(28))
                .andExpect(jsonPath("$.lectures[1].limitPersonCount").value(30));
    }
}
