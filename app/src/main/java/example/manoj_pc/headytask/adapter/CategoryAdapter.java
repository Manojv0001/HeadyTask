package example.manoj_pc.headytask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.model.Categories;
import example.manoj_pc.headytask.ui.ProductActivity;
import example.manoj_pc.headytask.ui.SubCategoryActivity;
import example.manoj_pc.headytask.util.SharedPreferenceManager;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private int listItemLayout;
    private static ArrayList<Categories> categoriesArrayList;
    private static Context ctx;
    public CategoryAdapter(int layoutId, ArrayList<Categories> categoriesArrayList,Context ctx) {
        listItemLayout = layoutId;
        this.categoriesArrayList = categoriesArrayList;
        this.ctx= ctx;
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList == null ? 0 : categoriesArrayList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(categoriesArrayList.get(listPosition).getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.textView_category_name);
        }
        @Override
        public void onClick(View view) {
            SharedPreferenceManager.writeInteger(ctx,"cat_pos",getLayoutPosition());


            if(!categoriesArrayList.get(getLayoutPosition()).getChild_categories().isEmpty())
            {
                Intent  intent = new Intent(ctx, SubCategoryActivity.class);
                ctx.startActivity(intent);
            }
            else
            {
                Intent  intent = new Intent(ctx, ProductActivity.class);
                ctx.startActivity(intent);
            }


        }
    }
}
