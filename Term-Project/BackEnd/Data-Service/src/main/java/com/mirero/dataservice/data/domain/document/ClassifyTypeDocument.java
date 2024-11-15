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
@Document(collection = "classify_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassifyTypeDocument {

    @Id
    private UUID id;

    @Field("name")
    private String name;

    @Field("color")
    private String color;

    @Field("number")
    private Integer number;

    @Field("equipment_id")
    private UUID equipmentId;
}
