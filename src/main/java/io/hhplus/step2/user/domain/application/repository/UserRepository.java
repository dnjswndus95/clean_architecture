package io.hhplus.step2.user.domain.application.repository;

import io.hhplus.step2.user.domain.application.User;

public interface UserRepository {

    User findById(Long userId);
}
