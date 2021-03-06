package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Skelbimo_pridejimas_template_choose extends AppCompatActivity {

    private Button template1, template2, template3;
    private String d;
    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skelbimo_pridejimas_template_choose);
        Intent intent = getIntent();

        d = intent.getStringExtra("usernameAS");
        //Toast.makeText(Skelbimo_pridejimas_template_choose.this, d, Toast.LENGTH_LONG).show();
        template1 = (Button) findViewById(R.id.button11);
        template2 = (Button) findViewById(R.id.button12);
        template3 = (Button) findViewById(R.id.button13);

        template1.setOnClickListener(start_first_design);
        template2.setOnClickListener(start_second_design);
        template3.setOnClickListener(start_third_design);
        template1.setOnLongClickListener(start_first);
        template2.setOnLongClickListener(start_second);
        template3.setOnLongClickListener(start_third);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }
    View.OnClickListener start_first_design = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent1 = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent1.putExtra("flag", true);
            intent1.putExtra("templateIs", 1);
            intent1.putExtra("usernameAS", d);
            startActivity(intent1);
        }
    };
    View.OnClickListener start_second_design = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent1.putExtra("flag", true);
            intent1.putExtra("templateIs", 2);
            intent1.putExtra("usernameAS", d);
            startActivity(intent1);
        }
    };
    View.OnClickListener start_third_design = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent1.putExtra("flag", true);
            intent1.putExtra("templateIs", 3);
            intent1.putExtra("usernameAS", d);
            startActivity(intent1);
        }
    };
            View.OnLongClickListener start_first = new View.OnLongClickListener() {
        public boolean onLongClick(View arg0) {
            Intent intent1 = new Intent(getApplicationContext(), DesignPreview.class);
            intent1.putExtra("flag", true);
            intent1.putExtra("templateIs", 1);
            intent1.putExtra("usernameAS", d);
            startActivity(intent1);
            return true;
        }
    };
        View.OnLongClickListener start_second = new View.OnLongClickListener() {
        public boolean onLongClick(View arg0) {
            Intent intent1 = new Intent(getApplicationContext(), DesignPreview.class);
            intent1.putExtra("flag", true);
            intent1.putExtra("templateIs", 2);
            intent1.putExtra("usernameAS", d);
            startActivity(intent1);
            return true;
        }
    };
        View.OnLongClickListener start_third = new View.OnLongClickListener() {
        public boolean onLongClick(View arg0) {
            Intent intent1 = new Intent(getApplicationContext(), DesignPreview.class);
            intent1.putExtra("flag", true);
            intent1.putExtra("templateIs", 3);
            intent1.putExtra("usernameAS", d);
            startActivity(intent1);
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
