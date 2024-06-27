package io.hhplus.step2.infra.repository.impl;

import io.hhplus.step2.domain.Lecture;
import io.hhplus.step2.application.repository.LectureRepository;
import io.hhplus.step2.infra.repository.jpa.JpaLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {

    private final JpaLectureRepository jpaLectureRepository;

    @Override
    public Optional<Lecture> findById(Long id) {
        // 여기서 find가 없을 경우 예외처리를 해야 되는 것인지...
        Optional<Lecture> findLecture = jpaLectureRepository.findById(id);

        return findLecture;
    }
}
