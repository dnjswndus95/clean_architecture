package io.hhplus.step2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "lecture_user_bounding")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureUserBounding {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_info_id")
    private Long lectureInfoId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;
    @PrePersist
    protected void onCreate() {
        isDeleted = false;
        createDate = new Date();
    }
    public LectureUserBounding(Long lectureInfoId, Long userId) {
        this.lectureInfoId = lectureInfoId;
        this.userId = userId;
    }
}
