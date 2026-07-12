package com.inventory.controller;

import com.inventory.constant.ApiResponse;
import com.inventory.dto.response.DashboardResponse;
import com.inventory.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> dashboard() {

        return ResponseEntity.ok(
                ApiResponse.<DashboardResponse>builder()
                        .success(true)
                        .message("Dashboard loaded successfully")
                        .data(dashboardService.getDashboard())
                        .build());

    }

}