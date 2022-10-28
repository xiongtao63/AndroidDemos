package com.xiongtao.fragment_java.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xiongtao.fragment_java.BaseFragment;
import com.xiongtao.fragment_java.FragmentDelegater;
import com.xiongtao.fragment_java.R;

/**
 * TODO: Fragment重叠异常
 */
public class Bug4Fragment extends BaseFragment {

    public static Fragment newInstance() {

        Bundle args = new Bundle();

        Bug4Fragment fragment = new Bug4Fragment();

        fragment.setArguments(args);
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, 1, 0, "新建").setIcon(R.mipmap.ic_launcher).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        menu.setGroupVisible(R.menu.bottomnavigationview,true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Zero", "Bug4Fragment onOptionsItemSelected: "+item );
        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug4,container,false);
    }
    boolean isshow = true;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bug41Fragment bug41Fragment = Bug41Fragment.newInstance();

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.childframeLayout,bug41Fragment,Bug41Fragment.class.getName());
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
        view.findViewById(R.id.showhide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(isshow){
                    fragmentTransaction.hide(bug41Fragment).commit();
                }else{
                    fragmentTransaction.show(bug41Fragment).commit();
                }
                isshow = !isshow;

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Zero", "Bug4Fragment requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data.getStringExtra("returnParam"));

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("Zero", "Bug4Fragment onHiddenChanged: " );
    }
}
