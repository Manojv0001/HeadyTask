package example.manoj_pc.headytask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import example.manoj_pc.headytask.R;
import example.manoj_pc.headytask.model.Data;
import example.manoj_pc.headytask.util.SharedPreferenceManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String mJSONURLString = "https://stark-spire-93433.herokuapp.com/json";
    private Data data;
    private FragmentManager fragmentManager;


    @Override
    protected void onStart() {
        super.onStart();
        fetchData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSetUpUi();
    }

    public void initSetUpUi()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public void setUpFragment()
    {
       fragmentManager = getSupportFragmentManager();
       fragmentManager.beginTransaction().replace(R.id.content_frame,new CategoryFragment(),"Category").addToBackStack("category").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void addMenuItemInNavMenuDrawer() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        Menu submenu = menu.addSubMenu("Rankings");

        for(int i=0;i<data.getRankings().size();i++)
        {
            final String name= data.getRankings().get(i).getRanking();
            final int pos = i;
            submenu.add(data.getRankings().get(i).getRanking()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    SharedPreferenceManager.writeString(MainActivity.this,"type",""+pos);
                    SharedPreferenceManager.writeString(MainActivity.this,"type_name",name);
                    Intent intent = new Intent(MainActivity.this,RankingProductActivity.class);
                    startActivity(intent);
                    return false;
                }
            });

        }
        navView.invalidate();
    }


    private void fetchData()
    {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            data= new Data();
                            data = gson.fromJson(response.toString(),Data.class);
                            String responseString = gson.toJson(data);
                            SharedPreferenceManager.writeString(MainActivity.this,"response",responseString);
                            addMenuItemInNavMenuDrawer();
                            setUpFragment();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }
}
