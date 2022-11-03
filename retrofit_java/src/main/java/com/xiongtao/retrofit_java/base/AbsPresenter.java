package com.xiongtao.retrofit_java.base;

public abstract class AbsPresenter <M extends BaseModel,V extends BaseView>{
    private static final String TAG = AbsPresenter.class.getSimpleName();
    protected M mModel;
    protected V mView;

    public abstract void setModel(M mModel);

    public abstract void setView(V mView);
}
