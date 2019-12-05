package com.aliyektan.bulut.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Timestamp updatedAt;

}
