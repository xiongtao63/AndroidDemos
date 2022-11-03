package com.xiongtao.retrofit_java.ui.login;

import com.xiongtao.retrofit_java.R;
import com.xiongtao.retrofit_java.base.BaseActivity;
import com.xiongtao.retrofit_java.ui.UIUtils;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> {
    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrameLayout);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            UIUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.contentFrameLayout);
        }
        mPresenter = new LoginPresenter();
        ((LoginPresenter)mPresenter).setView(loginFragment);
        ((LoginPresenter)mPresenter).setModel(new LoginModel());

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
