package com.zzt.cplex.tsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 朱振霆~
 */
public class GetData {
    Instance tspInstance;
    public List<double[]> getLocationList(String path) throws IOException {
        tspInstance = new Instance();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = null;
        int row = 1;
        while ((line = bufferedReader.readLine()) != null) {

            if (line.contains("NAME")) {
                tspInstance.setName(line.split(" : ")[1]);
            } else if (line.contains("DIMENSION")) {
                tspInstance.setN(Integer.parseInt(line.split(" : ")[1]));
                tspInstance.setDistances(new double[tspInstance.getN()][tspInstance.getN()]);
                tspInstance.setLocations(new double[tspInstance.getN()][2]);
            } else if (row >= 7 && row < 7 + tspInstance.getN()) {
                String[] split = line.split(" ");
                int index = Integer.parseInt(split[0]) - 1;
                int x = Integer.parseInt(split[1]);
                int y = Integer.parseInt(split[2]);
                tspInstance.getLocations()[index][0] = x;
                tspInstance.getLocations()[index][1] = y;
            }
            row++;
        }
        bufferedReader.close();
        double[][] locations = tspInstance.getLocations();
        List<double[]> locationList = new ArrayList<>();
        for (double[] location : locations) {
            locationList.add(location);
        }
        tspInstance.setLocationList(locationList);
        return locationList;
    }

    // 初始化变量
    public void initVar() {
        double[][] distances = tspInstance.getDistances();
        // 初始化距离矩阵
        for (int i = 0; i < tspInstance.getDistances().length; i++) {
            for (int j = 0; j < tspInstance.getDistances()[i].length; j++) {
                if (i == j) {
                    // 对角线为无穷大
                    tspInstance.getDistances()[i][j] = Double.MAX_VALUE;
                } else {
                    // 计算i到j的距离
                    distances[i][j]= getDistance(tspInstance.getLocationList().get(i), tspInstance.getLocationList().get(j));
                    distances[j][i] = distances[i][j];
                }
            }
        }
    }

    // 计算两点之间的距离（使用伪欧氏距离，可以减少计算量）
    private double getDistance(double[] place1, double[] place2) {
        // 伪欧氏距离在根号内除以了一个10
//        return Math.sqrt((Math.pow(place1[0] - place2[0], 2) + Math.pow(place1[1] - place2[1], 2)) / 10.0);
        return Math.sqrt((Math.pow(place1[0] - place2[0], 2) + Math.pow(place1[1] - place2[1], 2)));
    }
}
