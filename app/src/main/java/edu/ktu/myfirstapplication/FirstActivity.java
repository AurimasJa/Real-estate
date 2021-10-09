package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    private Button myButton;
    private Button skelbimai;
    private TextView myTextView;
    private Button secondActivityButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        skelbimai = (Button) findViewById(R.id.button);
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
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
}


