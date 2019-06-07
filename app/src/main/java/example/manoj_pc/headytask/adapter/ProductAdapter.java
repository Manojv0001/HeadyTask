package example.manoj_pc.headytask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.model.Products;
import example.manoj_pc.headytask.ui.VariantActivity;
import example.manoj_pc.headytask.util.SharedPreferenceManager;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private int listItemLayout;
    private ArrayList<Products> productsArrayList;
    private static Context ctx;
    public ProductAdapter(int layoutId, ArrayList<Products> productsArrayList,Context ctx) {
        listItemLayout = layoutId;
        this.productsArrayList = productsArrayList;
        this.ctx= ctx;
    }

    @Override
    public int getItemCount() {
        return productsArrayList == null ? 0 : productsArrayList.size();
    }


    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ProductAdapter.ViewHolder myViewHolder = new ProductAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int listPosition) {
        TextView textViewProductName = holder.textViewProductName;
        TextView textViewDate = holder.textViewDate;
        textViewProductName.setText(productsArrayList.get(listPosition).getName());

        Date time = null;
        try
        {
            time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.dd'Z'").parse(productsArrayList.get(listPosition).getDate());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        String date = new SimpleDateFormat("EEE, MMM d, yyyy").format(time);
        textViewDate.setText("Date: " + date);


    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewProductName,textViewDate;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewProductName = (TextView) itemView.findViewById(R.id.textView_product_name);
            textViewDate = (TextView) itemView.findViewById(R.id.textView_date);
        }
        @Override
        public void onClick(View view) {
            SharedPreferenceManager.writeInteger(ctx,"product_pos",getLayoutPosition());
            Intent intent = new Intent(ctx, VariantActivity.class);
            ctx.startActivity(intent);
        }
    }
}

