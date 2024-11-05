package com.mirero.dataservice.data.domain;

import com.mirero.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassifyType extends BaseEntity {

    @Id
    @Comment("분류 정보 아이디")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("분류 이름")
    @Column(nullable = false)
    private String name;

    @Comment("분류 색상")
    @Column(nullable = false)
    private String color;

    @Comment("분류 번호")
    @Column(nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @OneToMany(mappedBy = "classifyType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defect> defectList;
}
