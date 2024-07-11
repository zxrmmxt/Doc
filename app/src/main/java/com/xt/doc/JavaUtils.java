package com.xt.doc;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class JavaUtils {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static class FileUtils {
        /**
         * 写文件
         *
         * @param msg                写入的内容
         * @param fileRelPathAndName 绝对路径或相对地址,如:  aa/bb 表示 /sdcard/AutoSdkDemo/aa/bb 文件
         * @param append             是否是追加模式
         */
        public static void writeToFile(String msg, String fileRelPathAndName, boolean append) {
            if (isEmpty(fileRelPathAndName)) {
                return;
            }

            String targetFilePath = fileRelPathAndName;
            if (!fileRelPathAndName.startsWith("/")) { // 相对路径, 自动在 AutoSdkDemo/ 目录下创建文件
                targetFilePath = (fileRelPathAndName);
            }

            targetFilePath = targetFilePath
                    .replace("\\", "/")
                    .replace("//", "/");

            createFile(targetFilePath);

            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;
            try {
                File file = new File(targetFilePath);
                fileWriter = new FileWriter(file, append);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(msg + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                safetyClose(bufferedWriter);
                safetyClose(fileWriter);
            }
        }

        private static boolean isEmpty(String fileRelPathAndName) {
            return fileRelPathAndName == null || fileRelPathAndName.equals("");
        }

        public static void safetyClose(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 创建文件
         * 若存在同名文件/目录,则直接返回 true
         *
         * @param filepath 路径
         * @return 创建文件结果
         */
        public static boolean createFile(String filepath) {
            File file = new File(filepath);
            if (file.exists()) {
                return true;
            }
            boolean result = false;
            try {
                createDIR(file.getParent());

                result = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Logger.d(TAG, "createFile " + filepath + " , result = " + result);
            return result;
        }

        /**
         * 创建文件夹,若已存在则不重新创建
         *
         * @param dirpath 路径
         */
        public static boolean createDIR(String dirpath) {
            return createDIR(dirpath, false);
        }

        /**
         * 创建文件夹
         * 若文件存在,但非目录,则删除重建
         * 参考 {@link #createDIR(File, boolean)}
         */
        public static boolean createDIR(String dirpath, boolean forceRecreate) {
            return createDIR(new File(dirpath), forceRecreate);
        }

        /**
         * 创建文件夹
         * 若文件存在,但非目录,则删除重建
         *
         * @param targetFile    要创建的目标目录文件
         * @param forceRecreate 若目录已存在,是否要强制重新闯进(删除后,新建)
         * @return 是否创建成功
         */
        public static boolean createDIR(File targetFile, boolean forceRecreate) {
            if (targetFile == null) {
                return false;
            }

            if (targetFile.exists()) { // 存在同名文件
                boolean isDir = targetFile.isDirectory();
                if (!isDir) { // 非目录,删除以便创建目录
                    boolean result = targetFile.delete();
//                    Logger.d(TAG, "dirPath:" + targetFile.getAbsolutePath() + " is a file, delete it, result=" + result);
                } else if (forceRecreate) { // 强制删除目录
                    deleteDir(targetFile);
                } else { // 目录存在
                    return true;
                }
            }

            return targetFile.mkdirs();
        }

        /**
         * 删除指定目录
         * 若存在同名非目录文件,则不处理
         */
        public static boolean deleteDir(File dir) {
            if (dir == null || !dir.exists() || !dir.isDirectory())
                return true;
            for (File file : dir.listFiles()) {
                if (file.isFile()) {// 删除所有文件
                    file.delete();
                } else if (file.isDirectory()) { // 递归删除子目录
                    deleteDir(file);
                }
            }
            return dir.delete();// 删除空目录本身
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
         * 获取匹配到的文件的绝对路径
         *
         * @param directory 指定目录
         * @param matchStr  匹配词
         * @return 文件绝对路径
         */
        public static String getMatchedFilePath(File directory, String matchStr) {
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
    }

    public static class StrUtils {
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
    }

    public static class RuntimeUtils {
        public static void exeAdb(String command) {
            try {
                System.out.println(StrUtils.buildStr("command:{?}", command));
                Process process = Runtime.getRuntime().exec(command);

                // 读取并打印adb命令的输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    FileUtils.writeToFile(line, PROJECT_PATH + "\\log\\log.txt", false);
                }

                // 等待命令执行完成
                process.waitFor();
                System.out.println("Command executed successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * adb命令获取系统日志文件保存本地
         */
        public static void getLog() {
            try {
                Process exec1 = Runtime.getRuntime().exec("cmd.exe /c adb logcat | find \"com.lion.appfwk.navi\" >test_logcat.txt");
                Thread.sleep(2000);
                exec1.destroy();
           /* Thread.sleep(1000);
            Process exec = p.exec("cmd.exe /c adb kill-server");
            Thread.sleep(1000);
            exec.destroy();*/
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void exeCmd() {
            try {
                String ins_1 = "cmd.exe /c dir " + System.getProperty("user.home") + "&&";
                String ins_2 = "adb devices && adb logcat -c && adb logcat -g && adb logcat | find \"lt_navi\"";
                Process process = Runtime.getRuntime().exec(ins_1 + ins_2, null, null);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("Log:" + line);
                }
                bufferedReader.close();
                System.out.println("执行结果：" + process.waitFor());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ClipboardUtils {
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
}
