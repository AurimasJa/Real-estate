package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FourthActivityNoLogin extends AppCompatActivity {
    TextView name;
    TextView price;
    TextView desc;
    TextView kamb;
    ImageView img;

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
        //img = findViewById(R.id.imageView2);

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
