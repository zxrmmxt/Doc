package com.xt.doc;

import java.io.File;

public class RenameFile {
    private static final String TAG = RenameFile.class.getSimpleName();
    // 获取当前类所在的路径
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String APK_DIR = "D:\\WORK\\ASProject\\Chery\\dev_t28\\LTNavigationService\\app\\build\\outputs\\apk\\e0x_btech_8295Inner\\debug";

    public static void main(String[] args) {
        new Thread(() -> {

            // 输出路径
//            System.out.println("当前项目的根路径是: " + PROJECT_PATH);
//            renameDrawable();
//            System.out.println(Utils.buildLogContent("apk安装指令:[adb install -r -d {?}]", Utils.getFilePath(new File(APK_DIR), ".apk")));
            Utils.exeAdb(Utils.buildStr("adb install -r -d {?}", Utils.getFilePath(new File(APK_DIR), ".apk")));

        }).start();
    }

    private static void renameDrawable() {
        rename("/drawable/cross/day/hud_sou", "/drawable/cross/day/lt_image_cross_direction");
        rename("/drawable/cross/night/hud_sou", "/drawable/cross/night/lt_image_cross_direction");
        rename("/drawable/tbt/day/hud_sou", "/drawable/tbt/day/lt_image_hud_sou");
        rename("/drawable/tbt/night/hud_sou", "/drawable/tbt/night/lt_image_hud_sou");
    }

    private static void rename(String oldName, String newName) {
        for (int i = 0; i < 72; i++) {
            if (!Utils.renameFile(PROJECT_PATH + oldName + i + ".png", PROJECT_PATH + newName + i + ".png")) {
                System.out.println(Utils.buildStr("失败序号:{?}", i));
            }
        }
    }
}
