package com.example.cookingproject.Model;

import java.util.List;

public class IngredientList {
        List<Ingredient> ingredients;
        public IngredientList(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }


}
