package com.sia.tacocloud.repositories.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sia.tacocloud.essences.jdbc.Order;
import com.sia.tacocloud.essences.jdbc.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;

    private SimpleJdbcInsert orderTacoInserter;

    private ObjectMapper objectMapper;


    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter =
                new SimpleJdbcInsert(jdbc).
                        withTableName("Taco_Order").
                        usingGeneratedKeyColumns("id");

        this.orderTacoInserter =
                new SimpleJdbcInsert(jdbc).
                        withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        Long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacoList();

        for (Taco taco :
                tacos) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }


    private Long saveOrderDetails(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placeAt", order.getPlacedAt());

        Long orderId = orderInserter.executeAndReturnKey(values).longValue();
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, Long orderId ) {
        Map <String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
}