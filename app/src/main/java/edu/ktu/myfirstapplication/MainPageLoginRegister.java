package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainPageLoginRegister extends AppCompatActivity{

    private Button LoginButton;
    private Button RegisterButton;
    private Context context = this;

    private Button SkelbimaiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainloginpage);

        LoginButton = (Button) findViewById(R.id.button_login_main);
        RegisterButton = (Button) findViewById(R.id.button_register_main);
        SkelbimaiButton = (Button) findViewById(R.id.button_skelbimai_main);

        LoginButton.setOnClickListener(Loginonclick);
        RegisterButton.setOnClickListener(Registeronclick);
        SkelbimaiButton.setOnClickListener(Skelbimaionclick);
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
        Intent intent = new Intent(context, MainPageSkelbimaiNoLogin.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }
}
