package io.hhplus.step2.application.repository;

import io.hhplus.step2.domain.Lecture;

import java.util.Optional;

public interface LectureRepository {

    public Optional<Lecture> findById(Long id);

}
