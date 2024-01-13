package com.zzt.domain.data;

/**
 * @auther 朱振霆~
 */
public class LineInformationTableRowData extends Station{

    /**
     * 以鱼洞站为起点的距离
     */
    private int distance;
    /**
     * 车站序号
     */
    private int stationNum;
    /**
     * 是否连接车辆段
     */
    private boolean isConnectingDepot;
    /**
     * 连接车辆段名
     */
    private String connectingDepotName;
    /**
     * 是否是上行轮乘点
     */
    private boolean isUpRotationPoint;
    /**
     * 是否是下行轮乘点
     */
    private boolean isDownRotationPoint;
    /**
     * 是否为折返站
     */
    private boolean isTurnBackStation;


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStationNum() {
        return stationNum;
    }

    public void setStationNum(int stationNum) {
        this.stationNum = stationNum;
    }

    public boolean getConnectingDepot() {
        return isConnectingDepot;
    }

    public void setConnectingDepot(boolean connectingDepot) {
        isConnectingDepot = connectingDepot;
    }

    public String getConnectingDepotName() {
        return connectingDepotName;
    }

    public void setConnectingDepotName(String connectingDepotName) {
        this.connectingDepotName = connectingDepotName;
        processConnectingDepotName();
    }
    private void processConnectingDepotName() {
        if (connectingDepotName != null && !"".equals(connectingDepotName)) {
            this.isConnectingDepot = true;
        }
    }
    public boolean isUpRotationPoint() {
        return isUpRotationPoint;
    }

    public void setUpRotationPoint(boolean upRotationPoint) {
        isUpRotationPoint = upRotationPoint;
    }

    public boolean isDownRotationPoint() {
        return isDownRotationPoint;
    }

    public void setDownRotationPoint(boolean downRotationPoint) {
        isDownRotationPoint = downRotationPoint;
    }

    public boolean isTurnBackStation() {
        return isTurnBackStation;
    }

    public void setTurnBackStation(boolean turnBackStation) {
        isTurnBackStation = turnBackStation;
    }
}
