import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin2 : Plugin<Project> {
    override fun apply(target: Project) {
        println("kotlin 插件开发")
    }
}