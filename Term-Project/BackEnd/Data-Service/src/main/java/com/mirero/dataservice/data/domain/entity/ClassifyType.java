package com.mirero.dataservice.data.domain.entity;

import com.mirero.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx_classify_type_equipment_id_number", columnList = "equipment_id, number")
})
public class ClassifyType extends BaseEntity {

    @Id
    @Comment("분류 정보 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("분류 이름")
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Comment("분류 색상")
    @Column(columnDefinition = "VARCHAR(12)", nullable = false)
    private String color;

    @Comment("분류 번호")
    @Column(nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
