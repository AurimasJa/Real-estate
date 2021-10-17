package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Skelbimo_pridejimas extends AppCompatActivity {

    Context context = this;
    Button add_btn;
    EditText edit_Title;
    EditText edit_Price;
    EditText edit_Desc;
    EditText edit_RoomCount;
    EditText edit_PhoneNumber;
    EditText edit_CreatedBy;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    SkelbimaiList skelbimas = new SkelbimaiList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skelbimo_pridejimas);

        add_btn = (Button) findViewById(R.id.btn_add_item);
        edit_Title = (EditText) findViewById(R.id.edit_Title);
        edit_Price = (EditText) findViewById(R.id.edit_Price);
        edit_Desc = (EditText) findViewById(R.id.edit_Description);
        edit_RoomCount = (EditText) findViewById(R.id.edit_RoomCount);
        edit_PhoneNumber = (EditText) findViewById(R.id.edit_PhoneNumber);
        edit_CreatedBy = (EditText) findViewById(R.id.edit_CreatedBy);
        add_btn.setOnClickListener(Add_OnClick);
    }


    View.OnClickListener Add_OnClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            //run_AddItem_Activity(true);
            String Title = edit_Title.getText().toString().trim();
            float Price = Float.parseFloat(edit_Price.getText().toString().trim());
            String Desc = edit_Desc.getText().toString().trim();
            int roomCount = Integer.parseInt(edit_RoomCount.getText().toString().trim());
            String PhoneNumber = edit_PhoneNumber.getText().toString().trim();
            String CreatedBy = edit_CreatedBy.getText().toString().trim();

            skelbimas.setTitle(Title);
            skelbimas.setPrice(Price);
            skelbimas.setDescription(Desc);
            skelbimas.setRoom_count(roomCount);
            skelbimas.setPhoneNum(PhoneNumber);
            skelbimas.setCreatedBy(CreatedBy);

            firebaseDatabase = FirebaseDatabase.getInstance();
            reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");

            //TODO: change into ID from username
            reff.child(Title).setValue(skelbimas); //kiekviena karta suras kiek yra child parent klasei ir prides + 1 ir prides duomenis....

            Intent intent = new Intent(context, SkelbimaiListView.class);
            //intent.putExtra("flag", true);
            startActivity(intent);
        }
    };

    /*public void run_AddItem_Activity(boolean flag) {
        Intent intent = new Intent(getApplicationContext(), SkelbimaiListView.class);
        intent.putExtra("title", edit_Title.getText().toString().trim());
        intent.putExtra("price", edit_Price.getText().toString().trim());
        intent.putExtra("desc", edit_Desc.getText().toString().trim());
        intent.putExtra("room", edit_RoomCount.getText().toString().trim());
        intent.putExtra("phone", edit_PhoneNumber.getText().toString().trim());
        getApplicationContext().startActivity(intent);
    }*/

}