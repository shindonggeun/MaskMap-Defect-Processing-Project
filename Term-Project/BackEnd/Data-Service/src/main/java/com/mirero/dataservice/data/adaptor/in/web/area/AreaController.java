package com.mirero.dataservice.data.adaptor.in.web.area;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.dataservice.data.application.port.in.area.AreaQueryService;
import com.mirero.globalmodule.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data/areas")
public class AreaController {

    private final AreaQueryService areaQueryService;

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Message<List<AreaInfo>>> getRecipeInspectionSummaryByEquipmentId(
            @PathVariable UUID equipmentId) {
        List<AreaInfo> areaInfoList = areaQueryService.getAreaListByEquipmentId(equipmentId);
        return ResponseEntity.ok(Message.success(areaInfoList));
    }
}
