package example.manoj_pc.headytask.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable
{
    private ArrayList<Categories> categories;

    private ArrayList<Rankings> rankings;

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ArrayList<Rankings> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Rankings> rankings) {
        this.rankings = rankings;
    }
}
