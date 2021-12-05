package edu.ktu.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.view.menu.MenuView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    private Toolbar myToolbar;
    private Button changeLang;
    private Button editProfile;
    private Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.settingspage);
        Intent intent = getIntent();

        String d = intent.getStringExtra("usernameAS");

        //Toast.makeText(Settings.this, d, Toast.LENGTH_LONG).show();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavFirst);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        changeLang = (Button) findViewById(R.id.changeLang);
        editProfile = (Button) findViewById(R.id.profileEdit);
        changeLang.setOnClickListener(changeLangOnClick);
        editProfile.setOnClickListener(editProfile1);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainpage:
                        Intent inte1 = (new Intent(getApplicationContext(), FirstActivity.class));
                        inte1.putExtra("usernameAS", d);
                        startActivity(inte1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainPageLoginRegister.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.adv:
                        Intent inte = (new Intent(getApplicationContext(), SkelbimaiListViewBurger.class));
                        inte.putExtra("usernameAS", d);
                        startActivity(inte);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
    View.OnClickListener editProfile1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent inte1 = (new Intent(getApplicationContext(), UserProfile.class));
            Intent intent = getIntent();
            String d = intent.getStringExtra("usernameAS");
            inte1.putExtra("usernameAS", d);
            startActivity(inte1);
        }
    };
    View.OnClickListener changeLangOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            showChangeLang();
        }
    };

    private void showChangeLang()
    {
        String[] listItems = {"Lietuva", "US", "France"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        //mBuilder.setTitle("Pasirinkite kalbą...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0)
                {
                    setLocale("lt");
                    recreate();
                }
                else if(i == 1)
                {
                    setLocale("en");
                    recreate();
                }
                else if(i == 2)
                {
                    setLocale("fr");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String en) {
        Locale locale = new Locale(en);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", en);
        editor.apply();
    }

    private void loadLocale()
    {
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
