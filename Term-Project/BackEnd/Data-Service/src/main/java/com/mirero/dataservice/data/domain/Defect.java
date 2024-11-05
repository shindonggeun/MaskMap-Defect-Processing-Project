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
public class Defect extends BaseEntity {

    @Id
    @Comment("결함 정보 아이디")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classify_number", referencedColumnName = "number")
    private ClassifyType classifyType;
}
