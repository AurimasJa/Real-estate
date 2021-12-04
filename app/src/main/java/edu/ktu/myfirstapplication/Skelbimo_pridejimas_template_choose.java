package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class Skelbimo_pridejimas_template_choose extends AppCompatActivity {

    private Button template1, template2, template3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skelbimo_pridejimas_template_choose);

        template1 = (Button) findViewById(R.id.button11);
        template2 = (Button) findViewById(R.id.button12);
        template3 = (Button) findViewById(R.id.button13);

        template1.setOnClickListener(start_first_design);
        template2.setOnClickListener(start_second_design);
        template3.setOnClickListener(start_third_design);
    }
    View.OnClickListener start_first_design = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent.putExtra("flag", true);
            intent.putExtra("templateIs", 1);
            startActivity(intent);
        }
    };
    View.OnClickListener start_second_design = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent.putExtra("flag", true);
            intent.putExtra("templateIs", 2);
            startActivity(intent);
        }
    };
    View.OnClickListener start_third_design = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent.putExtra("flag", true);
            intent.putExtra("templateIs", 3);
            startActivity(intent);
        }
    };
}
