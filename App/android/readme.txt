



Update:
1.修改manifest
    android:versionCode="1"
    android:versionName="1.0"
2.修改jsapp/src/main/conf/InitStore.js
        _init_: {
            version: {
                current: 'v1.0.0'
            }
        }
3.修改App/android/app/build.gradle
    defaultConfig {
        versionCode 1
        versionName "1.0"
    }