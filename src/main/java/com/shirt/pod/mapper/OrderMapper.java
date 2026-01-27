package com.shirt.pod.mapper;

import com.shirt.pod.model.dto.response.OrderDTO;
import com.shirt.pod.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "createdDate", expression = "java(order.getCreatedDate() != null ? " +
            "order.getCreatedDate().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null)")
    OrderDTO toDTO(Order order);

    List<OrderDTO> toDTOList(List<Order> orders);
}
