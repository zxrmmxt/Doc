package com.xt.doc;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RenameFile {
    private static final String TAG = RenameFile.class.getSimpleName();
    // 获取当前类所在的路径
    private static final String PROJECT_PATH = System.getProperty("user.dir");

    public static void main(String[] args) {
        new Thread(() -> {

            // 输出路径
            System.out.println("当前项目的根路径是: " + PROJECT_PATH);
            rename("/drawable/cross/day/hud_sou","/drawable/cross/day/lt_image_cross_direction");
            rename("/drawable/cross/night/hud_sou","/drawable/cross/night/lt_image_cross_direction");
            rename("/drawable/tbt/day/hud_sou","/drawable/tbt/day/lt_image_hud_sou");
            rename("/drawable/tbt/night/hud_sou","/drawable/tbt/night/lt_image_hud_sou");
        }).start();
    }

    private static void rename(String oldName, String newName) {
        for (int i = 0; i < 72; i++) {
            if (!renameFile(PROJECT_PATH + oldName + i + ".png", PROJECT_PATH + newName + i + ".png")) {
                System.out.println(buildLogContent("失败序号:{?}", i));
            }
        }
    }

    public static boolean renameFile(@NonNull String oriFilePath, @NonNull String destFilePath) {
        File srcFile = new File(oriFilePath);
        if (!srcFile.exists()) {
            return false;
        }

        File dest = new File(destFilePath);
        dest.getParentFile().mkdirs();
        return srcFile.renameTo(dest);
    }

    private static String buildLogContent(String msg, Object... params) {
        if (msg == null) {
            return "";
        }
        if (params == null) {
            return msg;
        }
        StringBuilder bf = new StringBuilder();
        String[] msgArray = msg.split("\\{\\?\\}");
        int minLen = Math.min(msgArray.length, params.length);
        for (int i = 0; i < minLen; i++) {
            Object param = params[i];
            bf.append(msgArray[i]).append(param);
        }
        for (int i = minLen; i < msgArray.length; i++) {
            bf.append(msgArray[i]);
        }
        return bf.toString();
    }
}
