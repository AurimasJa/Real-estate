package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FourthActivityNoLogin extends AppCompatActivity {
    TextView name;
    TextView price;
    TextView desc;
    TextView kamb;
    ImageView img;
    ImageView img1;
    ImageView img2;

    TextView num;
    private Toolbar myToolbar;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiveractivitydesignnologin);
        name = findViewById(R.id.textView3);
        price = findViewById(R.id.textView4);
        desc = findViewById(R.id.textView5);
        kamb = findViewById(R.id.textView6);
        img = (ImageView) findViewById(R.id.imageView10);
        img1 = (ImageView) findViewById(R.id.imageView11);
        img2 = (ImageView) findViewById(R.id.imageView12);

        num = findViewById(R.id.textViewNum);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
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
        String image1url = intent.getStringExtra("nuotrauka1");
        Toast.makeText(FourthActivityNoLogin.this, image1url, Toast.LENGTH_LONG).show();
        Picasso.get().load(image1url.toString()).into(img);
        String image2url = intent.getStringExtra("nuotrauka0");
        Picasso.get().load(image2url).into(img1);
        String image3url = intent.getStringExtra("nuotrauka2");
        Picasso.get().load(image3url).into(img2);

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
