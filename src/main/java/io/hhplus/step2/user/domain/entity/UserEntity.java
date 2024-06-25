package io.hhplus.step2.user.domain.entity;

import io.hhplus.step2.user.domain.application.User;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .createDate(this.createDate)
                .updateDate(this.updateDate)
                .build();
    }
}
