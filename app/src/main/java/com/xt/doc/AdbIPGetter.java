package com.xt.doc;

public class AdbIPGetter {
//    public static void main(String[] args) {
//        AndroidDebugBridge.init(false);
//        AndroidDebugBridge bridge = AndroidDebugBridge.createBridge();
//
//        // 等待设备连接
//        if (!waitForDevices(bridge)) {
//            System.err.println("No devices found.");
//            return;
//        }
//
//        // 获取连接的设备
//        IDevice[] devices = bridge.getDevices();
//        for (IDevice device : devices) {
//            System.out.println("Device: " + device);
//            System.out.println("IP Address: " + device.getProperty("ip.address"));
//        }
//
//        // 断开设备连接
//        AndroidDebugBridge.disconnectBridge();
//        AndroidDebugBridge.terminate();
//    }
//
//    private static boolean waitForDevices(AndroidDebugBridge bridge) {
//        int count = 0;
//        while (!bridge.hasInitialDeviceList() && count < 10) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            count++;
//        }
//        return bridge.hasInitialDeviceList();
//    }
}
