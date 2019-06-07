package example.manoj_pc.headytask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.model.Categories;
import example.manoj_pc.headytask.model.RankingProduct;

public class RankingProductAdapter extends RecyclerView.Adapter<RankingProductAdapter.ViewHolder> {

    private int listItemLayout;
    private ArrayList<RankingProduct> rankingProductArrayList;
    private ArrayList<Categories> categoriesArrayList;
    private static Context ctx;
    private String type;
    public RankingProductAdapter(int layoutId, String type,ArrayList<RankingProduct> rankingProductArrayList,ArrayList<Categories> categoriesArrayList,Context ctx) {
        listItemLayout = layoutId;
        this.rankingProductArrayList = rankingProductArrayList;
        this.categoriesArrayList=categoriesArrayList;
        this.ctx= ctx;
        this.type=type;
    }

    @Override
    public int getItemCount() {
        return rankingProductArrayList == null ? 0 : rankingProductArrayList.size();
    }


    @Override
    public RankingProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        RankingProductAdapter.ViewHolder myViewHolder = new RankingProductAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RankingProductAdapter.ViewHolder holder, final int listPosition) {
        TextView textViewProductName = holder.textViewProductName;
        TextView textViewProductCount = holder.textViewCount;
        String name="";
        int id;
        id= rankingProductArrayList.get(listPosition).getId();
          for(int i=0;i<categoriesArrayList.size();i++)
          {
              for(int j=0;j<categoriesArrayList.get(i).getProducts().size();j++)
              {
                  if(id==categoriesArrayList.get(i).getProducts().get(j).getId())
                  {
                      name = categoriesArrayList.get(i).getProducts().get(j).getName();
                      textViewProductName.setText(name);
                  }

              }

          }


         if(type.equals("0"))
          textViewProductCount.setText("View Count : "+rankingProductArrayList.get(listPosition).getCount());
          else if(type.equals("1"))
             textViewProductCount.setText("Order Count : "+rankingProductArrayList.get(listPosition).getCount());
          else
             textViewProductCount.setText("Shares : "+rankingProductArrayList.get(listPosition).getCount());




    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewProductName,textViewCount;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewProductName = (TextView) itemView.findViewById(R.id.textView_ranking_product_name);
            textViewCount = (TextView) itemView.findViewById(R.id.textView_ranking_product_count);
        }

    }
}


