package com.ordering.system.orderdataaccess.restaurant.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ordering.system.commondataaccess.entity.RestaurantEntity;
import com.ordering.system.commondataaccess.exception.RestaurantDataAccessException;
import com.ordering.system.commondomain.valueobject.Money;
import com.ordering.system.commondomain.valueobject.ProductId;
import com.ordering.system.commondomain.valueobject.RestaurantId;
import com.ordering.system.orderdomaincore.entity.Product;
import com.ordering.system.orderdomaincore.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                        new Money(entity.getProductPrice()))).toList();

        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }
}
