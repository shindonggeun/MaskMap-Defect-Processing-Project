package com.mirero.dataservice.data.domain.entity;

import com.mirero.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "alignment_point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AlignmentPointEntity extends BaseEntity {

    @Id
    @Comment("정렬 포인트 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("순서 번호")
    @Column(nullable = false)
    private Integer idx;

    @Comment("X 좌표")
    @Column(nullable = false)
    private Double x;

    @Comment("Y 좌표")
    @Column(nullable = false)
    private Double y;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private EquipmentEntity equipment;
}
