package com.zzt.domain.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @auther 朱振霆~
 */
public class SingleTrainTask extends MetroNumTask {
    /**
     * 当天本次车底号首站
     */
    private String startStation;
    /**
     * 首战到站时间
     */
    private Integer startTime;
    /**
     * 当天本次车底号末站
     */
    private String endStation;
    /**
     * 末站结束时间
     */
    private Integer endTime;
    /**
     * 车底时刻表(按车次分开的全部数据)
     */
    private List<MetroNumTask> metroNumTasks;
    /**
     * 临时出段时间
     */
    private String tempOutDepot;
    /**
     * 临时入段时间
     */
    private String tempInDepot;

    public void init01(Map<String, String> stationToDepotName) {
        if (metroNumTasks!=null) {
            for (MetroNumTask metroTask : metroNumTasks) {
                //每个车次号（每一行）的所有时刻表,按照进站时间从早到晚进行排序
                metroTask.getArrivalSchedule().sort(Comparator.comparing(ArrivalStationTime::getIntEnterTime));
                arrivalSchedule.addAll(metroTask.getArrivalSchedule());
            }
            //按照进站时间从早到晚的顺序排列。
            arrivalSchedule.sort(Comparator.comparing(ArrivalStationTime::getIntEnterTime));
            if (!arrivalSchedule.isEmpty()) {
                startStation = arrivalSchedule.get(0).getStationName();
                startTime = arrivalSchedule.get(0).getIntEnterTime();
                endStation = arrivalSchedule.get(arrivalSchedule.size() - 1).getStationName();
                endTime = arrivalSchedule.get(arrivalSchedule.size() - 1).getIntExitTime();
                tempInDepot = stationToDepotName.get(arrivalSchedule.get(0).getStationName());
                tempOutDepot = stationToDepotName.get(arrivalSchedule.get(arrivalSchedule.size() - 1).getStationName());
            }
//            MetroNumTask firstMetroNumTask = metroNumTasks.get(0);
//            MetroNumTask lastMetroNumTask = metroNumTasks.get(metroNumTasks.size() - 1);
//            startStation = firstMetroNumTask.getArrivalSchedule().get(0).getStationName();
//            startTime = firstMetroNumTask.getArrivalSchedule().get(0).getIntEnterTime();
//            endStation  = lastMetroNumTask.getArrivalSchedule().get(lastMetroNumTask.getArrivalSchedule().size()-1).getStationName();
//            endTime  = lastMetroNumTask.getArrivalSchedule().get(lastMetroNumTask.getArrivalSchedule().size()-1).getIntExitTime();
//            tempOutDepot = stationToDepotName.get(firstMetroNumTask.getArrivalSchedule().get(0).getStationName());
//            tempInDepot = stationToDepotName.get(lastMetroNumTask.getArrivalSchedule().get(lastMetroNumTask.getArrivalSchedule().size()-1).getStationName());



        }
    }


    public SingleTrainTask() {
        metroNumTasks = new ArrayList<>();
        arrivalSchedule = new ArrayList<>();
    }



    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public List<MetroNumTask> getMetroNumTasks() {
        return metroNumTasks;
    }

    public void setMetroNumTasks(List<MetroNumTask> metroNumTasks) {
        this.metroNumTasks = metroNumTasks;
    }
}
