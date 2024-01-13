package com.zzt.domain.data;

import com.zzt.domain.constraint.Constraints;
import com.zzt.service.utils.Utils;

import java.util.List;

/**
 * @auther 朱振霆~
 * 车次任务集合
 */
public class RollingStockTask extends Train{
    private Constraints constraint;
    /**
     * 车底计划编号
     */
    private int rollingStockNum;
    /**
     * 默认按照时间顺序排序
     */
    //private int order;

    /**
     * 开始时间
     */
    private int intStartTime;
    private String strStartTime;
    /**
     * 结束时间
     */
    private int intEndTime;
    private String strEndTime;
    /**
     * 出段需要时间
     */
    private int inDepotTime;
    /**
     * 入段需要时间
     */
    private int outDepotTime;

    /**
     *持续时间
     */
    //private int duration;
    /**
     * 始发车站
     */
    private String startStation;
    /**
     * 终到车站
     */
    private String endStation;
    /**
     * 距离始发车站最近的车辆段（出段）
     */
    private String inDepot;
    /**
     * 距离终点车站最近的车辆段（入段）
     */
    private String outDepot;
    /**
     * 是否为出库
     */
    //private boolean isOutDepot;
    /**
     * 是否为入库
     */
    //private boolean isInDepot;
    /**
     * 是否有折返站
     */
    //private boolean turnBackTask;
//    /**
//     * 折返站之后的车次号
//     */
//    private String turnBackStation;
//    /**
//     * 折返的站名
//     */
//    private String metroNumAfterTurnBackStation;
//    /**
//     * 折返开始时间
//     */
//    private int intTurnBackStartTime;
//    private String strTurnBackStartTime;
//    /**
//     * 折返结束时间
//     */
//    private int intTurnBackEndTime;
//    private String strTurnBackEndTime;
//    /**
//     * 折返时间
//     */
//    private int intTurnBackTime;
    /**
     *每个车底的车次数量
     */
    private int trainNumber;
    /**
     * 接续集合（车辆段-车次号-车辆段）
     * @param constraint
     */
    private List<StockTask> rollingStockTaskList;

    public RollingStockTask() {
    }

    public RollingStockTask(Constraints constraint) {
        this.constraint = constraint;
    }

    public Constraints getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraints constraint) {
        this.constraint = constraint;
    }

    public int getRollingStockNum() {
        return rollingStockNum;
    }

    public void setRollingStockNum(int rollingStockNum) {
        this.rollingStockNum = rollingStockNum;
    }

//    public int getOrder() {
//        return order;
//    }
//
//    public void setOrder(int order) {
//        this.order = order;
//    }

    public int getIntStartTime() {
        return intStartTime;
    }

    public void setIntStartTime(int intStartTime) {
        this.intStartTime = intStartTime;
        this.strStartTime = Utils.intToStrTime(intStartTime);
    }

    public String getStrStartTime() {
        return strStartTime;
    }

    public void setStrStartTime(String strStartTime) {
        this.strStartTime = strStartTime;
    }

    public int getIntEndTime() {
        return intEndTime;
    }

    public void setIntEndTime(int intEndTime) {
        this.intEndTime = intEndTime;
        this.strEndTime = Utils.intToStrTime(intEndTime);
    }

    public String getStrEndTime() {
        return strEndTime;
    }

    public void setStrEndTime(String strEndTime) {
        this.strEndTime = strEndTime;
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
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getInDepot() {
        return inDepot;
    }

    public void setInDepot(String inDepot) {
        this.inDepot = inDepot;
    }

//    public boolean isTurnBackTask() {
//        return turnBackTask;
//    }
//
//    public void setTurnBackTask(boolean turnBackTask) {
//        this.turnBackTask = turnBackTask;
//    }

//    public String getTurnBackStation() {
//        return turnBackStation;
//    }
//
//    public void setTurnBackStation(String turnBackStation) {
//        this.turnBackStation = turnBackStation;
//    }
//
//    public String getMetroNumAfterTurnBackStation() {
//        return metroNumAfterTurnBackStation;
//    }

//    public void setMetroNumAfterTurnBackStation(String metroNumAfterTurnBackStation) {
//        this.metroNumAfterTurnBackStation = metroNumAfterTurnBackStation;
//        if (metroNumAfterTurnBackStation != null) {
//            this.turnBackTask = true;
//        }
//    }

//    public int getIntTurnBackStartTime() {
//        return intTurnBackStartTime;
//    }
//
//    public void setIntTurnBackStartTime(int intTurnBackStartTime) {
//        this.intTurnBackStartTime = intTurnBackStartTime;
//        this.strTurnBackStartTime = Utils.intToStrTime(intTurnBackStartTime);
//    }
//
//    public String getStrTurnBackStartTime() {
//        return strTurnBackStartTime;
//    }
//
//    public void setStrTurnBackStartTime(String strTurnBackStartTime) {
//        this.strTurnBackStartTime = strTurnBackStartTime;
//    }
//
//    public int getIntTurnBackEndTime() {
//        return intTurnBackEndTime;
//    }
//
//    public void setIntTurnBackEndTime(int intTurnBackEndTime) {
//        this.intTurnBackEndTime = intTurnBackEndTime;
//        this.strTurnBackEndTime = Utils.intToStrTime(intTurnBackEndTime);
//    }
//
//    public String getStrTurnBackEndTime() {
//        return strTurnBackEndTime;
//    }
//
//    public void setStrTurnBackEndTime(String strTurnBackEndTime) {
//        this.strTurnBackEndTime = strTurnBackEndTime;
//    }
//
//    public int getIntTurnBackTime() {
//        return intTurnBackTime;
//    }
//
//    public void setIntTurnBackTime(int intTurnBackTime) {
//        this.intTurnBackTime = intTurnBackTime;
//    }


    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getOutDepot() {
        return outDepot;
    }

    public void setOutDepot(String outDepot) {
        this.outDepot = outDepot;
    }

//    public boolean getIsOutDepot() {
//        return isOutDepot;
//    }
//
//    public void setIsOutDepot(boolean outDepot) {
//        isOutDepot = outDepot;
//    }
//
//    public boolean getIsInDepot() {
//        return isInDepot;
//    }
//
//    public void setIsInDepot(boolean inDepot) {
//        isInDepot = inDepot;
//    }

    public List<StockTask> getRollingStockTaskList() {
        return rollingStockTaskList;
    }

    public void setRollingStockTaskList(List<StockTask> rollingStockTaskList) {
        this.rollingStockTaskList = rollingStockTaskList;
    }
}
