package com.sldevs.panaghiusa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.google.zxing.WriterException;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Sign_Up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private EditText etFullName, etEmail, etMobileNo, etPassword;
    private TextView tvLogIn;
    private Button btnSignUp;
    private ProgressBar pbLoading;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    UploadTask uploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etMobileNo = findViewById(R.id.etMobileNo);
        etPassword = findViewById(R.id.etPassword);

        tvLogIn = findViewById(R.id.tvLogIn);
        btnSignUp = findViewById(R.id.btnCreateAccount);

        pbLoading = findViewById(R.id.pbLoading);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sign_Up.this, Log_In.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void registerUser(){
        String fullname = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String number = etMobileNo.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(fullname.isEmpty()){
            etFullName.setError("Full name is required!");
            etFullName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        if(number.isEmpty()){
            etMobileNo.setError("Mobile Number is required!");
            etMobileNo.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please provide a valid email!");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            etPassword.setError("Minimum password length should be 6 characters!");
            etPassword.requestFocus();
            return;
        }

        pbLoading.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            String id = FirebaseAuth.getInstance().getUid();
                            User user = new User(id,fullname,email,number,password,"0",currentDate,currentTime);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        generateQR();
                                        defaultProfile();
                                        pbLoading.setVisibility(View.GONE);
                                        passUserToken();
                                        Toast.makeText(Sign_Up.this,"You created an account successfully",Toast.LENGTH_LONG).show();
                                        pbLoading.setVisibility(View.GONE);

                                    }else{
                                        Toast.makeText(Sign_Up.this,"Failed to register",Toast.LENGTH_LONG).show();
                                        pbLoading.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Sign_Up.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                            pbLoading.setVisibility(View.GONE);
                        }
                    }
                });

    }
    public void defaultProfile(){
        String id = FirebaseAuth.getInstance().getUid();
        StorageReference profilePic = storageReference.child(id + ".png");
        StorageReference profilePicRef = storageReference.child("ProfilePicture/" + id + "user_profile.png");
        profilePic.getName().equals(profilePicRef.getName());
        profilePic.getPath().equals(profilePicRef.getPath());


        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.avatar_img);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask upload = profilePicRef.putBytes(data);
        upload.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Sign_Up.this,e.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void passUserToken(){
        Intent i = new Intent(Sign_Up.this,Home_Screen.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
    public void generateQR(){
        String id = FirebaseAuth.getInstance().getUid();
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder("User ID: " + id + "\nEmail: " + etEmail.getText().toString(), null, QRGContents.Type.TEXT, dimen);
        try {
            StorageReference qrCode = storageReference.child(id + ".png");
            StorageReference qrCodeRef = storageReference.child("QRCodes/" + id + "user_qr.png");
            qrCode.getName().equals(qrCodeRef.getName());
            qrCode.getPath().equals(qrCodeRef.getPath());
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask upload = qrCodeRef.putBytes(data);
            upload.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Sign_Up.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            });
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }
    }
}