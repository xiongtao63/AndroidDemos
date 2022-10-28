package com.xiongtao.fragment_java.arch;

import android.content.Context;

/**
 * fragment主题，被观察者
 */
public class FragmentObservable extends Observable {

    /**
     * 默认状态
     */
    FragmentLifeState fragmentLifeState = FragmentLifeState.UNKNOWN;

    Context context;


    /**
     * 定义fragment的getActivity为null的生命周期
     */
    enum FragmentLifeState {
        UNKNOWN, ATTACH, DETACH
    }

    public void onAttach(Context context) {
        fragmentLifeState = FragmentLifeState.ATTACH;
        this.context = context;
        this.notifyObservers();
    }

    public void onDetach() {
        fragmentLifeState = FragmentLifeState.DETACH;
        this.notifyObservers();
    }

}
