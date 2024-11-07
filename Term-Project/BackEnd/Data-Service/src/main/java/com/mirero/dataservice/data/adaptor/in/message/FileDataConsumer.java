package com.mirero.dataservice.data.adaptor.in.message;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.application.port.in.alignmentPoint.AlignmentPointCommandService;
import com.mirero.dataservice.data.application.port.in.area.AreaCommandService;
import com.mirero.dataservice.data.application.port.in.classifyType.ClassifyTypeCommandService;
import com.mirero.dataservice.data.application.port.in.defect.DefectCommandService;
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

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileDataConsumer {

    private static final String KAFKA_TOPIC = "pasing-data-topic";
    private final EquipmentCommandService equipmentCommandService;
    private final RecipeInspectionSummaryCommandService recipeInspectionSummaryCommandService;
    private final MaskMapCommandService maskMapCommandService;
    private final AreaCommandService areaCommandService;
    private final AlignmentPointCommandService alignmentPointCommandService;
    private final ClassifyTypeCommandService classifyTypeCommandService;
    private final DefectCommandService defectCommandService;

    private final Map<UUID, LrfFileData> lrfFileDataCache = new ConcurrentHashMap<>();
    private final Map<UUID, RffFileData> rffFileDataCache = new ConcurrentHashMap<>();

    @KafkaListener(topics = KAFKA_TOPIC)
    public void consumeFileData(FileData fileData) {
        String fileName = fileData.fileName();
        EquipmentInfo equipmentInfo = equipmentCommandService.getOrCreateEquipment(fileName);

        UUID equipmentId = equipmentInfo.id();

        if (fileData instanceof LrfFileData lrfFileData) {
            lrfFileDataCache.put(equipmentId, lrfFileData); // LRF 데이터를 임시 저장
            recipeInspectionSummaryCommandService.saveRecipeInspectionSummary(lrfFileData, equipmentId);
            areaCommandService.saveAreaList(lrfFileData, equipmentId);
        } else if (fileData instanceof RffFileData rffFileData) {
            rffFileDataCache.put(equipmentId, rffFileData); // RFF 데이터를 임시 저장
            maskMapCommandService.saveMaskMap(rffFileData, equipmentId);
            alignmentPointCommandService.saveAlignmentPointList(rffFileData, equipmentId);
        }

        processIfBothDataAvailable(equipmentId);
    }

    private void processIfBothDataAvailable(UUID equipmentId) {
        LrfFileData lrfFileData = lrfFileDataCache.get(equipmentId);
        RffFileData rffFileData = rffFileDataCache.get(equipmentId);

        if (lrfFileData != null && rffFileData != null) {
            classifyTypeCommandService.saveClassifyTypeList(lrfFileData, rffFileData, equipmentId);
            defectCommandService.saveDefectList(lrfFileData, rffFileData, equipmentId);

            lrfFileDataCache.remove(equipmentId);
            rffFileDataCache.remove(equipmentId);
        }
    }
}
