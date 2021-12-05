package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    EditText editText;
    EditText editPass11;
    EditText editPass12;
    private Context context = this;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Button button;
    User user = new User();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofilexml);
        Intent intent = getIntent();
        String d = intent.getStringExtra("usernameAS");
        editText = (EditText) findViewById(R.id.editEmailas);
        editPass11 = (EditText) findViewById(R.id.editPass);
        editPass12 = (EditText) findViewById(R.id.editPass1);
        button = (Button) findViewById(R.id.button2);
        //Toast.makeText(UserProfile.this, d, Toast.LENGTH_LONG).show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("User");

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = editText.getText().toString().trim();
                String password = editPass11.getText().toString().trim();
                String repeatPsv = editPass12.getText().toString().trim();
                if(!validate(password, repeatPsv,email)){
                    return;
                }else {
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setUsername(d);
                    user.setId(0);
                    user.setRepeatPassword(repeatPsv);
                    //TODO: change into ID from username
                    //reff.child(String.valueOf(id+1)).setValue(user); //kiekviena karta suras kiek yra child parent klasei ir prides + 1 ir prides duomenis....
                    reff.child(d).setValue(user); //kiekviena karta suras kiek yra child parent klasei ir prides + 1 ir prides duomenis....

                    //Toast.makeText(UserProfile.this, getText(R.string.succ), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FirstActivity.class);
                    intent.putExtra("flag", true);
                    intent.putExtra("usernameAS", d);
                    context.startActivity(intent);
                }

            }
        });
    }

    private Boolean validate(String password, String repeatedPassword, String email){
        if(password.isEmpty()){
            editPass11.setError(getText(R.string.empty));
            return false;
        }else if (password.length() >= 25 && password.length() <= 4){
            editPass11.setError(getText(R.string.longps));
            return false;
        }else if(password.compareTo(repeatedPassword) != 0){
            editPass11.setError(getText(R.string.pswnot));
            editPass12.setError(getText(R.string.pswnot));
            return false;
        }else if (email.isEmpty()){
            editText.setError(getText(R.string.empty));
            return false;
        }else if (!email.contains("@")){
            editText.setError(getText(R.string.inc));
            return false;
        }else{
            return true;
        }
    }
}
