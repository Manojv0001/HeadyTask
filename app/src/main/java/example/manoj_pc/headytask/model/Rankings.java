package example.manoj_pc.headytask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Rankings implements Serializable
{

    private String ranking;

    @SerializedName("products")
    private ArrayList<RankingProduct> rankingProducts;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public ArrayList<RankingProduct> getRankingProducts() {
        return rankingProducts;
    }

    public void setRankingProducts(ArrayList<RankingProduct> rankingProducts) {
        this.rankingProducts = rankingProducts;
    }
}
