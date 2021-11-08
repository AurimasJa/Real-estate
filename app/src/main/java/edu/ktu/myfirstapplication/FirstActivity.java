package edu.ktu.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;

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
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//skelbimai isiminti, filtruoti, paieska

public class FirstActivity extends AppCompatActivity {

    private CardView myCardView;
    private ListView myListView;
    private Toolbar myToolbar;
    private Context context = this;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    ArrayList<SkelbimaiList> list;
    SkelbimaiListAdapter adapter;
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
                        startActivity(new Intent(getApplicationContext(), SkelbimaiListViewBurger.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        myCardView = (CardView) findViewById(R.id.cardView);
        myListView = (ListView) findViewById(R.id.skelbimulistview);
        skelbimaiList = new SkelbimaiList();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");
        list = new ArrayList<>();
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        adapter = new SkelbimaiListAdapter(this, list);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    skelbimaiList = dataSnapshot.getValue(SkelbimaiList.class);
                    list.add(skelbimaiList);
                }
                if(!list.isEmpty()){
                    RandomiseList();

                    TextView title = (TextView) myCardView.findViewById(R.id.title);
                    ImageView image = (ImageView) myCardView.findViewById(R.id.imageView);
                    TextView price = (TextView) myCardView.findViewById(R.id.price);
                    TextView room_count = (TextView) myCardView.findViewById(R.id.room_count);

                    title.setText(list.get(0).getTitle());
                    price.setText(list.get(0).getPrice()+"€");
                    room_count.setText(list.get(0).getRoom_count()+" kambariai");
                    Picasso.get().load(list.get(0).getImage().toString()).into(image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button buttonas = (Button) findViewById(R.id.button);
        buttonas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FourthActivityNoLogin.class);
                intent.putExtra("pavadinimas", list.get(0).getTitle());
                intent.putExtra("kaina", list.get(0).getPrice());
                intent.putExtra("descriptionas", list.get(0).getDescription());
                intent.putExtra("kambariai", list.get(0).getRoom_count());
                intent.putExtra("numeris", list.get(0).getPhoneNum());
                startActivity(intent);
            }
        });
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

    public void RandomiseList(){
        Collections.shuffle(list);
    }
}


