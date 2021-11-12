package ggc.core;

class AggregateProduct extends Product {
    private double _aggravation;

    private Recipe _recipe;

    // similar to its superclass
    AggregateProduct(String productId, double aggravation, Recipe recipe) {
        super(productId);
        _aggravation = aggravation;
        _recipe = recipe;
    }

    // for external representation of instance ( productID|price|stock|recipe )
    public String toString() {
        return super.toString() +  '|' + _recipe.toString();
    }
}
