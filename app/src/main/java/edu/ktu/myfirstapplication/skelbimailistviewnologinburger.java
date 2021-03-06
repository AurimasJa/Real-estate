package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class skelbimailistviewnologinburger extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView myListView;
    private Button sortASC,sortDES,sortPriceB, sortPriceS, filterbutton;
    private Button add_item;
    private Toolbar myToolbar;
    private Context context = this;
    private TextView vienas, du;
    private EditText filterprice1,filterprice2;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    ArrayList<SkelbimaiList> list;
    SkelbimaiListAdapter adapter;
    SkelbimaiList skelbimaiList;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skelbimailistviewnologinburger);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");
        list = new ArrayList<>();
        adapter = new SkelbimaiListAdapter(this, list);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    skelbimaiList = dataSnapshot.getValue(SkelbimaiList.class);
                    list.add(skelbimaiList);
                }
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.my_toolbar);
        //tool bar
        setSupportActionBar(toolbar);
        //nav bar
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);

        myListView = (ListView) findViewById(R.id.skelbimulistview);
        skelbimaiList = new SkelbimaiList();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FourthActivityNoLogin.class);
                intent.putExtra("pavadinimas", list.get(i).getTitle());
                intent.putExtra("kaina", list.get(i).getPrice());
                intent.putExtra("descriptionas", list.get(i).getDescription());
                //intent.putExtra("nuotrauka", list.get(i).getImageId());
                intent.putExtra("kambariai", list.get(i).getRoom_count());
                intent.putExtra("numeris", list.get(i).getPhoneNum());
                intent.putExtra("template", list.get(i).getTemplate());
                intent.putExtra("adresas", list.get(i).getLocation());
                intent.putExtra("pardavejas", list.get(i).getCreatedBy());
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_filterByName1:
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        return skelbimaiList.getTitle().compareTo(t1.getTitle());
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case R.id.nav_filterByName2:
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        return skelbimaiList.getTitle().compareTo(t1.getTitle());
                    }
                });
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
                break;
            case R.id.nav_filterByNumb1:
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        float price1 = skelbimaiList.getPrice();
                        float price2 = t1.getPrice();

                        return Float.compare(price1, price2);
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case R.id.nav_filterByNumb2:
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        float price1 = skelbimaiList.getPrice();
                        float price2 = t1.getPrice();

                        return Float.compare(price1, price2);
                    }
                });
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
                break;
            case R.id.nav_filter:
                MenuItem menuItem1 = navigationView.getMenu().findItem( R.id.nav_text1 );
                EditText textView1 = (EditText) menuItem1.getActionView();
                MenuItem menuItem2 = navigationView.getMenu().findItem( R.id.nav_text2 );
                EditText textView2 = (EditText) menuItem2.getActionView();

                ArrayList<SkelbimaiList> filteredList = new ArrayList<>();
                    float price1 = Float.valueOf(textView1.getText().toString());
                    float price2 = Float.valueOf(textView2.getText().toString());

                    for (SkelbimaiList listas : list) {

                    //    Toast.makeText(skelbimailistviewnologinburger.this, listas.getPrice() + "   -   " + price1, Toast.LENGTH_LONG ).show();
                        if (listas.getPrice() >= price1 && listas.getPrice() <= price2) {
                            filteredList.add(listas);
                            //Toast.makeText(SkelbimaiListView.this, listas.getPrice() + "   -   " + price1, Toast.LENGTH_LONG ).show();
                        }
                    }
                    adapter = new SkelbimaiListAdapter(skelbimailistviewnologinburger.this, filteredList);
                    myListView.setAdapter(adapter);

                DrawerLayout mDrawerLayout;
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();
                break;
        }
        return true;
    }
}

