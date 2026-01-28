package com.shirt.pod.controller;

import com.shirt.pod.model.dto.response.ApiResponse;
import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.enums.OrderStatus;
import com.shirt.pod.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderDTO>> getOrders(@RequestParam(required = false) OrderStatus status) {
        return ApiResponse.<List<OrderDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Orders fetched successfully")
                .data(orderService.getOrders(status))
                .build();
    }
}
