package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.ktu.myfirstapplication.databinding.ActivitySkelbimasBinding;

public class SkelbimasActivity extends AppCompatActivity {

    ActivitySkelbimasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivitySkelbimasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String price = intent.getStringExtra("price");
            String place = intent.getStringExtra("place");
            String room_count = intent.getStringExtra("room_count");
            int imageId = intent.getIntExtra("imageId", R.drawable.house1);

            binding.nameProfile.setText(title);
            binding.phoneProfile.setText(description);
            binding.countryProfile.setText(price);
            binding.profileImage.setImageResource(imageId);




        }

    }



}
