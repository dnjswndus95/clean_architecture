package io.hhplus.step2.lecture.unit_test;

import io.hhplus.step2.lecture.application.feature.LectureFeature;
import io.hhplus.step2.lecture.application.feature.LectureFeatureImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LectureFeatureTest {

    private LectureFeature lectureFeature = new LectureFeatureImpl();

    @Test
    void 신청일자_이전신청() throws Exception {
        // given
        String currentDateString = "2024-06-25 15:00:00";
        String applicationDateString = "2024-06-25 14:59:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = simpleDateFormat.parse(currentDateString);
        Date applicationDate = simpleDateFormat.parse(applicationDateString);

        // when
        Exception exception = assertThrows(Exception.class,
                () -> this.lectureFeature.validateLectureApplicationDate(currentDate, applicationDate));

        // then
        Assertions.assertThat(exception.getMessage()).isEqualTo("강의 신청일자가 아닙니다.");

    }

    @Test
    void 신청일자_이후신청() throws Exception {
        // given
        String currentDateString = "2024-06-24 15:00:00";
        String applicationDateString = "2024-06-25 14:59:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = simpleDateFormat.parse(currentDateString);
        Date applicationDate = simpleDateFormat.parse(applicationDateString);

        // when
        this.lectureFeature.validateLectureApplicationDate(currentDate, applicationDate);

        // then
        // 검증코드가 필요할까?
    }

    @Test
    void 정원_초과된_경우() throws Exception {
        // given
        Integer lectureCapacity = 30;
        Integer biggerNumber = 30;


        // when
        // 정원보다 많이 차있을 경우 요청한 경우
        Exception exception = assertThrows(Exception.class,
                () -> this.lectureFeature.validateLectureCapacity(biggerNumber, lectureCapacity));

        // then
        Assertions.assertThat(exception.getMessage()).isEqualTo("강의 정원을 초과했습니다.");
    }

    @Test
    void 정원_딱맞는_경우() throws Exception {
        // given
        Integer lectureCapacity = 30;
        Integer equalNumber = 31;

        // when
        Exception exception = assertThrows(Exception.class,
                () -> this.lectureFeature.validateLectureCapacity(equalNumber, lectureCapacity));

        // then
        Assertions.assertThat(exception.getMessage()).isEqualTo("강의 정원을 초과했습니다.");
    }

    @Test
    void 정원_남는_경우() throws Exception {
        // given
        Integer lectureCapacity = 30;
        Integer currentNumber = 29;

        // when
        this.lectureFeature.validateLectureCapacity(currentNumber, lectureCapacity);

        // then
        // 검증코드가 필요할까?
    }
}
