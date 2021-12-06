package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FourthActivityNoLogin extends AppCompatActivity {
    TextView name;
    TextView price;
    TextView desc;
    TextView kamb;
    TextView pardavejas;
    TextView addressas;
    ImageView img;
    Button twitter;

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

    ImageButton arrow;
    LinearLayout hiddenView;
    CardView cardView;

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
        cardView = findViewById(R.id.base_cardview);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.hidden_view);

        name = findViewById(R.id.textView3);
        price = findViewById(R.id.textView4);
        desc = findViewById(R.id.textView5);
        kamb = findViewById(R.id.textView6);
        pardavejas = findViewById(R.id.textViewPardavejas1);
        addressas = findViewById(R.id.textViewAddress1);
        //img = findViewById(R.id.imageView2);

        num = findViewById(R.id.textViewNum);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //aaaaaaaaa
        if (template != 2){
            imageView1 = (ImageView) findViewById(R.id.image1);
            imageView1.setBackgroundResource(R.drawable.animation);
            AnimationDrawable frameAnimation = (AnimationDrawable) imageView1.getBackground();
            frameAnimation.start();
        }
        //imageView1 = (ImageView) findViewById(R.id.image1);
        //imageView1.setBackgroundResource(R.drawable.animation);
        //AnimationDrawable frameAnimation = (AnimationDrawable) imageView1.getBackground();
        //frameAnimation.start();


        myHScrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");

        //Intent intent = getIntent();
        String receivedName = intent.getStringExtra("pavadinimas");
        name.setText(receivedName);
        float kaina = intent.getFloatExtra("kaina",0);
        price.setText(kaina+" €");
        String descr = intent.getStringExtra("descriptionas");
        desc.setText(descr);
        String pard = intent.getStringExtra("pardavejas");
        pardavejas.setText(pard);
        //img.setImageResource(intent.getIntExtra("nuotrauka", 0));
        int room = intent.getIntExtra("kambariai",0);
        kamb.setText(room+"");
        String numb = intent.getStringExtra("numeris");
        num.setText(numb);
        String address = intent.getStringExtra("adresas");
        addressas.setText(address);

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

        if (template == 3 || template == 0){
            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // If the CardView is already expanded, set its visibility
                    //  to gone and change the expand less icon to expand more.
                    if (hiddenView.getVisibility() == View.VISIBLE) {

                        // The transition of the hiddenView is carried out
                        //  by the TransitionManager class.
                        // Here we use an object of the AutoTransition
                        // Class to create a default transition.
                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.GONE);
                        arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }

                    // If the CardView is not expanded, set its visibility
                    // to visible and change the expand more icon to expand less.
                    else {

                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
                    }
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbarmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.action_share) {
            Intent intent = getIntent();
            Intent sendIntent = new Intent();
            String receivedName = intent.getStringExtra("pavadinimas");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Dalinuosi skelbimu: " + receivedName);

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
