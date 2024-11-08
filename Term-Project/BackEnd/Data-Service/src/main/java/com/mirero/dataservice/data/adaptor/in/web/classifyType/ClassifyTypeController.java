package com.mirero.dataservice.data.adaptor.in.web.classifyType;

import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;
import com.mirero.dataservice.data.application.port.in.classifyType.ClassifyTypeQueryService;
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
@RequestMapping("/api/v1/data/classifyTypes")
public class ClassifyTypeController {

    private final ClassifyTypeQueryService classifyTypeQueryService;

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Message<List<ClassifyTypeInfo>>> getClassifyTypeListByEquipmentId(
            @PathVariable UUID equipmentId) {
        List<ClassifyTypeInfo> classifyTypeInfoList =
                classifyTypeQueryService.getClassifyTypeListByEquipmentId(equipmentId);
        return ResponseEntity.ok(Message.success(classifyTypeInfoList));
    }
}
