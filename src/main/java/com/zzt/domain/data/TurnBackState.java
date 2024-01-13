package com.zzt.domain.data;

/**
 * @auther 朱振霆~
 */
public class TurnBackState extends Station{
    /**
     * 折返开始时间
     */
    private int startTime;
    /**
     * 折返结束时间
     */
    private int endTime;

    /**
     * 折返所需时间
     * @return
     */
    private int durTime;


    public TurnBackState(String metroNum, String stationName, int startTime, int endTime,int durTime) {
        super(metroNum,stationName);
        this.startTime = startTime;
        this.endTime = endTime;
        this.durTime = durTime;
    }

    public int getDurTime() {
        return durTime;
    }

    public void setDurTime(int durTime) {
        this.durTime = durTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
