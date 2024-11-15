package com.mirero.dataservice.data.domain.entity;

import com.mirero.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;
import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Comment("일자별 파일명")
    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String fileName;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassifyType> classifyTypeList;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Area> areaList;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlignmentPoint> alignmentPointList;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defect> defectList;
}