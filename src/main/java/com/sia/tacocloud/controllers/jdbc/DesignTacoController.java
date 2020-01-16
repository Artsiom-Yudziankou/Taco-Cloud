package com.sia.tacocloud.controllers.jdbc;


import com.sia.tacocloud.essences.jdbc.Ingredient;
import com.sia.tacocloud.essences.jdbc.Ingredient.Type;
import com.sia.tacocloud.essences.jdbc.Order;
import com.sia.tacocloud.essences.jdbc.Taco;
import com.sia.tacocloud.repositories.jdbc.IngredientRepository;
import com.sia.tacocloud.repositories.jdbc.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.
                stream().
                filter(x -> x.getType().equals(type)).
                collect(Collectors.toList());
    }

    @ModelAttribute(name="order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name="taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String proccessDesign (@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

        if (errors.hasErrors())
            return "design";

        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);

        return "redirect:/orders/current";
    }
}
