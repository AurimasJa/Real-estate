package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterPage extends AppCompatActivity{

    private Button Register;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);

        Register = (Button) findViewById(R.id.button_register_enter);

        Register.setOnClickListener(RegisterOnClickEnter);
    }

    View.OnClickListener RegisterOnClickEnter = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runRegisterEnterActivity(true);
        }
    };
    public void runRegisterEnterActivity(boolean flag) {
        Intent intent = new Intent(context, FirstActivity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }


}
