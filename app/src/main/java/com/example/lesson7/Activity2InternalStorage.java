package com.example.lesson7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Activity2InternalStorage extends AppCompatActivity implements View.OnClickListener {
    private Button btnSaveData, btnReadData;
    private TextView tvData;
    private final String fileName = "codefresher.txt";
    private final String content ="Noi dung chia se moi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_internal_storage);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        tvData = (TextView) findViewById(R.id.tv_data);
        btnSaveData.setOnClickListener(this);
        btnReadData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_data:
                //// TODO: 11/15/16
                saveData();
                //saveDataByCache();
                break;

            case R.id.btn_read_data:
                //// TODO: 11/15/16
                // readData();
                readData2();
                break;


            default:
                break;
        }
    }

    public void saveData() {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readData2(){
        try {
            File file = new File(getFilesDir(), fileName); // /data/data/com. /codefresher.txt
            //FileInputStream in = openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine())!= null){
                buffer.append(line).append("\n");
            }
            Log.d("MainActivity", buffer.toString());
            tvData.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
