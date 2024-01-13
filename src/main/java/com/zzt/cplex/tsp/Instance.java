package com.zzt.cplex.tsp;

import java.util.Arrays;
import java.util.List;

/**
 * @auther 朱振霆~
 */
public class Instance {
    // 实例名
    String name;
    // 城市数量
    int n;
    // 每个城市的坐标
    double[][] locations;
    List<double[]> locationList;
    // 距离矩阵
    double[][] distances;
    public Instance() {
    }

    public Instance(String name, int n, double[][] locations) {
        this.name = name;
        this.n = n;
        this.locations = locations;
    }

    public List<double[]> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<double[]> locationList) {
        this.locationList = locationList;
    }

    public double[][] getDistances() {
        return distances;
    }

    public void setDistances(double[][] distances) {
        this.distances = distances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[][] getLocations() {
        return locations;
    }

    public void setLocations(double[][] locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "name='" + name + '\'' +
                ", n=" + n +
                ", locations=" + Arrays.toString(locations) +
                '}';
    }
}
