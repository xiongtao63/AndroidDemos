package com.xiongtao.fragment_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.xiongtao.fragment_java.activity.Bug1Activity;
import com.xiongtao.fragment_java.activity.Bug2Activity;
import com.xiongtao.fragment_java.activity.Bug3Activity;
import com.xiongtao.fragment_java.activity.Bug4Activity;
import com.xiongtao.fragment_java.activity.Bug5Activity;
import com.xiongtao.fragment_java.activity.MyDialogActivity;
import com.xiongtao.fragment_java.activity.SaveDataActivity;
import com.xiongtao.fragment_java.activity.ViewpagerActivity;


public class MainFragment extends ListFragment {


    public static Fragment newIntance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    ArrayAdapter<String> arrayAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.bottomnavigationview,menu);
//        menu.clear();//清除原有菜单选项
//        menu.add("Menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        menu.add("Menu 1b").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Zero", "Bug4Fragment onOptionsItemSelected: "+item );
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] array = new String[]{
                "getActivity==null",
                "Can not perform this action after onSaveInstanceState",
                "Fragment重叠异常",
                "嵌套的fragment不能在onActivityResult()中接收到返回值",
                "未必靠谱的出栈方法remove()",
                "mAvailIndeices的BUG",
                "popBackStack的坑",
                "pop多个Fragment时转场动画 带来的问题",
                "进入新的Fragment并立刻关闭当前Fragment 时的一些问题",
                "Fragment+viewpager",
                "FragmentDialog",
                "Fragment保存数据"
        };
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, array);
        setListAdapter(arrayAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String item = arrayAdapter.getItem(position);
        Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
        switch (position) {
            case 0:
                Intent intent0 = new Intent(getActivity(), Bug1Activity.class);
                startActivity(intent0);
                break;
            case 1:
                Intent intent1 = new Intent(getActivity(), Bug2Activity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(getActivity(), Bug3Activity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(getActivity(), Bug4Activity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(getActivity(), Bug5Activity.class);
                startActivity(intent4);
                break;
            case 9:
                Intent intent9 = new Intent(getActivity(), ViewpagerActivity.class);
                startActivity(intent9);
                break;
            case 10:
                Intent intent10 = new Intent(getActivity(), MyDialogActivity.class);
                startActivity(intent10);
                break;
            case 11:
                Intent intent11 = new Intent(getActivity(), SaveDataActivity.class);
                startActivity(intent11);
                break;
            default:
                break;
        }
    }


}
