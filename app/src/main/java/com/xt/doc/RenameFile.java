package com.xt.doc;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;

public class RenameFile {
    private static final String TAG = RenameFile.class.getSimpleName();

    public static void main(String[] args) {
        new Thread(() -> {
            // 获取当前类所在的路径
            String projectPath = System.getProperty("user.dir");
            // 输出路径
            System.out.println("当前类的路径是: " + projectPath);
            for (int i = 2; i < 72; i++) {
                rename(projectPath + "/drawable/lt_image_hud_sou" + i + ".png", projectPath + "/drawable/lt_image_cross_direction" + i + ".png");
            }
        }).start();
    }

    public static boolean rename(@NonNull String oriFilePath, @NonNull String destFilePath) {
        File srcFile = new File(oriFilePath);
        if (!srcFile.exists()) {
            return false;
        }

        File dest = new File(destFilePath);
        dest.getParentFile().mkdirs();
        return srcFile.renameTo(dest);
    }
}
