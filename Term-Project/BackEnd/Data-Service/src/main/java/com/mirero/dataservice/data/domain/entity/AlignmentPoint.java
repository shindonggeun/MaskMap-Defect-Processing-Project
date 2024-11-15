package com.mirero.dataservice.data.domain;

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
public class AlignmentPoint extends BaseEntity {

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
    private Equipment equipment;
}
