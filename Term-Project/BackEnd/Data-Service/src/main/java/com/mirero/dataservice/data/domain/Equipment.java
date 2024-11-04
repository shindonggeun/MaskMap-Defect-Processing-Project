package com.mirero.dataservice.data.domain;

import com.mirero.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx_file_name", columnList = "file_name")
})
public class Equipment extends BaseEntity {

    @Id
    @Comment("장비 정보 아이디")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("일자별 파일명")
    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String fileName;
}
