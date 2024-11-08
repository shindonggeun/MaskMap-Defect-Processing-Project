package com.mirero.dataservice.data.adaptor.in.web.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentQueryService;
import com.mirero.globalmodule.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentQueryService equipmentQueryService;

    @GetMapping
    public ResponseEntity<Message<List<EquipmentInfo>>> getEquipmentListByCreatedDateRange(
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<EquipmentInfo> equipmentInfoList = equipmentQueryService.getEquipmentListByCreatedDateRange(startDate, endDate);
        return ResponseEntity.ok(Message.success(equipmentInfoList));
    }
}
