package io.hhplus.step2.lecture;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.step2.lecture.dto.request.ApplyLecturesRequest;
import io.hhplus.step2.lecture.dto.response.ApplyLecturesResponse;
import io.hhplus.step2.lecture.dto.response.GetUserLectureApplicationWhetherResponse;
import io.hhplus.step2.lecture.presenters.controller.LectureController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

        // when
        // then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/lectures/application/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isApplicationLecture").value(expectedResponse.isApplicationLecture()))
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(expectedResponse.isSuccess()));
    }
}
