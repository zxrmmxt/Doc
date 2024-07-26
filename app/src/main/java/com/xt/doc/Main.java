package com.xt.doc;

import java.io.File;

public class Main {
    private static final String TAG = Main.class.getSimpleName();
    // 获取当前类所在的路径
    private static final String APK_DIR_8295 = "D:\\WORK\\ASProject\\Chery\\dev_t28\\LTNavigationService\\app\\build\\outputs\\apk\\e0x_btech_8295Inner\\debug";
    private static final String APK_DIR_T28 = "D:\\WORK\\ASProject\\Chery\\dev_t28\\LTNavigationService\\app\\build\\outputs\\apk\\t28_btechInner\\debug";
    private static final String APK_DIR_X9SP = "D:\\WORK\\ASProject\\Chery\\t1ej\\LTNavigationService\\app\\build\\outputs\\apk\\inner\\debug";
    private static final String APK_DIR_T18_DOWNLOAD = "C:\\Users\\p004827\\Downloads\\2-apk\\t18";
    private static final String APK_DIR_T18 = "D:\\WORK\\ASProject\\Chery\\dev_t28\\LTNavigationService\\app\\build\\outputs\\apk\\t18fl4_desayInner\\debug";


    public static void main(String[] args) {
        new Thread(() -> {

            // 输出路径
//            System.out.println("当前项目的根路径是: " + PROJECT_PATH);
//            renameDrawable();
//            JavaUtils.RuntimeUtils.exeAdb("adb devices");
            installApkAndStart(APK_DIR_8295);
//            installApk(APK_DIR_X9SP);
//            installApk(APK_DIR_T18);
//            installApk(APK_DIR_T28);
//            restart();
//            installApk("C:\\Users\\p004827\\Downloads\\2-apk\\LionLTNavigationService_inner.apk");
//            JavaUtils.RuntimeUtils.exeAdb("adb logcat -d -v time > "+JavaUtils.PROJECT_PATH+"\\log\\log.txt");
//            JavaUtils.RuntimeUtils.exeAdb("adb logcat *：W");
//            JavaUtils.RuntimeUtils.exeCmd();
        }).start();
    }

    private static void restart() {
        JavaUtils.RuntimeUtils.exeAdb("adb shell am force-stop com.lion.appfwk.navi");
        JavaUtils.RuntimeUtils.exeAdb("adb shell am start com.lion.appfwk.navi/.activity.StartupActivity");
    }

    private static void installApkAndStart(String apkPath) {
        if (JavaUtils.RuntimeUtils.exeAdb(JavaUtils.StrUtils.buildStr("adb install -r -d {?}", JavaUtils.FileUtils.getMatchedFilePath(new File(apkPath), ".apk")))) {
            System.out.println("安装成功");
        } else {
            System.out.println("安装失败");
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        restart();
    }

    private static void startApp() {
        JavaUtils.RuntimeUtils.exeAdb("adb shell am start -n com.lion.appfwk.navi/com.lion.appfwk.navi.activity.StartupActivity");
    }

    private static void renameDrawable() {
        rename("/drawable/cross/day/hud_sou", "/drawable/cross/day/lt_image_cross_direction");
        rename("/drawable/cross/night/hud_sou", "/drawable/cross/night/lt_image_cross_direction");
        rename("/drawable/tbt/day/hud_sou", "/drawable/tbt/day/lt_image_hud_sou");
        rename("/drawable/tbt/night/hud_sou", "/drawable/tbt/night/lt_image_hud_sou");
    }

    private static void rename(String oldName, String newName) {
        for (int i = 0; i < 72; i++) {
            if (!JavaUtils.FileUtils.renameFile(JavaUtils.PROJECT_PATH + oldName + i + ".png", JavaUtils.PROJECT_PATH + newName + i + ".png")) {
                System.out.println(JavaUtils.StrUtils.buildStr("失败序号:{?}", i));
            }
        }
    }
}
