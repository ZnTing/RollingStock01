package com.zzt.cplex.tsp;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 朱振霆~
 */
public class Solve {
    // 开始地点索引
    int startIndex;

    public void solver(Instance instance) throws IloException {
        IloCplex cplex = new IloCplex();
        double[][] distances = instance.getDistances();
        int n = instance.getN();
        /* 第一第二句是求解精度相关的设置。
         * 倒数第二句表示设置求解时间为3600s，比较常用。
         * 最后一句是告诉CPLEX不要输出那些乱七八糟的东西
         */
        cplex.setParam(IloCplex.Param.Simplex.Tolerances.Optimality, 1e-9);
        cplex.setParam(IloCplex.Param.MIP.Tolerances.MIPGap, 1e-9);
        cplex.setParam(IloCplex.DoubleParam.TimeLimit, 3600);
        cplex.setOut(null);
        //决策变量
        IloNumVar[][] NumVars = new IloNumVar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    NumVars[i][j] = cplex.intVar(0, 1);
                }
            }
        }
        //目标函数
        //distance[i][j] 表示城市 i到城市 j 之间的距离。
        //intVars[i][j] 表示一个二进制变量，通常用于表示是否选择从城市 i 移动到城市 j
        IloLinearNumExpr target = cplex.linearNumExpr();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    target.addTerm(distances[i][j], NumVars[i][j]);
                }
            }
        }
        //求目标函数的最小值
        cplex.addMinimize(target);
        //约束
        //约束1：每行每列之和等于1
        for (int i = 0; i < n; i++) {
            IloLinearNumExpr expr_row = cplex.linearNumExpr();
            IloLinearNumExpr expr_col = cplex.linearNumExpr();
            for (int j = 0; j < n; j++) {
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
        IloNumVar[] u = cplex.numVarArray(n, 0, Double.MAX_VALUE);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (j != i) {
                    IloLinearNumExpr expr = cplex.linearNumExpr();
                    expr.addTerm(1.0, u[i]);
                    expr.addTerm(-1.0,u[j]);
                    expr.addTerm(n - 1, NumVars[i][j]);
                    cplex.addLe(expr, n - 2);
                }
            }
        }
        //求解
        if (cplex.solve()) {
            //最优城市顺序
            List<Integer> bestPath = new ArrayList<>();
            bestPath.add(startIndex);
            int index = startIndex;
            while (true) {
                for (int i = 0; i < NumVars[index].length; i++) {
                    /*
                     *在TSP问题中，决策变量 xij 表示从城市i到城市j的路径是否被选择。由于浮点数运算存在精度问题，
                     * 通常在实际编程中使用一个很小的阈值，比如1e−06，作为判断路径是否被选择的标准。
                     */
                    if (index != i && cplex.getValue(NumVars[index][i]) > 1e-06) {
                        index = i;
                        bestPath.add(i);
                        break;
                    }
                }
                if (index == startIndex) {
                    break;
                }
            }
            System.out.println("Solution status = " + cplex.getStatus());
            System.out.println("最短路径为：" + bestPath);
            System.out.println("最短路径长度为：" + cplex.getObjValue());

        }
    }
}
