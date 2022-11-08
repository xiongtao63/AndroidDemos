package com.xiongtao.tinkerdemo;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
 
public class MyApplication  extends TinkerApplication {
 
    public MyApplication() {
        //注意第二个参数为自定义的SampleApplicationLike的路径
        super(ShareConstants.TINKER_ENABLE_ALL,
                "com.xiongtao.tinkerdemo.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader",
                false, false);
    }
}