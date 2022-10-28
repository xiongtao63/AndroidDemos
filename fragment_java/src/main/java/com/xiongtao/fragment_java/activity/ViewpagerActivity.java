package com.xiongtao.fragment_java.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xiongtao.fragment_java.BottomNavigationViewHelper;
import com.xiongtao.fragment_java.R;
import com.xiongtao.fragment_java.fragment.Bug51Fragment;
import com.xiongtao.fragment_java.fragment.Bug52Fragment;
import com.xiongtao.fragment_java.fragment.Bug53Fragment;
import com.xiongtao.fragment_java.fragment.Bug54Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ViewpagerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private Fragment[] fragments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        viewPager = findViewById(R.id.viewpager);
        initFragment();
        viewPager.setOnPageChangeListener(onPageChangeListener);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.setCurrentItem(0);



    }

    static class MyViewPagerAdapter extends FragmentStatePagerAdapter{
Fragment[] fragments;
        public MyViewPagerAdapter(FragmentManager fm,Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    private void initFragment() {
        fragments = new Fragment[]{
                Bug51Fragment.newIntance("第1页"),
                Bug52Fragment.newIntance("第2页"),
                Bug53Fragment.newIntance("第3页"),
                Bug54Fragment.newIntance("第4页"),
        };
    }

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.fragment_1:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.fragment_2:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.fragment_3:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.fragment_4:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
