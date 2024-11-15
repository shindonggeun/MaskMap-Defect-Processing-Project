package com.mirero.dataservice.data.domain.entity;

import com.mirero.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "recipe_inspection_summary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeInspectionSummaryEntity extends BaseEntity {

    @Id
    @Comment("레시피 검사 요약 정보 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("마스크 아이디")
    @Column(nullable = false)
    private String maskId;

    @Comment("Lot 아이디")
    @Column(nullable = false)
    private String lotId;

    @Comment("단계 아이디")
    @Column(nullable = false)
    private String stepId;

    @Comment("장비 아이디")
    @Column(nullable = false)
    private String deviceId;

    @Comment("검사 유형")
    @Column(nullable = false)
    private Integer inspType;

    @Comment("Pod 아이디")
    @Column(nullable = false)
    private Long podId;

    @Comment("마스크 회전 각도")
    @Column(nullable = false)
    private Double rotation;

    @Comment("장비 아이디")
    @Column(nullable = false)
    private String equipId;

    @Comment("레시피 버전")
    @Column(nullable = false)
    private String rcpVer;

    @Comment("검사 시작 시간")
    @Column(nullable = false)
    private LocalDateTime scanTime;

    @Comment("검사 종료 시간")
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Comment("픽셀 크기")
    @Column(nullable = false)
    private Double pixelSize;

    @Comment("총 줄무늬 수")
    @Column(nullable = false)
    private Integer totalStripeNumber;

    @Comment("시작 줄무늬 수")
    @Column(nullable = false)
    private Integer startStripeNumber;

    @Comment("결과 폴더")
    @Column(nullable = false)
    private String resultFolder;

    @Comment("검사 파일 번호(이름)")
    @Column(nullable = false)
    private String inspNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private EquipmentEntity equipment;
}
