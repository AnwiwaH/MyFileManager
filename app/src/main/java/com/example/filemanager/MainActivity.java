package com.example.filemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView(this);
        tv.setTextSize(16);
        tv.setPadding(32, 32, 32, 32);
        setContentView(tv);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }

        File root = Environment.getExternalStorageDirectory();
        File[] files = root.listFiles();
        StringBuilder sb = new StringBuilder("Daftar File & Folder:\n\n");

        if (files != null) {
            for (File file : files) {
                sb.append(file.isDirectory() ? "[FOLDER] " : "[FILE] ")
                  .append(file.getName()).append("\n");
            }
        } else {
            sb.append("Akses ditolak atau direktori kosong.");
        }

        tv.setText(sb.toString());
    }
}