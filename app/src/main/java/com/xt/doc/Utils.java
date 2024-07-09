package com.xt.doc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Utils {
    public static int getMeasuredWidth(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredWidth();
    }

    public static BitmapFactory.Options getImageWidthHeight(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //返回的bitmap为空，options.outHeight已计算
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options); // 此时返回的bitmap为null
        //options.outHeight为原始图片宽高
        return options;
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

    public static String buildStr(String msg, Object... params) {
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

    public static void listFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file);
                } else {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 搜索获取文件绝对路径
     *
     * @param directory 指定目录
     * @param matchStr  匹配词
     * @return 文件绝对路径
     */
    public static String getFilePath(File directory, String matchStr) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file);
                } else {
                    if (file.getName().contains(matchStr)) {
                        return file.getAbsolutePath();
                    }
                }
            }
        }
        return "";
    }

    public static void exeAdb(String command) {
        try {
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);

            // 读取并打印adb命令的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令执行完成
            process.waitFor();
            System.out.println("Command executed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy() {
        // 获取系统剪贴板
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//
//        // 要复制的文本
//        String textToCopy = "这是要复制到剪贴板的文本";
//
//        // 将文本转换为Transferable对象
//        StringSelection stringSelection = new StringSelection(textToCopy);
//
//        // 将文本放入剪贴板
//        clipboard.setContents(stringSelection, null);
//
//        System.out.println("文本已复制到剪贴板。");
    }
}
