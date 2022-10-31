package com.xiongtao.fragment_kotlin.basic

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.xiongtao.fragment_kotlin.R
import org.jetbrains.anko.find

class TaskDemo1Activity : AppCompatActivity() {
    private var mDatas: List<String>? = null
    private var mAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null
    private var dataFragment: OtherRetainedFragment? = null
    private var mMyTask: MyAsyncTask? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_list)
        listView = find(R.id.listview)
        var fm = supportFragmentManager
        dataFragment = fm.findFragmentByTag("data") as OtherRetainedFragment
        if (dataFragment == null) {
            dataFragment = OtherRetainedFragment()
            fm.commit {
                add(dataFragment!!, "data")
            }
        }
        mMyTask = dataFragment!!.data
        if (mMyTask != null) {
            mMyTask!!.setActivity(this)
        } else {
            mMyTask = MyAsyncTask(this)
            dataFragment!!.data = mMyTask
            mMyTask!!.execute()
        }


    }

    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        Log.i(TAG, "onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mMyTask!!.setActivity(null)
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState")
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        super.onDestroy()
    }

    fun onTaskCompleted() {
        mDatas = mMyTask!!.items

        mAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,mDatas!!)
        listView!!.adapter = mAdapter
    }


    companion object {
        private const val TAG = "Zero"
    }
}