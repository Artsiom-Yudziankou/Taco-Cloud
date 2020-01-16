package com.sia.tacocloud.repositories.jdbc;

import com.sia.tacocloud.essences.jdbc.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
