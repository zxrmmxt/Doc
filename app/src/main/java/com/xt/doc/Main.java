package com.xt.doc;

import java.io.File;

public class Main {
    private static final String TAG = Main.class.getSimpleName();
    // 获取当前类所在的路径
    private static final String APK_DIR_8295 = "D:\\WORK\\ASProject\\Chery\\dev_t28\\LTNavigationService\\app\\build\\outputs\\apk\\e0x_btech_8295Inner\\debug";
    private static final String APK_DIR_T28 = "D:\\WORK\\ASProject\\Chery\\dev_t28\\LTNavigationService\\app\\build\\outputs\\apk\\t28_btechInner\\debug";
    private static final String APK_DIR_X9SP = "D:\\WORK\\ASProject\\Chery\\t1ej\\LTNavigationService\\app\\build\\outputs\\apk\\inner\\debug";
    private static final String APK_DIR_T18 = "C:\\Users\\p004827\\Downloads\\2-apk\\t18";

    public static void main(String[] args) {
        new Thread(() -> {

            // 输出路径
//            System.out.println("当前项目的根路径是: " + PROJECT_PATH);
//            renameDrawable();
//            installApk(APK_DIR_8295);
//            installApk(APK_DIR_X9SP);
            installApk(APK_DIR_T18);
//            installApk(APK_DIR_T28);
//            installApk("C:\\Users\\p004827\\Downloads\\2-apk\\LionLTNavigationService_inner.apk");
//            JavaUtils.RuntimeUtils.exeAdb("adb logcat -d -v time");
//            JavaUtils.RuntimeUtils.exeAdb("adb logcat *：W");
//            JavaUtils.RuntimeUtils.exeCmd();
        }).start();
    }

    private static void installApk(String apkPath) {
        JavaUtils.RuntimeUtils.exeAdb(JavaUtils.StrUtils.buildStr("adb install -r -d {?}", JavaUtils.FileUtils.getMatchedFilePath(new File(apkPath), ".apk")));
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
