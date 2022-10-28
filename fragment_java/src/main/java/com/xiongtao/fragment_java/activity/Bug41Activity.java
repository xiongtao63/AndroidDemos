package com.xiongtao.fragment_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.xiongtao.fragment_java.R;

/**
 * TODO: Fragment重叠异常
 */
public class Bug41Activity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug41);
        String param = getIntent().getStringExtra("param");
        TextView showParam = findViewById(R.id.showparam);
        showParam.setText(param);
        findViewById(R.id.bntReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("returnParam","我是从41返回的");
                setResult(100,intent);
                finish();
            }
        });

    }
}
