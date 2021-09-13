package com.example.lesson7.externalstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.lesson7.R;

public class Activity3_ExternalStorage extends AppCompatActivity {

    public static final int REQUEST_WRITE_STORAGE = 1;

    private static final String IMAGE_URL = "https://codefresher.vn/wp-content/uploads/2021/06/Banner-KH-ios1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_external_storage);
//        startDownloadImage();
        checkPermission();
    }

    private void startDownloadImage() {
        DownloadImageTask task = new DownloadImageTask(this.getApplicationContext(), new DownloadImageTask.CallBack() {
            @Override
            public void onDownloadComplete(Bitmap bitmap) {
                ImageView imageView = findViewById(R.id.ivImage);
                imageView.setImageBitmap(bitmap);
            }

        });
        task.execute(IMAGE_URL);
    }

    private boolean hasStoragePermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void checkPermission() {
        boolean hasPermission = hasStoragePermission();
        if (hasPermission) {
            startDownloadImage();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startDownloadImage();
        }
    }
}