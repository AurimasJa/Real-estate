package edu.ktu.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MenuItem;

public class FirstActivity extends AppCompatActivity {

    private Button myButton;
    private Button skelbimai;
    private Toolbar myToolbar;
    private TextView myTextView;
    private Button secondActivityButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        skelbimai = (Button) findViewById(R.id.button);
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
  //      myTextView = (TextView) findViewById(R.id.textView);

        skelbimai.setOnClickListener(skelbimaiClick);
        secondActivityButton.setOnClickListener(startSecondActivity);
        secondActivityButton.setOnLongClickListener(startSecondActivityLong);
    }

  /*  View.OnClickListener myButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String text = myTextView.getText() + "\n" + "Next line";
            myTextView.setText(text);
        }
    };*/
    View.OnClickListener skelbimaiClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runSkelbimaiActivity(true);
        }
    };
    public void runSkelbimaiActivity(boolean flag) {
        Intent intent = new Intent(context, Skelbimai.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }



    public void runSecondActivity(boolean flag) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    View.OnClickListener startSecondActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runSecondActivity(true);
        }
    };
// Laikant mygtuka suveikia po 1 sec, del LONGclick
    View.OnLongClickListener startSecondActivityLong = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            runSecondActivity(false);
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}


