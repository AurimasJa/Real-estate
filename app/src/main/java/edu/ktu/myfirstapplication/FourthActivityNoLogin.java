package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FourthActivityNoLogin extends AppCompatActivity {
    TextView name;
    TextView price;
    TextView desc;
    TextView kamb;
    ImageView img;

    //aaaaaaaaaa
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;

    TextView num;
    private Toolbar myToolbar;
    private Context context = this;
    //aaa
    ArrayList<SkelbimaiList> list;
    SkelbimaiListAdapter adapter;
    SkelbimaiList skelbimaiList;
    private HorizontalScrollView myHScrollView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //---------------------------------------------------------
        //String receivedTemplate = intent.getStringExtra("template");
        int template = intent.getIntExtra("template",0);
        if(template == 1){
            setContentView(R.layout.receiveractivitydesignnologin);
            //Toast.makeText(FourthActivityNoLogin.this, "Pirmas dizainas", Toast.LENGTH_LONG).show();
        }else if(template == 2){
            setContentView(R.layout.receiveractivitydesignnologin2);
            //Toast.makeText(FourthActivityNoLogin.this, "Antras dizainas", Toast.LENGTH_LONG).show();
        }else{
            setContentView(R.layout.receiveractivitydesignnologin3);
            //Toast.makeText(FourthActivityNoLogin.this, "Defaultinis dizainas", Toast.LENGTH_LONG).show();
        }
        //---------------------------------------------------------
        name = findViewById(R.id.textView3);
        price = findViewById(R.id.textView4);
        desc = findViewById(R.id.textView5);
        kamb = findViewById(R.id.textView6);
        //img = findViewById(R.id.imageView2);

        num = findViewById(R.id.textViewNum);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //aaaaaaaaa
        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView1.setBackgroundResource(R.drawable.animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) imageView1.getBackground();
        frameAnimation.start();


        myHScrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");

        //Intent intent = getIntent();
        String receivedName = intent.getStringExtra("pavadinimas");
        name.setText(receivedName);
        float kaina = intent.getFloatExtra("kaina",0);
        price.setText(kaina+"");
        String descr = intent.getStringExtra("descriptionas");
        desc.setText(descr);
        //img.setImageResource(intent.getIntExtra("nuotrauka", 0));
        int room = intent.getIntExtra("kambariai",0);
        kamb.setText(room+"");
        String numb = intent.getStringExtra("numeris");
        num.setText(numb);

        //aaaaaaaaa
        skelbimaiList = new SkelbimaiList();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");
        list = new ArrayList<>();
        adapter = new SkelbimaiListAdapter(this, list);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    skelbimaiList = dataSnapshot.getValue(SkelbimaiList.class);
                    list.add(skelbimaiList);
                }
                //TextView title = (TextView) myHScrollView.findViewById(R.id.title);
                //ImageView image = (ImageView) myHScrollView.findViewById(R.id.imageView);
                //TextView price = (TextView) myHScrollView.findViewById(R.id.price);
                //TextView room_count = (TextView) myHScrollView.findViewById(R.id.room_count);
                //ImageView image1 = (ImageView) myHScrollView.findViewById(R.id.image1);

                //title.setText(list.get(0).getTitle());
                //price.setText(list.get(0).getPrice()+"€");
                //room_count.setText(list.get(0).getRoom_count()+" kambariai");
                //Picasso.get().load(list.get(0).getImage().toString()).into(imageView1);
               // Picasso.get().load(list.get(1).getImage().toString()).into(imageView2);
               // Picasso.get().load(list.get(2).getImage().toString()).into(imageView3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(context, Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
