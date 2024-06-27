package io.hhplus.step2.infra.repository.jpa;

import io.hhplus.step2.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureRepository extends JpaRepository<Lecture, Long> {

/*    @Query("SELECT l FROM Lecture l WHERE l.applicationDate > CURRENT_TIMESTAMP AND l.currentPersonCount < l.limitPersonCount")
    List<Lecture> findLectureByApplicationAndCurrentPersonCount();*/
}
