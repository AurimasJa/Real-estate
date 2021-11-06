package edu.ktu.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.view.menu.MenuView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FirstActivity extends AppCompatActivity {

    private Button myButton;
    private Button skelbimai;
    private Toolbar myToolbar;
    private TextView link, username;
    private Button secondActivityButton;
    private Button logout;
    private Context context = this;
    TextView name;
    TextView price;
    TextView desc;
    TextView kamb;

    SkelbimaiListAdapter adapter;
    TextView num;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    ArrayList<SkelbimaiList> list;
    SkelbimaiList skelbimaiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavFirst);
        bottomNavigationView.setSelectedItemId(R.id.mainpage);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainpage:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainPageLoginRegister.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.adv:
                        startActivity(new Intent(getApplicationContext(), SkelbimaiListView.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        name = findViewById(R.id.textView3);
        price = findViewById(R.id.textView4);
        desc = findViewById(R.id.textView5);
        kamb = findViewById(R.id.textView6);
        num = findViewById(R.id.textViewNum);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        skelbimaiList = new SkelbimaiList();

        list = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    skelbimaiList = dataSnapshot.getValue(SkelbimaiList.class);
                    list.add(skelbimaiList);
                }
                if(!list.isEmpty()){
                    showRandomAdv();
                }else{
                    name.setVisibility(View.GONE);
                    price.setVisibility(View.GONE);
                    desc.setVisibility(View.GONE);
                    num.setVisibility(View.GONE);
                    kamb.setVisibility(View.GONE);
                }

                //Toast.makeText(FirstActivity.this, list.size(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new SkelbimaiListAdapter(this, list);
       // adapter.addAll(list);
        //adapter.addAll(list);
        //showRandomAdv();

        //Toast.makeText(FirstActivity.this, adapter.getCount(), Toast.LENGTH_LONG).show();

       // name.setText(""+list.size());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(context, Settings.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void showRandomAdv(){
        RandomiseList();
        name.setText(list.get(0).getTitle());
        desc.setText(list.get(0).getDescription());
        price.setText(list.get(0).getPrice()+"");
        num.setText(list.get(0).getPhoneNum());
        kamb.setText(list.get(0).getRoom_count()+"");
        //Toast.makeText(FirstActivity.this, a, Toast.LENGTH_LONG).show();
    }

    public void RandomiseList(){
        Collections.shuffle(list);
    }


}


