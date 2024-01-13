package com.zzt.cplex.tsp;

import ilog.concert.IloException;

import java.io.IOException;
import java.util.List;

/**
 * @auther 朱振霆~
 */
public class CplexTest {
    public static void main(String[] args) throws IOException, IloException {
        GetData getData = new GetData();
        List<double[]> locationList = getData.getLocationList("D:\\桌面\\att48.tsp");
        getData.initVar();
        long startTime = System.currentTimeMillis();
        System.out.println("整数规划求解tsp问题："+locationList.size()+"个城市...");
        Solve solve = new Solve();
        solve.solver(getData.tspInstance);
        System.out.println("求解用时："+(System.currentTimeMillis()-startTime)/1000d+" s");
    }
}