package com.mirero.dataservice.data.adaptor.in.message;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.application.port.in.area.AreaCommandService;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentCommandService;
import com.mirero.dataservice.data.application.port.in.maskMap.MaskMapCommandService;
import com.mirero.dataservice.data.application.port.in.recipeInspectionSummary.RecipeInspectionSummaryCommandService;
import com.mirero.globalmodule.common.dto.FileData;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileDataConsumer {

    private static final String KAFKA_TOPIC = "pasing-data-topic";
    private final EquipmentCommandService equipmentCommandService;
    private final RecipeInspectionSummaryCommandService recipeInspectionSummaryCommandService;
    private final MaskMapCommandService maskMapCommandService;
    private final AreaCommandService areaCommandService;

    @KafkaListener(topics = KAFKA_TOPIC)
    public void consumeFileData(FileData fileData) {
        String fileName = fileData.fileName();

        EquipmentInfo equipmentInfo = equipmentCommandService.getOrCreateEquipment(fileName);
        log.info("장비 정보: {}", equipmentInfo);

        if (fileData instanceof LrfFileData lrfFileData) {
            RecipeInspectionSummaryInfo recipeInspectionSummaryInfo =
                    recipeInspectionSummaryCommandService.saveRecipeInspectionSummary(lrfFileData, equipmentInfo.id());

            List<AreaInfo> areaInfoList = areaCommandService.saveAreaList(lrfFileData, equipmentInfo.id());
        } else if (fileData instanceof RffFileData rffFileData) {
            MaskMapInfo maskMapInfo = maskMapCommandService.saveMaskMap(rffFileData, equipmentInfo.id());

            log.info("RFF 관련 데이터 중 마스크맵 요약 정보: {}", maskMapInfo);
        }

    }
}