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
public class MaskMap extends BaseEntity {

    @Id
    @Comment("마스크 맵 정보 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("마스크 크기 너비")
    @Column(nullable = false)
    private Double maskWidth;

    @Comment("마스크 크기 높이")
    @Column(nullable = false)
    private Double maskHeight;

    @Comment("픽셀 크기 너비")
    @Column(nullable = false)
    private Double pixelWidth;

    @Comment("픽셀 크기 높이")
    @Column(nullable = false)
    private Double pixelHeight;

    @Comment("다이 피치 X 좌표")
    @Column(nullable = false)
    private Double pitchX;

    @Comment("다이 피치 Y 좌표")
    @Column(nullable = false)
    private Double pitchY;

    @Comment("다이 원점 X 좌표")
    @Column(nullable = false)
    private Double originX;

    @Comment("다이 원점 Y 좌표")
    @Column(nullable = false)
    private Double originY;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;
}
