package edu.ktu.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.view.menu.MenuView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MenuItem;

public class FirstActivity extends AppCompatActivity {

    private Button myButton;
    private Button skelbimai;
    private Toolbar myToolbar;
    private TextView link, username;
    private Button secondActivityButton;
    private Button logout;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);
        Intent intent = getIntent();
        skelbimai = (Button) findViewById(R.id.button);
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        logout = (Button) findViewById(R.id.logoutButton);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        username = (TextView) findViewById(R.id.usernameCall);
        username.setText("Sveiki, " + intent.getStringExtra("usernameAS") + "!");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        link = (TextView) findViewById(R.id.link);
        String linkText = "Visit this <a href='https://google.com'>Google</a> web page.";
        link.setText(Html.fromHtml(linkText));
        link.setMovementMethod(LinkMovementMethod.getInstance());

        skelbimai.setOnClickListener(skelbimaiClick);
        secondActivityButton.setOnClickListener(startSecondActivity);
        secondActivityButton.setOnLongClickListener(startSecondActivityLong);
        logout.setOnClickListener(logoutClick);
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
        Intent intent = new Intent(context, SkelbimaiListView.class);
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

    View.OnClickListener logoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runLogoutActivity(true);
        }
    };
    public void runLogoutActivity(boolean flag) {
        Intent intent = new Intent(context, MainPageLoginRegister.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(context, Settings.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbarmenu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }*/
}


