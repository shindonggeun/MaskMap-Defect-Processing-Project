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
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MaskMap {

    @Id
    private UUID id;

    @Field("mask_width")
    private Double maskWidth;

    @Field("mask_height")
    private Double maskHeight;

    @Field("pixel_width")
    private Double pixelWidth;

    @Field("pixel_height")
    private Double pixelHeight;

    @Field("pitch_x")
    private Double pitchX;

    @Field("pitch_y")
    private Double pitchY;

    @Field("origin_x")
    private Double originX;

    @Field("origin_y")
    private Double originY;

    @Field("equipment_id")
    private UUID equipmentId;
}
