package io.hhplus.step2.infra.repository.impl;

import io.hhplus.step2.domain.User;
import io.hhplus.step2.application.repository.UserRepository;
import io.hhplus.step2.infra.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return this.jpaUserRepository.findById(userId);
    }
}
