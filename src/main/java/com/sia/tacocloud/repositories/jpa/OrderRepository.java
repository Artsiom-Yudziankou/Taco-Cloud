package com.sia.tacocloud.repositories.jpa;

import com.sia.tacocloud.essences.jpa.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
