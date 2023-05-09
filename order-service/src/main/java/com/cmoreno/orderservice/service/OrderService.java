package com.cmoreno.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cmoreno.orderservice.dto.OrderLineItemsDto;
import com.cmoreno.orderservice.dto.OrderRequest;
import com.cmoreno.orderservice.model.Order;
import com.cmoreno.orderservice.model.OrderLineItems;
import com.cmoreno.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDtoList().stream().map(this::mapToDTO)
                .toList();

        order.setOrderLineItemList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode()).build();
    }
}
