package com.xiongtao.fragment_java.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiongtao.fragment_java.BaseFragment;
import com.xiongtao.fragment_java.FragmentDelegater;
import com.xiongtao.fragment_java.R;

import java.util.List;

/**
 * TODO: Fragment重叠异常
 */
public class Bug5Fragment extends BaseFragment {

    public static Bug5Fragment newInstance() {

        Bundle args = new Bundle();

        Bug5Fragment fragment = new Bug5Fragment();
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        fragment.setArguments(args);
        return fragment;
    }
    private int mIndex = -1;

    private Fragment[] mTestFragments = new Fragment[]{
        Bug51Fragment.newIntance("A"),
        Bug52Fragment.newIntance("B"),
        Bug53Fragment.newIntance("C"),
        Bug54Fragment.newIntance("D"),
        Bug55Fragment.newIntance("E"),
        Bug56Fragment.newIntance("F"),
        Bug57Fragment.newIntance("G"),
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getChildFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                    Log.i("Zero", "onBackStackChanged");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug5,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndex++;
                FragmentManager manager = getChildFragmentManager();
                manager.beginTransaction()
                        .add(R.id.childframeLayout,mTestFragments[mIndex],mTestFragments[mIndex].getClass().getName())
                        .addToBackStack(mTestFragments[mIndex].getClass().getName())
                        .commit();

                if(mIndex >= mTestFragments.length){
                    mIndex = -1;
                }

            }
        });

        view.findViewById(R.id.replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                mIndex++;
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.childframeLayout, mTestFragments[mIndex], mTestFragments[mIndex].getClass().getName())
                        .addToBackStack(mTestFragments[mIndex].getClass().getName())
                        .commitAllowingStateLoss();
                if (mIndex >=mTestFragments.length) {
                    mIndex = -1;
                }
            }
        });

        view.findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .show(fragmentManager.findFragmentByTag("basefragment"))
                        .commitAllowingStateLoss();
            }
        });

        view.findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .hide(fragmentManager.findFragmentByTag(mTestFragments[mIndex].getClass().getName()))
                        .commitAllowingStateLoss();
            }
        });

        view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .remove(fragmentManager.findFragmentByTag(mTestFragments[mIndex].getClass().getName()))
                        .commitAllowingStateLoss();
            }
        });

        view.findViewById(R.id.popbackstack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.popBackStack();

            }
        });

        view.findViewById(R.id.popbackstatckimmediate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.popBackStackImmediate(mTestFragments[1].getClass().getName(),1);
            }
        });

        view.findViewById(R.id.debug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager childFragmentManager = getChildFragmentManager();
                List<Fragment> childFragments = childFragmentManager.getFragments();
                final int entryCount = childFragmentManager.getBackStackEntryCount();
                Log.i("Zero", "***************************");
                Log.i("Zero", "fragments: " + childFragments.size() + " entryCount: " + entryCount);
                if (childFragments.size() <= 0) {
                    Log.i("Zero", "no fragments");
                }
                for (Fragment fragment : childFragments) {
                    FragmentUtils.getFragmentInfo(fragment);
                }
                if (entryCount <= 0) {
                    Log.i("Zero", "backstack is empty");
                }
                for (int i = 0; i < entryCount; i++) {
                    FragmentManager.BackStackEntry entry = childFragmentManager.getBackStackEntryAt(i);
                    Log.i("Zero", "BackStackEntryid: " + entry.getId() + " name: " + entry.getName());
                }
            }
        });
    }
}
