package io.hhplus.step2.infra.repository.jpa;

import io.hhplus.step2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
