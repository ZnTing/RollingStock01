package com.zzt.domain.data;

/**
 * @auther 朱振霆~
 */
public class Station extends Train {
    /**
     * 车站名
     */
    private String stationName;

    public Station() {
    }

    public Station(String metroNum, String stationName) {
        super(metroNum);
        this.stationName = stationName;
    }

    //    public Station(String metroNum, String stationName) {
//        super(metroNum);
//        this.stationName = stationName;
//    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
