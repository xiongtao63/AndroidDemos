package com.xiongtao.asmplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.internal.extensibility.DefaultConvention;

public class APMPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
//        DefaultConvention android = project.getExtensions().getByType(DefaultConvention.class);
////
////        //注册一个Transform
////
//        android.registerTransform(new ASMTransfrom());
    }
}
