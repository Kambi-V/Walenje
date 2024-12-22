import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.the

internal fun Project.configureAndroidLibrary() {
  val libs = the<LibrariesForLibs>()

  extensions.configure<LibraryExtension> {
    val moduleName = path.split(":").drop(2).joinToString(".")
    namespace =
      if (moduleName.isNotEmpty()) "kambi.victor.walenje.$moduleName" else "io.tajji.sceptre.kit"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
      minSdk = libs.versions.android.minSdk.get().toInt()
      consumerProguardFiles("consumer-proguard-rules.pro")
    }
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_21
      targetCompatibility = JavaVersion.VERSION_21
    }
    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
    apply { sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml") }
//    namespace = group.toString() + path.replace("-", "").split(":").joinToString(".")
//    compileSdk = libs.versions.android.compileSdk.get().toInt()

//    defaultConfig {
//      minSdk = libs.versions.android.minSdk.get().toInt()
//    }
//    compileOptions {
//      sourceCompatibility = JavaVersion.VERSION_21
//      targetCompatibility = JavaVersion.VERSION_21
//    }
  }
}