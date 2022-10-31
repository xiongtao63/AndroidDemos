package com.xiongtao.fragment_kotlin.basic

import android.os.AsyncTask
import androidx.fragment.app.FragmentManager
import com.xiongtao.fragment_kotlin.ProgressDialog
import java.util.*
import kotlin.collections.ArrayList

class MyAsyncTask(private var activity: TaskDemo1Activity?) : AsyncTask<Void?, Void?, Void?>() {
    /**
     * 是否完成
     */
    private var isCompleted = false

    /**
     * 进度框
     */
    private lateinit var mLoadingDialog: ProgressDialog
    var items: List<String>? = null
        private set

    override fun onPreExecute() {
        mLoadingDialog = ProgressDialog()
        mLoadingDialog.show(activity!!.supportFragmentManager, "loading")

    }

    override fun doInBackground(vararg p0: Void?): Void? {
        items = loadingData()
        return null
    }

    private fun loadingData(): List<String>? {
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
        }
        return ArrayList(
            listOf(
                "fragment 基本使用",
                "fragment动态添加",
                "getActivity == null", "fragment重叠", "add replace的区别",
                "popstack"
            )
        )
    }

    fun setActivity(activity: TaskDemo1Activity?) {
        if (activity == null) {
            mLoadingDialog.dismiss()
        }
        if (activity != null) {
            this.activity = activity
        }
        if (activity != null && !isCompleted) {
            mLoadingDialog = ProgressDialog()
            mLoadingDialog.show(activity.supportFragmentManager, "loading")
        }
        if (isCompleted) {
            notifyActivityTaskCompleted()
        }
    }

    override fun onPostExecute(result: Void?) {
        isCompleted = true
        notifyActivityTaskCompleted()
        if (mLoadingDialog != null) mLoadingDialog.dismiss()
    }

    fun notifyActivityTaskCompleted() {
        if (null != activity) {
            activity?.onTaskCompleted()
        }
    }


}