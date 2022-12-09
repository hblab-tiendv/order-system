package com.ordering.system.orderdataaccess.restaurant.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ordering.system.commondataaccess.entity.RestaurantEntity;
import com.ordering.system.commondataaccess.repository.RestaurantJpaRepository;
import com.ordering.system.orderapplicationservice.ports.output.repository.RestaurantRepository;
import com.ordering.system.orderdataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.ordering.system.orderdomaincore.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository,
                                    RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts =
                restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(),
                        restaurantProducts);
        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
