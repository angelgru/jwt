package com.example.jwt;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Data
@MappedSuperclass
//@MappedSuperclass tells the JPA provider to include the base class persistent properties as if they were declared
// by the child class extending the superclass annotated with @MappedSuperclass.
public class DateAudit<E> {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @CreatedBy
    private E createdBy;

    @LastModifiedBy
    private E lastModifiedBy;
}
