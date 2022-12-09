package com.ordering.system.commondataaccess.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ordering.system.commondataaccess.entity.RestaurantEntity;
import com.ordering.system.commondataaccess.entity.RestaurantEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {

    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}
