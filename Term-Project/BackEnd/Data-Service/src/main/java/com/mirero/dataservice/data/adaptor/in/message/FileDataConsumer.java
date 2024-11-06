package com.mirero.dataservice.data.adaptor.in.message;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentCommandService;
import com.mirero.dataservice.data.application.port.in.recipeInspectionSummary.RecipeInspectionSummaryCommandService;
import com.mirero.globalmodule.common.dto.FileData;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileDataConsumer {

    private static final String KAFKA_TOPIC = "pasing-data-topic";
    private final EquipmentCommandService equipmentCommandService;
    private final RecipeInspectionSummaryCommandService recipeInspectionSummaryCommandService;

    @KafkaListener(topics = KAFKA_TOPIC)
    public void consumeFileData(FileData fileData) {
        String fileName = fileData.fileName();

        EquipmentInfo equipmentInfo = equipmentCommandService.getOrCreateEquipment(fileName);
        log.info("장비 정보: {}", equipmentInfo);

        if (fileData instanceof LrfFileData lrfFileData) {
            RecipeInspectionSummaryInfo recipeInspectionSummaryInfo =
                    recipeInspectionSummaryCommandService.saveRecipeInspectionSummary(lrfFileData, equipmentInfo.id());

            log.info("LRF 관련 데이터 중 레시피 검사 요약 정보: {}", recipeInspectionSummaryInfo);
        } else if (fileData instanceof RffFileData rffFileData) {
            log.info("RFF 관련 데이터 각 테이블에 뿌리기 작업");
        }

    }
}
