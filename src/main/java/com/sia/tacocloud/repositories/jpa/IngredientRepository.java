package com.sia.tacocloud.repositories.jpa;

import com.sia.tacocloud.essences.jpa.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
