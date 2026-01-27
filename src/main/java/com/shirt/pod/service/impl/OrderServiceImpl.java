package com.shirt.pod.service.impl;

import com.shirt.pod.mapper.OrderMapper;
import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.Order;
import com.shirt.pod.model.entity.enums.OrderStatus;
import com.shirt.pod.repository.OrderRepository;
import com.shirt.pod.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getOrders(OrderStatus status) {
        log.info("Fetching orders with status: {}", status);
        List<Order> orders;
        if (status != null) {
            orders = orderRepository.findByStatus(status);
        } else {
            orders = orderRepository.findAll();
        }
        log.info("Found {} orders", orders.size());
        return orderMapper.toDTOList(orders);
    }
}
