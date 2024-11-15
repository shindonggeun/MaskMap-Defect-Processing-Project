package com.mirero.dataservice.data.domain.document;

import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@Document(collation = "defect")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DefectDocument {

    @Id
    private UUID id;

    @Field("idx")
    private Integer idx;

    @Field("size_width")
    private Double sizeWidth;

    @Field("size_height")
    private Double sizeHeight;

    @Field("die_x")
    private Double dieX;

    @Field("die_y")
    private Double dieY;

    @Field("ref_x")
    private Double refX;

    @Field("ref_y")
    private Double refY;

    @Field("rel_x")
    private Double relX;

    @Field("rel_y")
    private Double relY;

    @Field("area")
    private Double area;

    @Field("classify_number")
    private Integer classifyNumber;

    @Field("equipment_id")
    private UUID equipmentId;
}
