package com.zzt.cplex;

import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;

/**
 * @auther 朱振霆~
 */
public class MyTest {

    /**
     * max z = x1 + 5x2 + 1x3
     * -x1 + x2 + x3 <=56
     * x1 - 3x2 + x3 <= 39
     * 0 <= x1 <= 40 * 0 <= x2, x3
     *
     * @throws IloException
     */
    public static void main(String[] args) throws IloException {
        IloNumVarType varType = IloNumVarType.Float;
        int varNum = 3;

        IloCplex cplex = new IloCplex();

        /// 决策变量申明
        // 有几个决策变量，x1,x2,x3
        IloNumVar[] vars = new IloNumVar[varNum];
        // 目标函数里面的决策变量的系数
        double[] xishu = new double[]{1, 5, 1};
        // 决策变量下界
        double[] mins = new double[]{0, 0, 0};
        // 决策变量上界
        double[] maxs = new double[]{40, Double.MAX_VALUE, Double.MAX_VALUE};
        //通过循环，为每个决策变量创建并初始化。
        for (int i = 0; i < vars.length; i++) {
            vars[i] = cplex.numVar(mins[i], maxs[i], varType);
        }

        /// 目标函数,定义最大化的目标函数，其中使用 scalProd 计算决策变量和系数的点积。
        cplex.addMaximize(cplex.scalProd(vars, xishu));

        /// 约束条件
        cplex.addLe(cplex.sum(cplex.prod(-1.0, vars[0]), cplex.prod(1.0, vars[1]), cplex.prod(1.0, vars[2])), 56);
        cplex.addLe(cplex.sum(cplex.prod(1.0, vars[0]), cplex.prod(-3.0, vars[1]), cplex.prod(1.0, vars[2])), 39);

        //通过调用 solve() 方法求解线性规划问题。
        if (cplex.solve()) {
            //cplex.getStatus()：功能：获取求解器的解决状态。
            //返回值：表示求解的状态，可能的取值包括：
            //        Optimal：找到了最优解。
            //        Infeasible：问题无可行解。
            //        Unbounded：问题具有无界解。
            cplex.output().println("Solution status = " + cplex.getStatus());
            //最优解对应的目标函数值，将x1，x2,x3带入目标函数
            cplex.output().println("Solution value = " + cplex.getObjValue());

            double[] val = cplex.getValues(vars);
            //获取问题中决策变量的数量。
            int ncols = cplex.getNcols();
            for (int j = 0; j < ncols; ++j) {
                cplex.output().println("Column: " + j + " Value = " + val[j]);
            }
        }
        cplex.end();
    }
}
