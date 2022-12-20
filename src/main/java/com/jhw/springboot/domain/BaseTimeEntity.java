package com.jhw.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속할경우, 필드들로 칼럼으로 인식하도록함
@EntityListeners(AuditingEntityListener.class) // Auditing기능을 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될떄 시간이 자동 저장됨
    private LocalDateTime createDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;

}
