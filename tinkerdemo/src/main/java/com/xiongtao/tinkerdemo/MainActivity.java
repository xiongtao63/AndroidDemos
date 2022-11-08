package com.xiongtao.tinkerdemo;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askForRequiredPermissions();

        findViewById(R.id.btnLoad).setOnClickListener(v -> {

//            String pathRootPatchApk = Environment.getExternalStorageDirectory() +
//                    File.separator + "app-debug-patch_signed.apk";
            String pathRootPatchApk = "/storage/sdcard0/app-debug-patch_signed.apk";
            File fileApk = new File(pathRootPatchApk);
            Log.i("Tinker", "补丁文件路径：" + pathRootPatchApk);
            Log.i("Tinker", "补丁文件是否存在：" + fileApk.exists());
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), pathRootPatchApk);
            Toast.makeText(this,"1111111111修改好的dsfdsfsd",Toast.LENGTH_LONG).show();
        });
    }

    private void askForRequiredPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }

    private boolean hasRequiredPermissions() {
        if (Build.VERSION.SDK_INT >= 16) {
            final int res = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            return res == PackageManager.PERMISSION_GRANTED;
        } else {
            // When SDK_INT is below 16, READ_EXTERNAL_STORAGE will also be granted if WRITE_EXTERNAL_STORAGE is granted.
            final int res = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return res == PackageManager.PERMISSION_GRANTED;
        }
    }
}