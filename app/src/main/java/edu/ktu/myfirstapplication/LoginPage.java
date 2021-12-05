package edu.ktu.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity{

    private Button Login;
    private EditText etUsername, etPassword;
    private Context context = this;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        etUsername = (EditText) findViewById(R.id.editUsernameLogin);
        etPassword = (EditText) findViewById(R.id.editPasswordLogin);
        Login = (Button) findViewById(R.id.login_login_enter);

        // database...
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("User");
        Login.setOnClickListener(LoginOnClick);
    }

    View.OnClickListener LoginOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            userIsCorrect();
            //runLoginEnterActivity(true);
        }
    };
    public void runLoginEnterActivity(boolean flag) {
        Intent intent = new Intent(context, FirstActivity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    private void userIsCorrect(){
        String userEdUsername = etUsername.getText().toString().trim();
        String userEdPassw = etPassword.getText().toString().trim();



        Query finder = reff.orderByChild("username").equalTo(userEdUsername);
        finder.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!validate(userEdUsername, userEdPassw)){
                    return;
                }else{
                    if(snapshot.exists()){
                        String password = snapshot.child(userEdUsername).child("password").getValue(String.class);
                        if(password.equals(userEdPassw)){
                            String usernameDB = snapshot.child(userEdUsername).child("username").getValue(String.class);
                            String emailDB = snapshot.child(userEdUsername).child("email").getValue(String.class);
                            //Toast.makeText(LoginPage.this, "Sitas " + userEdPassw + " " + userEdUsername, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                            intent.putExtra("usernameAS", usernameDB);
                            intent.putExtra("emailAS", emailDB);

                            Toast.makeText(LoginPage.this, getText(R.string.loginSuc), Toast.LENGTH_SHORT).show();

                            startActivity(intent);
                        }
                        else{
                            etPassword.setError(getText(R.string.incorrectPassword));
                        }
                    }else {
                        etUsername.setError(getText(R.string.NoUser));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validate(String username, String password){
        if(username.isEmpty()){
            etUsername.setError(getText(R.string.empty));
            return false;
        }else if(password.isEmpty()){
            etPassword.setError(getText(R.string.empty));
            return false;
        }else{
            return true;
        }
    }

}
