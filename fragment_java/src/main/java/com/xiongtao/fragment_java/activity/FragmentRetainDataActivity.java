package com.xiongtao.fragment_java.activity;//package com.xiongtao.fragment_java.activity;
//
//import android.app.Activity;
//import android.app.FragmentManager;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//
//import androidx.fragment.app.DialogFragment;
//
//import com.xiongtao.fragment_java.LoadingDialog;
//import com.xiongtao.fragment_java.R;
//import com.xiongtao.fragment_java.dialog.RetainedFragment;
//
//
//public class FragmentRetainDataActivity extends Activity {
//
//    private static final String TAG = "Zero";
//    private RetainedFragment dataFragment;
//    private LoadingDialog mLoadingDialog;
//    private ImageView mImageView;
//    private Bitmap mBitmap;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // find the retained fragment on activity restarts
//        FragmentManager fm = getFragmentManager();
////        dataFragment = (RetainedFragment) fm.findFragmentByTag("data");
//        // create the fragment and data the first time
//        if (dataFragment == null) {
//            // add the fragment
//            dataFragment = new RetainedFragment();
////            fm.beginTransaction().add(dataFragment, "data").commit();
//        }
//        mBitmap = collectMyLoadedData();
//        initData();
//
//        // the data is available in dataFragment.getData()
//    }
//
//    /**
//     * 初始化数据
//     */
//    private void initData() {
////        mImageView = (ImageView) findViewById(R.id.id_imageView);
//        if (mBitmap == null) {
//            mLoadingDialog = new LoadingDialog();
//            mLoadingDialog.show(this);
//
//        } else {
//            mImageView.setImageBitmap(mBitmap);
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e(TAG, "onDestroy");
//        super.onDestroy();
//        // store the data in the fragment
//        dataFragment.setData(mBitmap);
//    }
//
//    private Bitmap collectMyLoadedData() {
//        return dataFragment.getData();
//    }
//
//}
//
