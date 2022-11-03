package com.xiongtao.retrofit_java.ui.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.xiongtao.retrofit_java.MainActivity;
import com.xiongtao.retrofit_java.R;
import com.xiongtao.retrofit_java.base.BaseFragment;
import com.xiongtao.retrofit_java.network.api.WanAndroidApi;
import com.xiongtao.retrofit_java.network.bean.BaseResponse;
import com.xiongtao.retrofit_java.network.bean.ProjectBean;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * view层负责界面控件的显示
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;


    private LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {

    }

    protected static LoginFragment newInstance() {
        return new LoginFragment();
    }
    private void uploadImage() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()//建造者模式
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WanAndroidApi wanAndroidApi = retrofit.create(WanAndroidApi.class);//代理实例

        //上传单个图片1
        File file = new File("");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"),file);
        wanAndroidApi.upload1(requestBody).execute();
        //上传单个图片2
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("上传的key"
                ,file.getName(),requestBody);
        wanAndroidApi.upload2(imagePart)
                .enqueue(new Callback<ProjectBean>() {
                    @Override
                    public void onResponse(Call<ProjectBean> call, Response<ProjectBean> response) { }

                    @Override
                    public void onFailure(Call<ProjectBean> call, Throwable t) { }
                });
    }
    private void uploadImages() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()//建造者模式
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WanAndroidApi wanAndroidApi = retrofit.create(WanAndroidApi.class);//代理实例

        //上传多张图片1
        //图片集合
        List<File> files = new ArrayList<>();

        Map<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), files.get(i));
            map.put("file" + i + "\";filename=\"" + files.get(i).getName(), requestBody);
        }
        wanAndroidApi.upload3(map).execute();
        //上传多张图片2
        Map<String, MultipartBody.Part> map1 = new HashMap<>();
        File file1 = new File("");
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/png"), file1);
        MultipartBody.Part part1 = MultipartBody.Part.createFormData("上传的key1", file1.getName(), requestBody1);
        map1.put("上传的key1", part1);

        File file2 = new File("");
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/png"), file2);
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("上传的key2", file2.getName(), requestBody2);
        map1.put("上传的key2", part2);
        wanAndroidApi.upload4(map1).execute();



    }


    private void uploadImagesAndText() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()//建造者模式
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WanAndroidApi wanAndroidApi = retrofit.create(WanAndroidApi.class);//代理实例


        //RequestBody startTowerId = RequestBody.create(MediaType.parse("multipart/form-data"), "xx");
        MediaType textType = MediaType.parse("text/plain");
        RequestBody name = RequestBody.create(textType, "zero");
        RequestBody password = RequestBody.create(textType, "123456");

        File file = new File("");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("上传的key", file.getName(), requestBody);

        wanAndroidApi
                .upload6(name, password, part)
                .enqueue(new Callback<ProjectBean>() {
                    @Override
                    public void onResponse(Call<ProjectBean> call, Response<ProjectBean> response) {

                    }

                    @Override
                    public void onFailure(Call<ProjectBean> call, Throwable t) {

                    }
                });

        //multipart/form-data ： 需要在表单中进行文件上传时，就需要使用该格式
        //        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        //
        //        String descriptionString = "hello, 这是文件描述";
        //        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

    }

    private void example1() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()//建造者模式
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WanAndroidApi wanAndroidApi = retrofit.create(WanAndroidApi.class);//代理实例

        Map<String,String> headers = new HashMap<>();
        headers.put("Accept","text/plain");
        headers.put("Accept-Charset", "utf-8");

        wanAndroidApi.example1(headers)
                .enqueue(new Callback<ProjectBean>() {
                    @Override
                    public void onResponse(Call<ProjectBean> call, Response<ProjectBean> response) { }

                    @Override
                    public void onFailure(Call<ProjectBean> call, Throwable t) { }
                });
    }

    @OnClick(R.id.btn_login)
    public void onClick(View v) {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        mPresenter.login(username,password);
        if(true)return;
        //MVP retrofit + rxjava
//        mPresenter.login(username, password);

        //断点调试大法
        //基本用法
        Retrofit retrofit = new Retrofit.Builder()//建造者模式
                .baseUrl("https://www.wanandroid.com/")
                //Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WanAndroidApi wanAndroidApi = retrofit.create(WanAndroidApi.class);//代理实例

//        Call<ProjectBean> call = wanAndroidApi.getProject1();//获取具体的某个业务
//        //同步请求
//        Response<ProjectBean> response = call.execute();
//        ProjectBean projectBean = response.body();
        //异步请求
//        call.enqueue(new Callback<ProjectBean>() {
//            @Override
//            public void onResponse(final Call<ProjectBean> call, final Response<ProjectBean> response) {
//                Log.i("Zero","response: " + response.body());
//            }
//            @Override
//            public void onFailure(final Call<ProjectBean> call, final Throwable t) {}
//        });
        Maybe<BaseResponse> login = wanAndroidApi.login(username, password);
        login.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
            @Override
            public void accept(BaseResponse baseResponse) throws Exception {
                Log.i("","");
            }
        });


    }


    @Override
    public void loginSuccess(String result) {
        Toast.makeText(mActivity, result, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mActivity, MainActivity.class));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (mPresenter == null) {//P层与V层关联起来
            mPresenter = presenter;
        }
    }
}
