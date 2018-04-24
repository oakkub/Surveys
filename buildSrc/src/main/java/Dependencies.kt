import sun.misc.Version

object Dependencies {

    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val support_v4 = "com.android.support:support-v4:${Versions.support_lib}"
    const val support_design = "com.android.support:design:${Versions.support_lib}"
    const val recycler_view = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraint_layout}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_adapter_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"

    const val arch_extensions = "android.arch.lifecycle:extensions:${Versions.arch_component}"
    const val arch_common = "android.arch.lifecycle:common-java8:${Versions.arch_component}"
    const val android_ktx = "androidx.core:core-ktx:${Versions.android_ktx}"

    const val fresco = "com.facebook.fresco:fresco:${Versions.fresco}"
    const val fresco_okhttp = "com.facebook.fresco:imagepipeline-okhttp3:${Versions.fresco}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val arch_testing = "android.arch.core:core-testing:${Versions.arch_component}"

    const val test_runner = "com.android.support.test:runner:${Versions.test_runner}"
    const val test_rules = "com.android.support.test:rules:${Versions.test_runner}"
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"

}