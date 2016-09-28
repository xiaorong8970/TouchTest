package com.example.touchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycle);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("第"+(i+1)+"条文字");
        }
        Adapter adapter = new Adapter(list, this);
        recyclerView.setAdapter(adapter);
        //这个recyclerView里面的item可以滑动出一个选项,需要监听它的触摸事件
        recyclerView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.onTouchEvent(motionEvent);
        return true;
    }
}
