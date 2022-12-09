package com.ordering.system.orderapplicationservice.ports.output.repository;

import java.util.Optional;

import com.ordering.system.orderdomaincore.entity.Restaurant;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
