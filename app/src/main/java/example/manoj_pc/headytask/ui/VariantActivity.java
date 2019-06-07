package example.manoj_pc.headytask.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.adapter.VariantAdapter;
import example.manoj_pc.headytask.model.Data;
import example.manoj_pc.headytask.util.SharedPreferenceManager;

public class VariantActivity extends AppCompatActivity {

    private RecyclerView variantRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Data data;
    private int product_pos,cat_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Variant");
        variantRecyclerView = findViewById(R.id.recyclerView_variant);
        variantRecyclerView.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        variantRecyclerView.setLayoutManager(layoutManager);
        cat_pos = SharedPreferenceManager.readInteger(this,"cat_pos",-1);
        product_pos = SharedPreferenceManager.readInteger(this,"product_pos",-1);
        Gson gson= new Gson();
        data = gson.fromJson(SharedPreferenceManager.readString(VariantActivity.this,"response",""),Data.class);
        VariantAdapter variantAdapter = new VariantAdapter(R.layout.item_variant, data.getCategories().get(cat_pos).getProducts().get(product_pos).getVariants(),this);
        variantRecyclerView.setItemAnimator(new DefaultItemAnimator());
        variantRecyclerView.setAdapter(variantAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
