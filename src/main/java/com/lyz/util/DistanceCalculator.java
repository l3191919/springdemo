package com.lyz.util;

public class DistanceCalculator {

    private static final int EARTH_RADIUS_KM = 6371; // 地球半径，单位：千米

    /**
     * 计算两点之间的距离（单位：千米）
     *
     * @param lat1 纬度1
     * @param lon1 经度1
     * @param lat2 纬度2
     * @param lon2 经度2
     * @return 两点之间的距离
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        return distance;
    }

    public static void main(String[] args) {
        double lat1 = 39.9075; // 北京的纬度
        double lon1 = 116.39723; // 北京的经度
        double lat2 = 31.2304; // 上海的纬度
        double lon2 = 121.4737; // 上海的经度

        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        System.out.println("两地之间的距离是：" + distance + " 千米");
    }
}