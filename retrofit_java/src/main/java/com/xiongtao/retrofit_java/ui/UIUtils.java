package com.xiongtao.retrofit_java.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.reactivex.annotations.NonNull;

public class UIUtils {
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             Fragment fragment,int frameId){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId,fragment);
        fragmentTransaction.commit();
    }
}
