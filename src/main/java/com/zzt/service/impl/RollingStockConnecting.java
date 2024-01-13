package com.zzt.service.impl;

import com.zzt.domain.constraint.Constraints;
import com.zzt.domain.data.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @auther 朱振霆~
 */
public class RollingStockConnecting {
    private List<RollingStockTask> rollingStockPlan;
    int rollingStockNum;
    private final List<TurnBackState> turnBackStateList = new ArrayList<>(400);

    /**
     * 输出车底周转(接续)计划
     *
     * @return
     */

    public List<RollingStockTask> generateRollingStockPlan(List<MetroNumTask> metroNumTasks, List<LineInformationTableRowData> lineInfoData, Constraints constraint) {
        //出入段时间
        Map<String, Integer> inOutDepotTime = constraint.getInOutDepotTime();
        //线路信息
        Map<String, LineInformationTableRowData> lineInfoMap = lineInfoData.stream().collect(Collectors.toMap(LineInformationTableRowData::getStationName, (e) -> e));
        //连接车辆段的车站名->连接的车辆段名
        Map<String, String> stationToDepotName = lineInfoData.stream().filter(
                LineInformationTableRowData::getConnectingDepot).collect(
                Collectors.toMap(
                        LineInformationTableRowData::getStationName, LineInformationTableRowData::getConnectingDepotName));

        //时刻表数据,按照车底号分类
        Map<String, List<MetroNumTask>> trainToMetroNumTasksMap = metroNumTasks.stream().collect(Collectors.groupingBy(MetroNumTask::getTrainNum));

        //实现按车底分类
        SingleTrainTask singleTrainTask;
        List<SingleTrainTask> singleTrainTasks = new ArrayList<>();
        for (Map.Entry<String, List<MetroNumTask>> entry : trainToMetroNumTasksMap.entrySet()) {
            String k = entry.getKey();
            List<MetroNumTask> v = entry.getValue();
            singleTrainTask = new SingleTrainTask();
            singleTrainTask.setTrainNum(k);
            singleTrainTask.getMetroNumTasks().addAll(v);
            singleTrainTasks.add(singleTrainTask);
        }

        for (SingleTrainTask task : singleTrainTasks) {
            task.init01(stationToDepotName);
        }
        //车次接续
        RollingStockTask rollingStockTask = null;
        //StockTask stockTask;
        rollingStockPlan = new ArrayList<>();
        int startIndex;
        boolean inAndOutDepotFlag;
        //遍历每一车底
        for (SingleTrainTask trainTask : singleTrainTasks) {
            startIndex = 0;
            inAndOutDepotFlag = true;
            //每个车底对应的时刻表
            List<ArrivalStationTime> arrivalStationTimeList = trainTask.getArrivalSchedule();
            //车底计划集合
            List<StockTask> rollingStockTaskList = new ArrayList<>();
            rollingStockTask = new RollingStockTask(constraint);
            // stockTask = new StockTask();
            int trainNumber = 0;
            for (int i = 0; i < arrivalStationTimeList.size() - 1; i++) {
                int j = i + 1;
                int end = 0;
                if (arrivalStationTimeList.get(arrivalStationTimeList.size() - 1) != null) {
                    end = arrivalStationTimeList.size() - 1;
                }
                //首车站是否包含车辆段名
                boolean isStationToDepotName = stationToDepotName.containsKey(arrivalStationTimeList.get(startIndex).getStationName());
                //尾车站是否包含车辆段名
                boolean isEndStationToDepotName = stationToDepotName.containsKey(arrivalStationTimeList.get(end).getStationName());


                //=======出段===========
                if (inAndOutDepotFlag && isStationToDepotName) {
                    String tempDepotName = stationToDepotName.get(arrivalStationTimeList.get(startIndex).getStationName());
                    int tempStartTime = arrivalStationTimeList.get(startIndex).getIntEnterTime() - inOutDepotTime.get(tempDepotName);
                    //车底计划编号
                    rollingStockTask.setRollingStockNum(++rollingStockNum);
                    rollingStockTask.setTrainNum(trainTask.getTrainNum());
                    //rollingStockTask.setMetroNum(arrivalStationTimeList.get(i).getMetroNum());
                    rollingStockTask.setOutDepot(tempDepotName);
                    rollingStockTask.setStartStation(arrivalStationTimeList.get(startIndex).getStationName());
                    StockTask stockTask = new StockTask();
                    stockTask.setPlan(rollingStockTask.getOutDepot());
                    stockTask.setOutDepotTime(inOutDepotTime.get(tempDepotName));
                    stockTask.setDurTime(inOutDepotTime.get(tempDepotName));

                    rollingStockTaskList.add(stockTask);
                    rollingStockTask.setRollingStockTaskList(rollingStockTaskList);
                    rollingStockTask.setIntStartTime(tempStartTime);
                    rollingStockTask.setOutDepotTime(inOutDepotTime.get(tempDepotName));

                    //rollingStockTask.setIsOutDepot(true);
                    inAndOutDepotFlag = false;

                }
                int flag = 0;
                //=======折返=========
                if (arrivalStationTimeList.get(i).getStationName().equals(arrivalStationTimeList.get(j).getStationName())
                        && arrivalStationTimeList.get(i).getIntEnterTime() != arrivalStationTimeList.get(j).getIntEnterTime()) {

                    String plan_i = arrivalStationTimeList.get(i).getMetroNum();
                    StockTask stockTask = new StockTask();
                    stockTask.setPlan(plan_i);
                    stockTask.setStartTime(arrivalStationTimeList.get(i).getIntEnterTime());
                    stockTask.setEndTime(arrivalStationTimeList.get(j).getIntExitTime());
                    stockTask.setDurTime(stockTask.getEndTime()-stockTask.getStartTime());
                    rollingStockTaskList.add(stockTask);
                    //记录最后一个折返的位置
                    flag = j;

                        // 检查是否已经添加过这个plan值,接续车次号
//                    if (!addedPlans.contains(plan_i)) {
//                        StockTask stockTask = new StockTask();
//                        stockTask.setPlan(plan_i);
//                        stockTask.setDurTime(arrivalStationTimeList.get(j).getIntExitTime()-arrivalStationTimeList.get(i).getIntEnterTime());
//                        rollingStockTaskList.add(stockTask);
//                        addedPlans.add(plan_i);
//                    }
//                    // 检查是否已经添加过这个plan值
//                    if (!addedPlans.contains(plan_j)) {
//                        StockTask stockTask_j = new StockTask();
//                        stockTask_j.setPlan(plan_j);
//                        stockTask_j.setDurTime(arrivalStationTimeList.get(j).getIntExitTime()-arrivalStationTimeList.get(j).getIntEnterTime());
//                        rollingStockTaskList.add(stockTask_j);
//                        addedPlans.add(plan_j);
//                    }
//                    i++;

                }
                //如果
                if (j == end) {
                    String tempDepotName = stationToDepotName.get(arrivalStationTimeList.get(end).getStationName());
                    int tempEndTime = arrivalStationTimeList.get(end).getIntExitTime() + inOutDepotTime.get(tempDepotName);
                    StockTask stockTask1 = new StockTask();
                    stockTask1.setPlan(arrivalStationTimeList.get(j).getMetroNum());
                    stockTask1.setStartTime(arrivalStationTimeList.get(j).getIntEnterTime());
                    stockTask1.setEndTime(tempEndTime);
                    stockTask1.setDurTime(tempEndTime-stockTask1.getStartTime());
                    stockTask1.setInDepotTime(tempEndTime-stockTask1.getStartTime());
                    rollingStockTaskList.add(stockTask1);
                }
                //=======入段===========
                if (!inAndOutDepotFlag && isEndStationToDepotName && j == end) {
                    String tempDepotName = stationToDepotName.get(arrivalStationTimeList.get(end).getStationName());
                    int tempEndTime = arrivalStationTimeList.get(end).getIntExitTime() + inOutDepotTime.get(tempDepotName);
                    //车底计划编号
                    rollingStockTask.setTrainNum(trainTask.getTrainNum());
                    //rollingStockTask.setMetroNum(arrivalStationTimeList.get(i).getMetroNum());
                    rollingStockTask.setInDepot(tempDepotName);
                    rollingStockTask.setEndStation(arrivalStationTimeList.get(end).getStationName());
                    StockTask stockTask = new StockTask();
                    stockTask.setPlan(rollingStockTask.getInDepot());
                    stockTask.setInDepotTime(inOutDepotTime.get(tempDepotName));
                   //stockTask.setDurTime(inOutDepotTime.get(tempDepotName));

                    rollingStockTaskList.add(stockTask);
                    rollingStockTask.setRollingStockTaskList(rollingStockTaskList);
                    rollingStockTask.setIntEndTime(tempEndTime);
                    rollingStockTask.setInDepotTime(inOutDepotTime.get(tempDepotName));
                    rollingStockTask.setTrainNumber(rollingStockTaskList.size());
                    //rollingStockTask.setIsInDepot(true);
                }
                //=======上下行=========
//                stockTask.setPlan(arrivalStationTimeList.get(i).getMetroNum());
//                if (!rollingStockTaskList.stream().anyMatch(task->task.getPlan().equals(stockTask.getPlan()))) {
//                    rollingStockTaskList.add(stockTask);
//                }

            }
            rollingStockPlan.add(rollingStockTask);
        }

        return rollingStockPlan;
    }
}