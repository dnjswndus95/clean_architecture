package io.hhplus.step2.domain;

import io.hhplus.step2.application.model.LectureInfoWithLectureName;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Table(name = "lecture_info")
@Entity
@Getter
public class LectureInfo extends BaseTime{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_id")
    private Long lectureId;

    @Column(name = "limit_person_count")
    private Integer limitPersonCount;

    @Column(name = "current_person_count")
    private Integer currentPersonCount;

    @Column(name = "application_date")
    private Date applicationDate;

    @Column(name = "class_date")
    private Date classDate;

    @ManyToOne
    @JoinColumn(name = "lecture_id", insertable = false, updatable = false)
    private Lecture lecture;

    public void addCurrentPersonCount() {
        ++currentPersonCount;
    }

    public LectureInfoWithLectureName toLectureInfoWithLectureName() {
        return LectureInfoWithLectureName.builder()
                .lectureInfoId(this.id)
                .lectureId(this.lectureId)
                .applicationDate(this.applicationDate)
                .classDate(this.classDate)
                .currentPersonCount(this.currentPersonCount)
                .limitPersonCount(this.limitPersonCount)
                .lectureName(this.lecture.getName())
                .build();
    }

}
