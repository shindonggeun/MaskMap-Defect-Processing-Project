package com.mirero.dataservice.data.adaptor.in.message;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;
import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;
import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.application.port.in.alignmentPoint.AlignmentPointCommandService;
import com.mirero.dataservice.data.application.port.in.area.AreaCommandService;
import com.mirero.dataservice.data.application.port.in.classifyType.ClassifyTypeCommandService;
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

    private final Map<UUID, LrfFileData> lrfFileDataCache = new ConcurrentHashMap<>();
    private final Map<UUID, RffFileData> rffFileDataCache = new ConcurrentHashMap<>();

    @KafkaListener(topics = KAFKA_TOPIC)
    public void consumeFileData(FileData fileData) {
        String fileName = fileData.fileName();

        EquipmentInfo equipmentInfo = equipmentCommandService.getOrCreateEquipment(fileName);
        log.info("장비 정보: {}", equipmentInfo);

        UUID equipmentId = equipmentInfo.id();

        if (fileData instanceof LrfFileData lrfFileData) {
            lrfFileDataCache.put(equipmentId, lrfFileData); // LRF 데이터를 임시 저장

            RecipeInspectionSummaryInfo recipeInspectionSummaryInfo =
                    recipeInspectionSummaryCommandService.saveRecipeInspectionSummary(lrfFileData, equipmentId);

            List<AreaInfo> areaInfoList = areaCommandService.saveAreaList(lrfFileData, equipmentId);
            processIfBothDataAvailable(equipmentId);
        } else if (fileData instanceof RffFileData rffFileData) {
            rffFileDataCache.put(equipmentId, rffFileData); // RFF 데이터를 임시 저장

            MaskMapInfo maskMapInfo = maskMapCommandService.saveMaskMap(rffFileData, equipmentId);

            List<AlignmentPointInfo> alignmentPointInfoList =
                    alignmentPointCommandService.saveAlignmentPointList(rffFileData, equipmentId);
            processIfBothDataAvailable(equipmentId);
        }

    }

    private void processIfBothDataAvailable(UUID equipmentId) {
        LrfFileData lrfFileData = lrfFileDataCache.get(equipmentId);
        RffFileData rffFileData = rffFileDataCache.get(equipmentId);

        if (lrfFileData != null && rffFileData != null) {
            // Classify_Type 데이터를 완성하고 저장하는 로직 호출
            List<ClassifyTypeInfo> classifyTypeInfoList =
                    classifyTypeCommandService.saveClassifyTypeList(lrfFileData, rffFileData, equipmentId);

            log.info("분류 정보 데이터: {}", classifyTypeInfoList);

            lrfFileDataCache.remove(equipmentId);
            rffFileDataCache.remove(equipmentId);
        }
    }
}
