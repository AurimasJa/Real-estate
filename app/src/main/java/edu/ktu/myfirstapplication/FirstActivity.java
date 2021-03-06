package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

//skelbimai isiminti, filtruoti, paieska

public class FirstActivity extends AppCompatActivity {

    private CardView myCardView;
    private ListView myListView;
    private Toolbar myToolbar;
    private Context context = this;
    Button maps2;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    ArrayList<SkelbimaiList> list, temp;
    SkelbimaiListAdapter adapter;
    SkelbimaiList skelbimaiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);
        Intent intent = getIntent();

        String d = intent.getStringExtra("usernameAS");
        //Toast.makeText(FirstActivity.this, d, Toast.LENGTH_LONG).show();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavFirst);
        bottomNavigationView.setSelectedItemId(R.id.mainpage);

        maps2 = (Button) findViewById(R.id.maps2);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainpage:
                        return true;
                    case R.id.settings:
                        Intent inte = (new Intent(getApplicationContext(), Settings.class));
                        inte.putExtra("usernameAS", d);
                        startActivity(inte);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainPageLoginRegister.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.adv:
                        Intent inte1 = (new Intent(getApplicationContext(), SkelbimaiListViewBurger.class));
                        inte1.putExtra("usernameAS", d);
                        startActivity(inte1);
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
                maps2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(FirstActivity.this, MapsActivity2new.class);
                        intent.putExtra("flag", true);
                        intent.putParcelableArrayListExtra("list", list);
                        startActivity(intent);
                    }
                });
                if(!list.isEmpty()){
                    RandomiseList();

                    TextView title = (TextView) myCardView.findViewById(R.id.title);
                    ImageView image = (ImageView) myCardView.findViewById(R.id.imageView);
                    TextView price = (TextView) myCardView.findViewById(R.id.price);
                    TextView room_count = (TextView) myCardView.findViewById(R.id.room_count);

                    title.setText(list.get(0).getTitle());
                    price.setText(list.get(0).getPrice()+"???");
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
                intent.putExtra("pardavejas", list.get(0).getCreatedBy());
                intent.putExtra("adresas", list.get(0).getLocation());
                intent.putExtra("template", list.get(0).getTemplate());
                startActivity(intent);
            }
        });
//        Toast.makeText(FirstActivity.this, String(temp.size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void RandomiseList(){
        Collections.shuffle(list);
    }
}


