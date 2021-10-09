package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity{

    private Button Login;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        Login = (Button) findViewById(R.id.login_login_enter);

        Login.setOnClickListener(LoginOnClick);
    }

    View.OnClickListener LoginOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runLoginEnterActivity(true);
        }
    };
    public void runLoginEnterActivity(boolean flag) {
        Intent intent = new Intent(context, FirstActivity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }



}
