package com.zzt.domain.data;

/**
 * @auther 朱振霆~
 */
public class Train {
    /**
     * 车底号
     */
    private String trainNum;
    /**
     * 车次号
     */
    private String metroNum;

    public Train() {
    }

    public Train(String metroNum) {
        this.metroNum = metroNum;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public String getMetroNum() {
        return metroNum;
    }

    public void setMetroNum(String metroNum) {
        this.metroNum = metroNum;
    }
}
