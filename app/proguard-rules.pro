# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Dawidj\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}





-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable




-dontwarn okio.**
-keep class okio.* { *; }
-dontwarn retrofit2.Platform$Java8
-keep class retrofit2.* { *; }


-dontwarn org.eclipse.**
-keep class org.eclipse.* { *; }


-dontwarn com.squareup.**
-keep class com.squareup.* { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class com.google.common.* { *; }
-dontwarn com.google.common.**

-keep class com.dawidj.weatherforecastapp.* { *; }
-dontwarn com.dawidj.weatherforecastapp.**

-dontwarn dagger.**
-keep class dagger.* { *;}


#-renamesourcefileattribute SourceFile
#-keepattributes SourceFile,LineNumberTable
#
#-dontwarn dagger.**

-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}
#
## For using GSON @Expose annotation
#-keepattributes *Annotation*
#-keepattributes InnerClasses
#-keepattributes EnclosingMethod
#
## Gson specific classes
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
#
#-keep class dagger.* { *; }
#-keep class javax.inject.* { *; }
#-keep class * extends dagger.internal.Binding
#-keep class * extends dagger.internal.ModuleAdapter
#-keep class * extends dagger.internal.StaticInjection
#
#-dontwarn com.dawidj.weatherforecastapp.**
#
#-dontwarn com.google.common.**
#
#
#-dontwarn com.squareup.javapoet.**
#
#-dontwarn org.eclipse.**
#
#-dontwarn okio.**
#
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }

#-keepattributes EnclosingMethod
#-keepattributes InnerClasses
#
#-dontoptimize
#
#-keep class retrofit.** { *; }
#-keepclasseswithmembers class * {
#    @retrofit.http.* <methods>;
#}
#-dontwarn io.realm.**
#-keep class io.realm.** { *; }
#
#-dontwarn android.support.**
#-dontwarn com.github.**
#-dontwarn com.squareup.**
#-dontwarn com.dawidj.weatherforecastapp.**
#-dontwarn com.google.common.**
#-dontwarn dagger.internal.**
#-dontwarn retrofit2.**
#-dontwarn okio.**
#-dontwarn org.eclipse.**
#
#-keep interface com.squareup.okhttp.** { *; }
#
#-keep class com.github.mikephil.charting.** { *; }
#-keep class com.squareup.okhttp.** { *; }
#-keep class retrofit2.** { *; }
#-keep class **$$ModuleAdapter
#-keep class **$$InjectAdapter
#-keep class **$$StaticInjection
#-keep class javax.inject.** { *; }
#-keep class com.google.gson.** { *; }
#
#-dontwarn dagger.internal.codegen.**
#-keepclassmembers,allowobfuscation class * {
#    @retrofit.http.* <methods>;
#    @javax.inject.* *;
#    @dagger.* *;
#    <init>();
#}
#
#-keep class dagger.* { *; }
#-keep class javax.inject.* { *; }
#-keep class * extends dagger.internal.Binding
#-keep class * extends dagger.internal.ModuleAdapter
#-keep class * extends dagger.internal.StaticInjection


