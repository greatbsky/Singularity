



------------------------------------Version Update:
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





------------------------------------编译启动

gradlew assembleDebug

E:\MyWork6\Android>emulator -list-avds
Android_TV_720p_API_25
Nexus_6_API_25
ReactNative


emulator -avd Android_TV_720p_API_25

E:\MyWork6\Android>adb devices
List of devices attached
emulator-5554   device
N7M6R15603003118        device

adb -s emulator-5554 install tv\build\outputs\apk\tv-debug.apk


#手机设备运行
adb -d install app\build\outputs\apk\app-debug.apk







------------------------------------发布流程
1.生成秘钥
keytool -genkey -v -keystore signer/release.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias geniuskey

2.生成未签名APK
gradlew assembleRelease

3.对齐未签署的APK
zipalign -v -p 4 app\build\outputs\apk\app-release-unsigned.apk app\build\outputs\apk\app-release-unsigned-aligned.apk

4.使用私钥签署 APK
apksigner sign --ks signer/release.keystore --out app\build\outputs\apk\app-release.apk app\build\outputs\apk\app-release-unsigned-aligned.apk


5.验证APK是否已签署
apksigner verify app\build\outputs\apk\app-release.apk



------------------------------------配置Gradle以签署APK
android {
    ...
    defaultConfig { ... }
    signingConfigs {
        release {
            storeFile file("my-release-key.jks")
            storePassword "password"
            keyAlias "my-alias"
            keyPassword "password"
        }
    }
    buildTypes {
        release {
			shrinkResources true
            minifyEnabled true
            signingConfig signingConfigs.release

            ...
        }
    }
}

E:\MyWork6\Android>gradlew clean assembleRelease -info







------------------------------------ClassyShark反编译
java -jar ClassyShark.jar   //可视化界面，查看大概结构，所用到的库
------------------------------------APKTool反编译
java -jar apktool_2.3.0.jar d 1834431.apk  //获得素材
java -jar apktool_2.3.0.jar b 1834431   //重新打包，测试安装不行
F:\apk\apktool\1834431dex>d2j-dex2jar.bat classes.dex   //得到class文件，jd-gui可查看详细代码





------------------------------------其他















