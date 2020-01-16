package com.sia.tacocloud.repositories.jdbc;

import com.sia.tacocloud.essences.jdbc.Order;

public interface OrderRepository {
    Order save(Order order);
}
