package io.hhplus.step2.infra.repository.jpa;

import io.hhplus.step2.domain.LectureUserBounding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaLectureReservationRepository extends JpaRepository<LectureUserBounding, Long> {

    @Query("SELECT lr FROM LectureUserBounding lr WHERE lr.userId = :userId AND lr.isDeleted = false")
    List<LectureUserBounding> findLectureReservationByUserId(@Param("userId") Long userId);

    @Query("SELECT lr FROM LectureUserBounding lr WHERE lr.isDeleted = false AND lr.userId = :userId")
    Optional<LectureUserBounding> findByIdAndLectureId(@Param("userId") Long userId);
}
