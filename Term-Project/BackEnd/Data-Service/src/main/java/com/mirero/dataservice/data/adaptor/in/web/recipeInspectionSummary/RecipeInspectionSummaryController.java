package com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.application.port.in.recipeInspectionSummary.RecipeInspectionSummaryQueryService;
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
@RequestMapping("/api/v1/data/recipeInspectionSummaries")
public class RecipeInspectionSummaryController {

    private final RecipeInspectionSummaryQueryService recipeInspectionSummaryQueryService;

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Message<RecipeInspectionSummaryInfo>> getRecipeInspectionSummaryByEquipmentId(
            @PathVariable UUID equipmentId) {
        RecipeInspectionSummaryInfo recipeInspectionSummaryInfo =
                recipeInspectionSummaryQueryService.getRecipeInspectionSummaryByEquipmentId(equipmentId);
        return ResponseEntity.ok(Message.success(recipeInspectionSummaryInfo));
    }
}
