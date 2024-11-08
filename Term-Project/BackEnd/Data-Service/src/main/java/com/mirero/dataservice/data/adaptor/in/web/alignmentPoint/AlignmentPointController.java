package com.mirero.dataservice.data.adaptor.in.web.alignmentPoint;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;
import com.mirero.dataservice.data.application.port.in.alignmentPoint.AlignmentPointQueryService;
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
@RequestMapping("/api/v1/data/alignmentPoints")
public class AlignmentPointController {

    private final AlignmentPointQueryService alignmentPointQueryService;

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Message<List<AlignmentPointInfo>>> getAlignmentPointListByEquipmentId(
            @PathVariable UUID equipmentId) {
        List<AlignmentPointInfo> alignmentPointInfoList
                = alignmentPointQueryService.getAlignmentPointListByEquipmentId(equipmentId);
        return ResponseEntity.ok(Message.success(alignmentPointInfoList));
    }
}
