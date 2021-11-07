package edu.ktu.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog.Builder;

import java.util.Locale;

public class MainPageLoginRegister extends AppCompatActivity{

    private Button LoginButton;
    private Button RegisterButton;
    private Context context = this;
    private Button changeLang;
    private Button SkelbimaiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.mainloginpage);

        LoginButton = (Button) findViewById(R.id.button_login_main);
        RegisterButton = (Button) findViewById(R.id.button_register_main);
        SkelbimaiButton = (Button) findViewById(R.id.button_skelbimai_main);
        changeLang = (Button) findViewById(R.id.changeLang);

        LoginButton.setOnClickListener(Loginonclick);
        RegisterButton.setOnClickListener(Registeronclick);
        SkelbimaiButton.setOnClickListener(Skelbimaionclick);
        changeLang.setOnClickListener(changeLangOnClick);
    }

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

    View.OnClickListener Loginonclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runLoginActivity(true);
        }
    };
    public void runLoginActivity(boolean flag) {
        Intent intent = new Intent(context, LoginPage.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    View.OnClickListener Registeronclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runRegisterActivity(true);
        }
    };
    public void runRegisterActivity(boolean flag) {
        Intent intent = new Intent(context, RegisterPage.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    View.OnClickListener Skelbimaionclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runSkelbimaiActivity(true);
        }
    };
    public void runSkelbimaiActivity(boolean flag) {
        Intent intent = new Intent(context, skelbimailistviewnologinburger.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }
}
