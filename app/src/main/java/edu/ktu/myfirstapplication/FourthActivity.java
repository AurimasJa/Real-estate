package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

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

        //uzd
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //enable back Button
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //getting back to listview
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

    //uzd
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = getIntent();

                return true;

            case R.id.action_search:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbarmenu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }
}
