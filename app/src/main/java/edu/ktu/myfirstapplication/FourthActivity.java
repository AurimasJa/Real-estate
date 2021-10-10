package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {
    TextView name;
    TextView price;
    TextView desc;
    TextView kamb;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiveractivitydesign);
        name = findViewById(R.id.textView3);
        price = findViewById(R.id.textView4);
        desc = findViewById(R.id.textView5);
        kamb = findViewById(R.id.textView6);
        img = findViewById(R.id.imageView2);
        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("pavadinimas");
        name.setText(receivedName);
        float kaina = intent.getFloatExtra("kaina",0);
        price.setText(kaina+"");
        String descr = intent.getStringExtra("descriptionas");
        desc.setText(descr);
        img.setImageResource(intent.getIntExtra("nuotrauka", 0));
        int room = intent.getIntExtra("kambariai",0);
        kamb.setText(room+"");

        //enable back Button
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //getting back to listview
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
