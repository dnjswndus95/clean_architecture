package io.hhplus.step2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "lecture_history")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Proxy 조회를 위해 private x
@AllArgsConstructor
public class LectureHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "lecture_info_id")
    private Long lectureInfoId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    public LectureHistory(Long userId, Long lectureInfoId, Integer type) {
        this.userId = userId;
        this.lectureInfoId = lectureInfoId;
        this.type = type;
    }

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }

}

