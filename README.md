project `build.gralde`

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } // this line
  }
}
```

app `build.gradle`

```gradle
dependencies {
  implementation 'com.github.mumayank:AirBgTaskProjectNew:LATEST_VERSION' // this line
}
```
where LATEST_VERSION is [![](https://jitpack.io/v/mumayank/AirBgTaskProjectNew.svg)](https://jitpack.io/#mumayank/AirBgTaskProjectNew)

Use

 - Define instance variable in the activity: `var airBgTask = AirBgTask()`
 - In `onCreate()`, call the variable's method by the same name: `airBgTask.onCreate(this)`
 - In `onDestroy()`, call the variable's method by the same name: `airBgTask.onDestroy()`
 - Wherever you want, define the background task: `airBgTask.define()`
 - Wherever you want, execute the background task: `airBgTask.execute()`
 - The only condition is to call `.define()` before `.execute()` (which is obvious)
