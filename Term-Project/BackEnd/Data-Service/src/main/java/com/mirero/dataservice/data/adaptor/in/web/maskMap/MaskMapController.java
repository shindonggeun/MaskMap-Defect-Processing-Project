package com.mirero.dataservice.data.adaptor.in.web.maskMap;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.application.port.in.maskMap.MaskMapQueryService;
import com.mirero.globalmodule.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data/maskMaps")
public class MaskMapController {

    private final MaskMapQueryService maskMapQueryService;

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Message<MaskMapInfo>> getMaskMapByEquipmentId(@PathVariable UUID equipmentId) {
        MaskMapInfo maskMapInfo = maskMapQueryService.getMaskMapByEquipmentId(equipmentId);
        return ResponseEntity.ok(Message.success(maskMapInfo));
    }
}
