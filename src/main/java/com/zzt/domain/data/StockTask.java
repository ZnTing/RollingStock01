package com.zzt.domain.data;

import com.zzt.service.utils.Utils;

/**
 * @auther 朱振霆~
 */
public class StockTask {
    /**
     * 接续节点
     */
    private String plan;

    /**
     * 折返开始时间
     */
    private int startTime;
    private String startTimeStr;
    /**
     * 折返结束时间
     */
    private int endTime;
    private String endTimeStr;

    /**
     * 当前站i到下个站j折返所需时间
     * @return
     */
    private int durTime;

    /**
     * 出段需要时间
     */
    private int inDepotTime;
    /**
     * 入段需要时间
     */
    private int outDepotTime;


    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
        this.startTimeStr = Utils.intToStrTime(startTime);
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
        this.endTimeStr = Utils.intToStrTime(endTime);
    }

    public int getDurTime() {
        return durTime;
    }

    public void setDurTime(int durTime) {
        this.durTime = durTime;
    }

    public int getInDepotTime() {
        return inDepotTime;
    }

    public void setInDepotTime(int inDepotTime) {
        this.inDepotTime = inDepotTime;
    }

    public int getOutDepotTime() {
        return outDepotTime;
    }

    public void setOutDepotTime(int outDepotTime) {
        this.outDepotTime = outDepotTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
