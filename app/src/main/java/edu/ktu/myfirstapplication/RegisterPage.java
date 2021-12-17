package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity{

    private Button Register;
    private Context context = this;
    EditText etEmail, etPassword, etUsername, etRepeatPsw;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    long id = 0;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        //surandam id pagal .xml sio failo
        etEmail=(EditText) findViewById(R.id.emailRegister);
        etPassword=(EditText) findViewById(R.id.passwordRegister);
        etRepeatPsw=(EditText) findViewById(R.id.registerPasswordRepeat);
        etUsername=(EditText) findViewById(R.id.usernameRegister);
        //surandam id pagal .xml sio failo
        Register = (Button) findViewById(R.id.button_register_enter);
        // database...
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("User");

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkIdCounter();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String repeatPsv = etRepeatPsw.getText().toString().trim();
                if(!validate(username, password, repeatPsv,email)){
                    return;
                }else {
                    user.setId(id+1);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setUsername(username);
                    user.setRepeatPassword(repeatPsv);
                    reff.child(username).setValue(user);
                    Toast.makeText(RegisterPage.this, getText(R.string.succ), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginPage.class);
                    intent.putExtra("flag", true);
                    context.startActivity(intent);
                }

            }
        });

    }

    private void checkIdCounter() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //pridedam i firebase database, kuria nurodem auksciau
                if(snapshot.exists())
                {
                    id = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //jei data nebuvo prideta arba buvo atsaukta ismes fail teksta
                Toast.makeText(RegisterPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validate(String username, String password, String repeatedPassword, String email){
        if(username.isEmpty()){
            etUsername.setError(getText(R.string.empty));
            return false;
        }else if (username.length() >= 15){
            etUsername.setError(getText(R.string.small));
            return false;
        }else if(password.isEmpty()){
            etPassword.setError(getText(R.string.empty));
            return false;
        }else if (password.length() >= 25 && password.length() <= 4){
            etPassword.setError(getText(R.string.longps));
            return false;
        }else if(password.compareTo(repeatedPassword) != 0){
            etPassword.setError(getText(R.string.pswnot));
            etRepeatPsw.setError(getText(R.string.pswnot));
            return false;
        }else if (email.isEmpty()){
            etEmail.setError(getText(R.string.empty));
            return false;
        }else if (!email.contains("@")){
            etEmail.setError(getText(R.string.inc));
            return false;
        }else{
            return true;
        }
    }
}
