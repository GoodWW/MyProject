package com.xunman.yibenjiapu.utils;

/**
 * Created by lwk .
 * 计算两个时间的差值
 */
public class TimeUtils {
    /*
    *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
    * 根据差值返回多长之间前或多长时间后
    * */
    public String getDistanceTime(long time1, long time2) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        String flag;
        LogUtils.e("time",time1+"+++"+time2);
        if (time1 < time2) {
            flag = "前";
        } else {
            flag = "后";
        }
        long time = time2 - time1;//秒
        LogUtils.e("time",time+"");
        if (time > nd)
            flag = time / nd + "天" + flag;
        else if (time > nh)
            flag = time / nh + "小时" + flag;
        else if (time > nm)
            flag = time / nm + "分钟" + flag;
        else if (time > ns)
            flag = time / ns + "秒" + flag;
        else
            flag = "刚刚";


        return flag;
    }
}
