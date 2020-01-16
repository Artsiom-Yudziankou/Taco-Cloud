package com.sia.tacocloud.repositories.jdbc;

import com.sia.tacocloud.essences.jdbc.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
