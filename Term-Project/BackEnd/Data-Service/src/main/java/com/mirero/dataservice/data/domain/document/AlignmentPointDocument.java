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
@Document(collection = "alignment_point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AlignmentPointDocument {

    @Id
    private UUID id;

    @Field("idx")
    private Integer idx;

    @Field("x")
    private Double x;

    @Field("y")
    private Double y;

    @Field("equipment_id")
    private UUID equipmentId;
}
