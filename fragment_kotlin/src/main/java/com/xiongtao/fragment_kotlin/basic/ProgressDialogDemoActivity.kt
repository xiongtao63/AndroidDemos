package com.xiongtao.fragment_kotlin.basic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xiongtao.fragment_kotlin.ProgressDialog
import com.xiongtao.fragment_kotlin.R

class ProgressDialogDemoActivity: AppCompatActivity() {

   private val progressDialog = ProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progressdialog)
    }

    fun showProgress(view: View) {

        progressDialog.show(supportFragmentManager,"show")
    }
    fun hideProgress(view: View) {
        progressDialog.dismiss()
    }
}