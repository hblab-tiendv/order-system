package com.ordering.system.customerdataaccess.repository;

import java.util.UUID;

import com.ordering.system.customerdataaccess.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
    
}
