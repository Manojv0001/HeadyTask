package example.manoj_pc.headytask.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import java.util.ArrayList;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.adapter.SubCategoryAdapter;
import example.manoj_pc.headytask.model.Categories;
import example.manoj_pc.headytask.model.Data;
import example.manoj_pc.headytask.util.SharedPreferenceManager;

public class SubCategoryActivity extends AppCompatActivity
{

    private RecyclerView subCategoryRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private Data data;
    private int pos,subCategoryId;
    private ArrayList<Categories> categoriesArrayList;
    private ArrayList<Integer> categoryPosList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Sub Category");
        subCategoryRecyclerView = findViewById(R.id.recyclerView_sub_category);
        subCategoryRecyclerView.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        subCategoryRecyclerView.setLayoutManager(layoutManager);
        pos = SharedPreferenceManager.readInteger(this,"cat_pos",-1);
        Gson gson= new Gson();
        data = gson.fromJson(SharedPreferenceManager.readString(SubCategoryActivity.this,"response",""),Data.class);
        categoriesArrayList = new ArrayList<>();
        categoryPosList = new ArrayList<>();

        for(int i=0;i<data.getCategories().get(pos).getChild_categories().size();i++)
        {
            subCategoryId = data.getCategories().get(pos).getChild_categories().get(i);

            for(int j=0;j<data.getCategories().size();j++)
            {
                if(data.getCategories().get(j).getId()==subCategoryId)
                {
                    categoriesArrayList.add(data.getCategories().get(j));
                    categoryPosList.add(j);
                    break;
                }
            }

        }
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(R.layout.item_sub_category,categoriesArrayList,categoryPosList,this);
        subCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subCategoryRecyclerView.setAdapter(subCategoryAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}
