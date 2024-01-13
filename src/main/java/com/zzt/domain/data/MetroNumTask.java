package com.zzt.domain.data;

import java.util.List;

/**
 * @auther 朱振霆~
 */
public class MetroNumTask extends Train{

//    /**
//     * 行驶里程
//     */
//    double travelledDistance;

    /**
     * 到站时刻表
     */
    List<ArrivalStationTime> arrivalSchedule;

    public MetroNumTask(String metroNum, List<ArrivalStationTime> arrivalSchedule) {
        super(metroNum);
        this.arrivalSchedule = arrivalSchedule;
    }

    public MetroNumTask() {

    }

    public void init(){
        arrivalSchedule.forEach((e) -> e.setMetroNum(getMetroNum()));
        arrivalSchedule.forEach((e) -> e.setTrainNum(getTrainNum()));
    }

    public List<ArrivalStationTime> getArrivalSchedule() {
        return arrivalSchedule;
    }

    public void setArrivalSchedule(List<ArrivalStationTime> arrivalSchedule) {
        this.arrivalSchedule = arrivalSchedule;
    }
}
