package ggc.core;

import java.io.Serializable;
import java.util.List;

class Recipe implements Serializable {
    private static final long serialVersionUID = 5L;

    private List<Product> _components;
    private List<Integer> _quantities;

    Recipe(List<Product> components, List<Integer> quantities) {
        _components = components;
        _quantities = quantities;
    }

    public String toString() {
        String recipe = "";
        for(int i = 0; i < _components.size(); i += 2)
            recipe = String.join("#", recipe, _components.get(i).getId(), ":" + _quantities.get(i + 1));
        return recipe;
    }

}