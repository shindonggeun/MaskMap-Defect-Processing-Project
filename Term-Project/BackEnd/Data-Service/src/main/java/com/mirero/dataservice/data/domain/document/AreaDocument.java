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
@Document(collection = "area")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaDocument {

    @Id
    private UUID id;

    @Field("die_size_width")
    private Double dieSizeWidth;

    @Field("die_size_height")
    private Double dieSizeHeight;

    @Field("die_pitch_x")
    private Double diePitchX;

    @Field("die_pitch_y")
    private Double diePitchY;

    @Field("die_count_x")
    private Integer dieCountX;

    @Field("die_count_y")
    private Integer dieCountY;

    @Field("equipment_id")
    private UUID equipmentId;
}
