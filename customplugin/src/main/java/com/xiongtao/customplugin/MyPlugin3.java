package com.xiongtao.customplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin3 implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        System.out.println("测试第三种方式11111");
    }
}
