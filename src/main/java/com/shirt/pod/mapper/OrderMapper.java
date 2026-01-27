package com.shirt.pod.mapper;

import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.Order;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class OrderMapper {
    public OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .shippingFee(order.getShippingFee())
                .recipientName(order.getRecipientName())
                .recipientPhone(order.getRecipientPhone())
                .shippingAddress(order.getShippingAddress())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .note(order.getNote())
                .userId(order.getUserId())
                .createdDate(order.getCreatedDate() != null ? order.getCreatedDate().atZone(ZoneId.systemDefault()).toLocalDateTime() : null)
                .build();
    }
}
