package com.shirt.pod.service;

import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrders(OrderStatus status);
}
