package example.manoj_pc.headytask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.model.Variants;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.ViewHolder> {

    private int listItemLayout;
    private ArrayList<Variants> variantsArrayList;
    private static Context ctx;
    public VariantAdapter(int layoutId, ArrayList<Variants> variantsArrayList,Context ctx) {
        listItemLayout = layoutId;
        this.variantsArrayList = variantsArrayList;
        this.ctx= ctx;
    }

    @Override
    public int getItemCount() {
        return variantsArrayList == null ? 0 : variantsArrayList.size();
    }


    @Override
    public VariantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        VariantAdapter.ViewHolder myViewHolder = new VariantAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final VariantAdapter.ViewHolder holder, final int listPosition) {
        TextView itemColor = holder.textViewColor;
        TextView itemSize = holder.textViewSize;
        TextView itemPrice = holder.textViewPrice;
        itemColor.setText("Color: "+variantsArrayList.get(listPosition).getColor());
        itemSize.setText("Size: "+variantsArrayList.get(listPosition).getSize());
        itemPrice.setText("Price: "+variantsArrayList.get(listPosition).getPrice());


    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView textViewSize,textViewColor,textViewPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewColor = (TextView) itemView.findViewById(R.id.textView_color);
            textViewSize = (TextView) itemView.findViewById(R.id.textView_size);
            textViewPrice = (TextView) itemView.findViewById(R.id.textView_price);

        }

    }
}

