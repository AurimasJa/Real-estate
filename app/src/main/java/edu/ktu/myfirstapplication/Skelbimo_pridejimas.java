package edu.ktu.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Skelbimo_pridejimas extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    Context context = this;
    Button add_btn;
    EditText edit_Title;
    EditText edit_Price;
    EditText edit_Desc;
    EditText edit_RoomCount;
    EditText edit_PhoneNumber;
    EditText edit_CreatedBy;
    ImageView imageView;
    Button add_pht, choose_image;
    private Uri mImageUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    SkelbimaiList skelbimas = new SkelbimaiList();
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skelbimo_pridejimas);

        add_btn = (Button) findViewById(R.id.btn_add_item);
        add_pht = (Button) findViewById(R.id.btn_add_photo);
        choose_image = (Button) findViewById(R.id.btn_choose_img);
        imageView = (ImageView) findViewById(R.id.imageView2);
        edit_Title = (EditText) findViewById(R.id.edit_Title);
        edit_Price = (EditText) findViewById(R.id.edit_Price);
        edit_Desc = (EditText) findViewById(R.id.edit_Description);
        edit_RoomCount = (EditText) findViewById(R.id.edit_RoomCount);
        edit_PhoneNumber = (EditText) findViewById(R.id.edit_PhoneNumber);
        edit_CreatedBy = (EditText) findViewById(R.id.edit_CreatedBy);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Skelbimo_pridejimas.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavFirst);
        bottomNavigationView.setSelectedItemId(R.id.adv);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainpage:
                        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainPageLoginRegister.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.adv:
                        startActivity(new Intent(getApplicationContext(), SkelbimaiListView.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(imageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+ "." + getFileExtension(mImageUri));
            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageUrl = uri.toString();
                                            Toast.makeText(Skelbimo_pridejimas.this, imageUrl, Toast.LENGTH_LONG).show();
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
                                            skelbimas.setImage(imageUrl);

                                            reff.child(Title).setValue(skelbimas);
                                            Intent intent = new Intent(context, SkelbimaiListView.class);
                                            //intent.putExtra("flag", true);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Skelbimo_pridejimas.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}