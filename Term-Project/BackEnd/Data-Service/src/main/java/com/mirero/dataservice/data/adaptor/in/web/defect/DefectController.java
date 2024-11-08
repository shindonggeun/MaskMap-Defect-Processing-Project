package com.mirero.dataservice.data.adaptor.in.web.defect;

import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectInfo;
import com.mirero.dataservice.data.application.port.in.defect.DefectQueryService;
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
@RequestMapping("/api/v1/data/defects")
public class DefectController {

    private final DefectQueryService defectQueryService;

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Message<List<DefectInfo>>> getDefectListByEquipmentId(@PathVariable UUID equipmentId) {
        List<DefectInfo> defectInfoList = defectQueryService.getDefectListByEquipmentId(equipmentId);
        return ResponseEntity.ok(Message.success(defectInfoList));
    }
}
