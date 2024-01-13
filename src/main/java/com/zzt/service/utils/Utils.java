package com.zzt.service.utils;

import com.zzt.domain.constant.Constant;
import com.zzt.domain.constraint.OnAndOffDutyStation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 朱振霆~
 */
public class Utils {
    /**
     * int时间转String时间
     *
     * @param intTime 时间（秒）
     * @return 时间（HH:mm:ss）
     */
    public static String intToStrTime(int intTime) {
        int oneDay = 24 * 60 * 60;
        String strTime;
        intTime = Math.abs(intTime);
        if (intTime > oneDay) {
            strTime = (intTime - oneDay) / 3600
                    + ":" + (((intTime - oneDay) % 3600) / 60 < 10 ? ("0" + (((intTime - oneDay) % 3600) / 60)) : (((intTime - oneDay) % 3600) / 60))
                    + ":" + (((intTime - oneDay) % 60) == 0 ? "00" : (((intTime - oneDay) % 60) < 10 ? ("0" + ((intTime - oneDay) % 60)) : ((intTime - oneDay) % 60)));
        } else {
            strTime = intTime / 3600
                    + ":" + ((intTime % 3600) / 60 < 10 ? ("0" + ((intTime % 3600) / 60)) : ((intTime % 3600) / 60))
                    + ":" + ((intTime % 60) == 0 ? "00" : ((intTime % 60) < 10 ? ("0" + (intTime % 60)) : (intTime % 60)));
        }
        return strTime;
    }

    /**
     * str时间转int时间
     *
     * @param strEnterTime HH:mm:ss
     * @return 时间（秒）
     */
    public static int strToIntTime(String strEnterTime) {
        int temp;
        String[] splitTime = strEnterTime.split(":");
        if (splitTime.length == 1) {
            temp = Integer.parseInt(splitTime[0]) * 3600;
        } else if (splitTime.length == 2) {
            temp = Integer.parseInt(splitTime[0]) * 3600 + Integer.parseInt(splitTime[1]) * 60;
        } else {
            temp = Integer.parseInt(splitTime[0]) * 3600 + Integer.parseInt(splitTime[1]) * 60 + Integer.parseInt(splitTime[2]);
        }
        if (temp < 3 * 3600) {
            return temp + 24 * 60 * 60;
        } else {
            return temp;
        }
    }

    /**
     * 临时匹配出退勤地点（需要注意顺序）
     */
    public static List<OnAndOffDutyStation> tempOnAndOffDutyStation() {
        ArrayList<OnAndOffDutyStation> onAndOffDutyStations = new ArrayList<>();

        OnAndOffDutyStation temp1 = new OnAndOffDutyStation();
        OnAndOffDutyStation temp2 = new OnAndOffDutyStation();
        OnAndOffDutyStation temp3 = new OnAndOffDutyStation();
        OnAndOffDutyStation temp4 = new OnAndOffDutyStation();
        OnAndOffDutyStation temp5 = new OnAndOffDutyStation();
        OnAndOffDutyStation temp6 = new OnAndOffDutyStation();
        OnAndOffDutyStation temp7 = new OnAndOffDutyStation();
//
        temp1.getStartWorkStation().add(Constant.CC_TJYZ);
        temp1.getStartWorkStation().add(Constant.STATION_LTSZ);
        temp1.getEndWorkStation().add(Constant.CC_TJYZ);
        temp1.getEndWorkStation().add(Constant.STATION_LTSZ);

        temp2.getStartWorkStation().add(Constant.CC_DJ);
        temp2.getStartWorkStation().add(Constant.STATION_JBJCZ);
        temp2.getStartWorkStation().add(Constant.CC_DJ);
        temp2.getStartWorkStation().add(Constant.STATION_JGLZ);
        temp2.getEndWorkStation().add(Constant.CC_DJ);
        temp2.getEndWorkStation().add(Constant.STATION_JGLZ);

        temp3.getStartWorkStation().add(Constant.CC_HCBL);
        temp3.getEndWorkStation().add(Constant.STATION_LTSZ);
        temp3.getEndWorkStation().add(Constant.STATION_JGLZ);

//        temp4.getStartWorkStation().add(Constant.STATION_LTSZ);
//        temp4.getStartWorkStation().add(Constant.STATION_JGLZ);
//        temp4.getEndWorkStation().add(Constant.STATION_HCBLCC);

        temp6.getStartWorkStation().add(Constant.STATION_LTSZ);
        temp6.getEndWorkStation().add(Constant.CC_HCBL);

        temp7.getStartWorkStation().add(Constant.STATION_JGLZ);
        temp7.getEndWorkStation().add(Constant.CC_HCBL);

        temp5.getStartWorkStation().add(Constant.CC_HCBL);
        temp5.getStartWorkStation().add(Constant.STATION_JRBZ);
        temp5.getEndWorkStation().add(Constant.CC_HCBL);
        temp5.getEndWorkStation().add(Constant.STATION_JRBZ);

        onAndOffDutyStations.add(temp1);
        onAndOffDutyStations.add(temp2);
        onAndOffDutyStations.add(temp3);
//        onAndOffDutyStations.add(temp4);
        onAndOffDutyStations.add(temp5);
        onAndOffDutyStations.add(temp6);
        onAndOffDutyStations.add(temp7);
        return onAndOffDutyStations;
    }

    /**
     * 临时匹配出入段时间
     *
     * @return
     */
    public static Map<String, Integer> tempInAndOutTrainDepot() {
        Map<String, Integer> tempMap = new HashMap<>(8);
        tempMap.put(Constant.CC_TJYZ, 15 * 60);
        tempMap.put(Constant.CC_DJ, 10 * 60);
        tempMap.put(Constant.CC_HCBL, 15 * 60);
        return tempMap;
    }
}
