package com.mirero.dataservice.data.domain.document;

import jakarta.persistence.Id;
import java.time.LocalDateTime;
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
@Document(collection = "recipe_inspection_summary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeInspectionSummaryDocument {

    @Id
    private UUID id;

    @Field("mask_id")
    private String maskId;

    @Field("lot_id")
    private String lotId;

    @Field("step_id")
    private String stepId;

    @Field("device_id")
    private String deviceId;

    @Field("insp_type")
    private Integer inspType;

    @Field("pod_id")
    private Long podId;

    @Field("rotation")
    private Double rotation;

    @Field("equip_id")
    private String equipId;

    @Field("rcp_ver")
    private String rcpVer;

    @Field("scan_time")
    private LocalDateTime scanTime;

    @Field("end_time")
    private LocalDateTime endTime;

    @Field("pixel_size")
    private Double pixelSize;

    @Field("total_stripe_number")
    private Integer totalStripeNumber;

    @Field("start_stripe_number")
    private Integer startStripeNumber;

    @Field("result_folder")
    private String resultFolder;

    @Field("insp_no")
    private String inspNo;

    @Field("equipment_id")
    private UUID equipmentId;
}
