package com.xiongtao.fragment_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.xiongtao.fragment_kotlin.basic.BasicDemoActivity
import com.xiongtao.fragment_kotlin.basic.BasicDynamicDemoActivity
import com.xiongtao.fragment_kotlin.basic.BitmapDemoActivity
import com.xiongtao.fragment_kotlin.basic.TaskDemo1Activity
import org.jetbrains.anko.support.v4.startActivity

class MainFragment: ListFragment() {
    private lateinit var arrarAdapter: ArrayAdapter<String>
    companion object{
        fun newInstance(): Fragment {
            val args = Bundle()

            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val array = arrayOf(
            "静态使用",
            "动态使用",
            "保存bitmap",
            "保存Task",
            "Can not perform this action after onSaveInstanceState",
            "Fragment重叠异常",
            "嵌套的fragment不能在onActivityResult()中接收到返回值",
            "未必靠谱的出栈方法remove()",

            "popBackStack的坑",
            "pop多个Fragment时转场动画 带来的问题",
            "进入新的Fragment并立刻关闭当前Fragment 时的一些问题",
            "Fragment+viewpager"
        )
        arrarAdapter = ArrayAdapter(activity!!,android.R.layout.simple_list_item_1,array)
        listAdapter = arrarAdapter
    }
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val item = arrarAdapter.getItem(position)
        Toast.makeText(activity,item,Toast.LENGTH_SHORT).show()
        when(position){
            0 -> startActivity(Intent(activity,BasicDemoActivity::class.java))
            1 -> startActivity<BasicDynamicDemoActivity>()
            2 -> startActivity<BitmapDemoActivity>()
            3 -> {
                startActivity<TaskDemo1Activity>()
            }
        }

    }
}