plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(libs.plugins.kotlinMultiplatform.toDep())
  implementation(libs.plugins.androidApplication.toDep())
  implementation(libs.plugins.androidLibrary.toDep())
  implementation(libs.plugins.composeCompiler.toDep())
  implementation(libs.plugins.composeMultiplatform.toDep())
  implementation(libs.plugins.ktfmt.toDep())
  implementation(libs.plugins.kotlinx.serialization.toDep())
  compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

kotlin {
  jvmToolchain(libs.versions.java.get().toInt())
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

fun Provider<PluginDependency>.toDep() = map {
  "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}