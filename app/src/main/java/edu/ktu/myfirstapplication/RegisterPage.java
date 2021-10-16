package edu.ktu.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                addDatatoFirebase();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String repeatPsv = etRepeatPsw.getText().toString().trim();
                if(!validateUsername(username, password, repeatPsv)){
                    return;
                }else {
                    user.setId(id+1);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setUsername(username);
                    user.setRepeatPassword(repeatPsv);
                    //TODO: change into ID from username
                    //reff.child(String.valueOf(id+1)).setValue(user); //kiekviena karta suras kiek yra child parent klasei ir prides + 1 ir prides duomenis....
                    reff.child(username).setValue(user); //kiekviena karta suras kiek yra child parent klasei ir prides + 1 ir prides duomenis....

                    Intent intent = new Intent(context, FirstActivity.class);
                    intent.putExtra("flag", true);
                    context.startActivity(intent);
                }

            }
        });

    }

    private void addDatatoFirebase() {
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
                Toast.makeText(RegisterPage.this, "Kazkas ne taip " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateUsername(String username, String password, String repeatedPassword){
        if(username.isEmpty()){
            etUsername.setError("Laukelis negali būti tuščias.");
            return false;
        }else if (username.length() >= 15){
            etUsername.setError("Vartotojo vardas negali būti ilgesnis nei 15 simbolių.");
            return false;
        }else if(password.isEmpty()){
            etPassword.setError("Laukelis negali būti tuščias.");
            return false;
        }else if (password.length() >= 15 && password.length() <= 4){
            etPassword.setError("Vartotojo vardas negali būti ilgesnis nei 15 simbolių ir trumpesnis nei 4 simbolių.");
            return false;
        }else if(password.compareTo(repeatedPassword) != 0){
            etPassword.setError("Slaptažodžiai nesutampa.");
            return false;
        }else{
            return true;
        }
    }
}
