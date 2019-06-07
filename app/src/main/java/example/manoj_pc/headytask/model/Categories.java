package example.manoj_pc.headytask.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable
{
    private int id;
    private String name;

    private ArrayList<Products> products;

    private ArrayList<Integer> child_categories;

    public ArrayList<Integer> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(ArrayList<Integer> child_categories) {
        this.child_categories = child_categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }
}
