package com.xiongtao.fragment_kotlin.basic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.xiongtao.fragment_kotlin.LifeCycleFragment
import com.xiongtao.fragment_kotlin.R
import org.jetbrains.anko.support.v4.find

class NumFragment @JvmOverloads constructor(val name:String = "",val age:Int = 0):LifeCycleFragment() {

    companion object{
        fun newInstance(num :Int): Fragment {
            val args = Bundle()

            val fragment = NumFragment()
            args.putString("NAME",num.toString())
            fragment.arguments = args
            return fragment
        }

        fun newInstance(layoutId: Int, num: Int): Fragment {
            val args = Bundle()
            
            val fragment = NumFragment()
            args.putString("NAME", num.toString())
            args.putInt("LayoutId", layoutId)
            fragment.arguments = args
            return fragment
        }
        fun newInstance(name: String): Fragment {
            val args = Bundle()

            val fragment = NumFragment()
            args.putString("NAME", name)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if(arguments != null){
            inflater.inflate(arguments!!.getInt("LayoutId",R.layout.fragment_num),container,false)
        }else{ inflater.inflate(R.layout.fragment_num,container,false)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = find<TextView>(R.id.text)
        val num = arguments?.getString("NAME","null")
        text.text = String.format(getString(R.string.fragment_text),num)
    }


}