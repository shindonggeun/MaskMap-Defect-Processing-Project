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
public class Area extends BaseEntity {

    @Id
    @Comment("영역 정보 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("다이 크기 너비")
    @Column(nullable = false)
    private Double dieSizeWidth;

    @Comment("다이 크기 높이")
    @Column(nullable = false)
    private Double dieSizeHeight;

    @Comment("다이 피치 X 좌표")
    @Column(nullable = false)
    private Double diePitchX;

    @Comment("다이 피치 Y 좌표")
    @Column(nullable = false)
    private Double diePitchY;

    @Comment("다이 개수 X")
    @Column(nullable = false)
    private Integer dieCountX;

    @Comment("다이 개수 Y")
    @Column(nullable = false)
    private Integer dieCountY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
