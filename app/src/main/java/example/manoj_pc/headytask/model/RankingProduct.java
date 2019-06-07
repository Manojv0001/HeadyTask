package example.manoj_pc.headytask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RankingProduct implements Serializable
{
    private int id;

    @SerializedName(value = "count",alternate = {"view_count","order_count","shares"})
    private  int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
