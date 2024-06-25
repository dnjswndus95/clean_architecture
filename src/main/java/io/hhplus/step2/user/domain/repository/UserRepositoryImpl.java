package io.hhplus.step2.user.domain.repository;

import io.hhplus.step2.user.domain.application.User;
import io.hhplus.step2.user.domain.application.repository.UserRepository;
import io.hhplus.step2.user.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findById(Long userId) {
        UserEntity findUser = this.jpaUserRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("user가 존재하지 않습니다.")
        );

        return findUser.toDomain();
    }
}
