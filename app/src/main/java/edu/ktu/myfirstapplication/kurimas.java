package edu.ktu.myfirstapplication;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
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

public class kurimas extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button save;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bandau);
        etEmail=(EditText) findViewById(R.id.emailas);
        etPassword=(EditText) findViewById(R.id.passwordas);
        save = (Button) findViewById(R.id.button2);
        firebaseDatabase = FirebaseDatabase.getInstance();
        String key = firebaseDatabase.getReference("User").push().getKey();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("User").child(key);
        save.setOnClickListener(view -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                // if the text fields are empty
                // then show the below message.
                Toast.makeText(kurimas.this, "Please add some data.", Toast.LENGTH_SHORT).show();
            } else {
                // else call the method to add
                // data to our database.
                addDatatoFirebase(email, password);
            }
            Toast.makeText(kurimas.this,"Done ", Toast.LENGTH_SHORT).show();
        });

    }
    private void addDatatoFirebase(String email, String password) {
        // below 3 lines of code is used to set
        // data in our object class.
        user.setEmail(email);
        user.setPassword(password);

        // we are use add value event listener method
        // which is called with database reference.
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                reff.setValue(user);

                // after adding this data we are showing toast message.
                Toast.makeText(kurimas.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(kurimas.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
