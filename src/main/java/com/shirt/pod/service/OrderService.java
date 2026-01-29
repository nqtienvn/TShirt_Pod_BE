package com.shirt.pod.service;

import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<OrderDTO> getOrders(OrderStatus status, Pageable pageable);
}
