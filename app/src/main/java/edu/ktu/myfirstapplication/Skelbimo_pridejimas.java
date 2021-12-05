package edu.ktu.myfirstapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Skelbimo_pridejimas extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String CHANNEL_ID = "channelForNotification";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private int count = 0;
    String currentPhotoPath;
    Context context = this;
    Button add_btn;
    EditText edit_Title;
    EditText edit_Price;
    EditText edit_Desc;
    EditText edit_RoomCount;
    EditText edit_PhoneNumber;
    EditText edit_CreatedBy;
    ImageView imageView;

    //private ProgressBar mProgressBar;
    Button choose_image;
    private Uri mImageUri;
    private Uri contentUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    SkelbimaiList skelbimas = new SkelbimaiList();
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skelbimo_pridejimas);
        Intent intent = getIntent();

        String d = intent.getStringExtra("usernameAS");

        //Toast.makeText(Skelbimo_pridejimas.this, d, Toast.LENGTH_LONG).show();
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
        add_btn = (Button) findViewById(R.id.btn_add_item);
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
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
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
                    Toast.makeText(Skelbimo_pridejimas.this, getText(R.string.upload), Toast.LENGTH_SHORT).show();
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
                        Intent inte2 = (new Intent(getApplicationContext(), FirstActivity.class));
                        inte2.putExtra("usernameAS", d);
                        startActivity(inte2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        Intent inte = (new Intent(getApplicationContext(), Settings.class));
                        inte.putExtra("usernameAS", d);
                        startActivity(inte);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), MainPageLoginRegister.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.adv:
                        Intent inte1 = (new Intent(getApplicationContext(), SkelbimaiListViewBurger.class));
                        inte1.putExtra("usernameAS", d);
                        startActivity(inte1);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(Skelbimo_pridejimas.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(Skelbimo_pridejimas.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                new AlertDialog.Builder(Skelbimo_pridejimas.this)
                        .setTitle("Permission Required")
                        .setMessage("Storage permission is required to save image")
                        .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //on click Allow we need request again
                                dialogInterface.cancel();
                                ActivityCompat.requestPermissions(Skelbimo_pridejimas.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                            }
                        }).setNegativeButton("DENIED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
            } else {
                ActivityCompat.requestPermissions(Skelbimo_pridejimas.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        } else {
            //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            //capture image when permission is granted
            openCameraTocaptureImage();
        }
    }
    private void openCameraTocaptureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "edu.ktu.myfirstapplication",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, PICK_IMAGE_REQUEST);
            }
        }
    }

    public void getImage(View view){
        checkStoragePermission();
    }

    private File createImageFile() throws IOException {
        // failo pavadinimas is kameros fotkes
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();
        count++;
        return image;
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
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if(count == 1){
                File f = new File(currentPhotoPath);
                contentUri = Uri.fromFile(f);
            }else{
                mImageUri = data.getData();
            }

            if(mImageUri == null){
                mImageUri = contentUri;
                Picasso.get().load(contentUri).into(imageView);
            }else {
                Picasso.get().load(mImageUri).into(imageView);
            }
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
                                    Intent intent = getIntent();
                                    int numb = intent.getIntExtra("templateIs",0);
                                    //Toast.makeText(Skelbimo_pridejimas.this, "Sitas yra naudingas", Toast.LENGTH_LONG).show();
                                    String imageUrl = uri.toString();
                                    //Toast.makeText(Skelbimo_pridejimas.this, imageUrl, Toast.LENGTH_LONG).show();
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
                                    skelbimas.setTemplate(numb);

                                    reff.child(Title).setValue(skelbimas);
                                    intent = new Intent(context, SkelbimaiListViewBurger.class);
                                    //intent.putExtra("flag", true);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(Skelbimo_pridejimas.this, 0, intent, 0);
                                    createNotificationChannel();
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Skelbimo_pridejimas.this, CHANNEL_ID)
                                            .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                                            .setContentTitle(getText(R.string.NotificationTitle))
                                            .setContentText(getText(R.string.NotificationDescription) + " " + Title)
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Skelbimo_pridejimas.this);

                                    notificationManager.notify(0, builder.build());
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
            Toast.makeText(this, getText(R.string.nofile), Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        //android sdk pvz
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getText(R.string.NotificationTitle);
            String description = (String) getText(R.string.NotificationDescription);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}