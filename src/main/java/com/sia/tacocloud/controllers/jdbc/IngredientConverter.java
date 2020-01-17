package com.sia.tacocloud.controllers.jdbc;

import com.sia.tacocloud.essences.jdbc.Ingredient;
import com.sia.tacocloud.repositories.jdbc.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    public IngredientConverter (IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
