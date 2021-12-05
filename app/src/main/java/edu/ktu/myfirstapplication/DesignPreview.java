package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DesignPreview extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //---------------------------------------------------------
        String receivedTemplate = intent.getStringExtra("templateIs");
        int template = intent.getIntExtra("templateIs", 0);
        if (template == 1) {
            setContentView(R.layout.moderndesign);
            Toast.makeText(DesignPreview.this, "modern dizainas", Toast.LENGTH_LONG).show();
        } else if (template == 2) {
            setContentView(R.layout.olddesign);
            Toast.makeText(DesignPreview.this, "old dizainas", Toast.LENGTH_LONG).show();
        } else {
            setContentView(R.layout.defaultdesign);
            Toast.makeText(DesignPreview.this, "default dizainas", Toast.LENGTH_LONG).show();
        }
    }
}
