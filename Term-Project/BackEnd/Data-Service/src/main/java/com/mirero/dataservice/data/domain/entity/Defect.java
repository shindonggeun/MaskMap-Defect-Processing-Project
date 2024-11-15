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
@Table(indexes = {
        @Index(name = "idx_defect_equipment_id_classify_number", columnList = "equipment_id, classify_number")
})
public class Defect extends BaseEntity {

    @Id
    @Comment("결함 정보 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("결함 순서 번호")
    @Column(nullable = false)
    private Integer idx;

    @Comment("너비")
    @Column(nullable = false)
    private Double sizeWidth;

    @Comment("높이")
    @Column(nullable = false)
    private Double sizeHeight;

    @Comment("결함 고유 식별자")
    @Column(nullable = false)
    private String uniqueId;

    @Comment("다이 X 좌표")
    @Column(nullable = false)
    private Double dieX;

    @Comment("다이 Y 좌표")
    @Column(nullable = false)
    private Double dieY;

    @Comment("참조 위치의 X 좌표")
    @Column(nullable = false)
    private Double refX;

    @Comment("참조 위치의 Y 좌표")
    @Column(nullable = false)
    private Double refY;

    @Comment("상대적 위치의 X 좌표")
    @Column(nullable = false)
    private Double relX;

    @Comment("상대적 위치의 Y 좌표")
    @Column(nullable = false)
    private Double relY;

    @Comment("결함 면적")
    @Column(nullable = false)
    private Double area;

    @Comment("분류 번호")
    @Column(nullable = false)
    private Integer classifyNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
