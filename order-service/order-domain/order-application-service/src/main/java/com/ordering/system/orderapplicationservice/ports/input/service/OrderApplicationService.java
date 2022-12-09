package com.ordering.system.orderapplicationservice.ports.input.service;

import com.ordering.system.orderapplicationservice.dto.create.CreateOrderCommand;
import com.ordering.system.orderapplicationservice.dto.create.CreateOrderResponse;
import com.ordering.system.orderapplicationservice.dto.track.TrackOrderQuery;
import com.ordering.system.orderapplicationservice.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
