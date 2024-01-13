package com.zzt.service.impl;

import com.zzt.domain.data.RollingStockTask;
import com.zzt.domain.data.StockTask;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 朱振霆~
 * 建模求解
 */
public class StockCplex {

    public void solver(List<RollingStockTask> rollingStockTasks) throws IloException {
        //遍历每一车底
        for (RollingStockTask rollingStockTask : rollingStockTasks) {
            System.out.println("求解 " + rollingStockTask.getTrainNum() + " 车底");
            //开始索引
            int startIndex = 0;

            IloCplex cplex = new IloCplex();
            cplex.setParam(IloCplex.Param.Simplex.Tolerances.Optimality, 1e-9);
            cplex.setParam(IloCplex.Param.MIP.Tolerances.MIPGap, 1e-9);
            cplex.setParam(IloCplex.DoubleParam.TimeLimit, 3600);
            cplex.setOut(null);
            //每一车底的车次节点数量
            int trainNumber = rollingStockTask.getTrainNumber();
            //车次与车次之间接续所用的时间
            double[][] C = new double[trainNumber][trainNumber];

            //车次链表
            List<StockTask> rollingStockTaskList = rollingStockTask.getRollingStockTaskList();
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C[i].length; j++) {
                    if (i == j) {
                        C[i][j] = Double.MAX_VALUE;
                    } else {
                        C[i][j] = rollingStockTaskList.get(j).getDurTime();
                        C[j][i] = C[i][j];
                    }
                }
            }

            rollingStockTask.getRollingStockTaskList();
            //决策变量
            IloNumVar[][] NumVars = new IloNumVar[trainNumber][trainNumber];
            for (int i = 0; i < trainNumber; i++) {
                for (int j = 0; j < trainNumber; j++) {
                    if (i != j) {
                        NumVars[i][j] = cplex.intVar(0, 1);//表示每对车次节点之间是否存在连接
                    }
                }
            }
            //目标函数
            IloLinearNumExpr target = cplex.linearNumExpr();
            for (int i = 0; i < trainNumber; i++) {
                for (int j = 0; j < trainNumber; j++) {
                    if (i != j) {
                        //时间乘是否连接车次
                        target.addTerm(C[i][j], NumVars[i][j]);
                    }
                }
            }
            //求目标函数的最小值
            cplex.addMinimize(target);
            //约束
            //约束1：每行每列之和等于1，接续节点唯一性
            for (int i = 0; i < trainNumber; i++) {
                IloLinearNumExpr expr_row = cplex.linearNumExpr();
                IloLinearNumExpr expr_col = cplex.linearNumExpr();
                for (int j = 0; j < trainNumber; j++) {
                    if (i != j) {
                        expr_row.addTerm(1, NumVars[i][j]);
                        expr_col.addTerm(1, NumVars[j][i]);
                    }
                }
                //这行代码添加了一个等式约束
                cplex.addEq(expr_row, 1);
                cplex.addEq(expr_col, 1);
            }
            //约束2：消除子回路
            //.addLe() 表示添加一个小于等于
            //用于创建决策变量数组，0下界，Double.MAX_VALUE表示决策变量的上界，这里设为双精度浮点数的最大值。
            IloNumVar[] u = cplex.numVarArray(trainNumber, 0, Double.MAX_VALUE);
            for (int i = 1; i < trainNumber; i++) {
                for (int j = 1; j < trainNumber; j++) {
                    if (j != i) {
                        IloLinearNumExpr expr = cplex.linearNumExpr();
                        expr.addTerm(1.0, u[i]);
                        expr.addTerm(-1.0, u[j]);
                        expr.addTerm(trainNumber - 1, NumVars[i][j]);
                        cplex.addLe(expr, trainNumber - 2);
                    }
                }
            }
            //求解
            List<StockTask> bestPath = null;
            if (cplex.solve()) {
                //最优车次顺序
                bestPath = new ArrayList<>();
                bestPath.add(rollingStockTask.getRollingStockTaskList().get(startIndex));
                int index = startIndex;
                while (true) {
                    for (int i = 0; i < NumVars[index].length; i++) {
                        /*
                         *在TSP问题中，决策变量 xij 表示从车次i到车j的路径是否被选择。由于浮点数运算存在精度问题，
                         * 通常在实际编程中使用一个很小的阈值，比如1e−06，作为判断路径是否被选择的标准。
                         */
                        if (index != i && cplex.getValue(NumVars[index][i]) > 1e-06) {
                            index = i;
                            bestPath.add(rollingStockTask.getRollingStockTaskList().get(index));
                            break;
                        }
                    }
                    if (index == startIndex) {
                        break;
                    }
                }
            }
            System.out.println("最短路径为：" + bestPath);
            System.out.println("车底" + rollingStockTask.getTrainNum() + " 最短时间为：" + cplex.getObjValue());

        }


    }
}