package io.hhplus.step2.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Table(name = "users")
@Entity
@Getter
public class User extends BaseTime {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
