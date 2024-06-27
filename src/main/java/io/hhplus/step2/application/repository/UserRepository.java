package io.hhplus.step2.application.repository;

import io.hhplus.step2.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long userId);
}
