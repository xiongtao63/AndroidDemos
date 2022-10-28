package com.xiongtao.fragment_java.dialog;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


public class RetainedFragment extends Fragment
{
    // data object we want to retain
    private Object data;
    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(Bitmap data)
    {
        this.data = data;
    }

    public Object getData()
    {
        return data;
    }
}
