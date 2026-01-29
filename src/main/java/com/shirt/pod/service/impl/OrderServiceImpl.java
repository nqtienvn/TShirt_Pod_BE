package com.shirt.pod.service.impl;

import com.shirt.pod.mapper.OrderMapper;
import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.Order;
import com.shirt.pod.model.entity.enums.OrderStatus;
import com.shirt.pod.repository.OrderRepository;
import com.shirt.pod.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderDTO> getOrders(OrderStatus status, Pageable pageable) {
        log.info("Fetching orders with status: {}, pageable: {}", status, pageable);

        Page<Order> orderPage = (status != null)
                ? orderRepository.findByStatus(status, pageable)
                : orderRepository.findAll(pageable);

        log.info("Found {} orders (page {}/{}, size {})",
                orderPage.getTotalElements(),
                orderPage.getNumber(),
                orderPage.getTotalPages(),
                orderPage.getSize());

        return orderPage.map(orderMapper::toDTO);
    }
}
