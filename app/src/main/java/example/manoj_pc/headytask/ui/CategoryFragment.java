package example.manoj_pc.headytask.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.adapter.CategoryAdapter;
import example.manoj_pc.headytask.model.Data;
import example.manoj_pc.headytask.util.SharedPreferenceManager;

public class CategoryFragment extends Fragment {

    private  View categoryFragmentView;
    private RecyclerView categoryRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private Data data;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context= context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        categoryFragmentView = inflater.inflate(R.layout.fragment_category, container, false);
        return  categoryFragmentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryRecyclerView = categoryFragmentView.findViewById(R.id.recylerView_category);
        categoryRecyclerView.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        Gson gson= new Gson();
        data = gson.fromJson(SharedPreferenceManager.readString(getActivity(),"response",""),Data.class);
        CategoryAdapter categoryAdapter = new CategoryAdapter(R.layout.item_category, data.getCategories(),getActivity());
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
}
