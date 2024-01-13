import com.zzt.dao.ExcelExporter;
import com.zzt.dao.GetExcelData;
import com.zzt.domain.constraint.Constraints;
import com.zzt.domain.data.LineInformationTableRowData;
import com.zzt.domain.data.MetroNumTask;
import com.zzt.domain.data.RollingStockTask;
import com.zzt.service.impl.RollingStockConnecting;
import com.zzt.service.impl.StockCplex;
import com.zzt.service.utils.Utils;
import ilog.concert.IloException;

import java.io.IOException;
import java.util.List;

/**
 * @auther 朱振霆~
 */
public class Main {
    public static void main(String[] args) throws IOException, IloException {
        String path01 = "E:\\实习文件\\重庆数据\\187号图时刻表 22-08-2（删环北）(删750-753).xls";
        String path02 = "E:\\实习文件\\重庆数据\\3号线线路信息.xlsx";
        String path03 = "D:\\桌面\\车底运用计划.xlsx";
        GetExcelData getExcelData = new GetExcelData();
        Constraints constraint = new Constraints();
        constraint.setOnAndOffDutyStations(Utils.tempOnAndOffDutyStation());//出退勤地点
        constraint.setInOutDepotTime(Utils.tempInAndOutTrainDepot());//出退勤时间
        //时刻表名称
        String trainDiagramNo = getExcelData.getTrainDiagramNo(path01);
        //时刻表每一行（每一车次）的数据list,
        List<MetroNumTask> trainTimeTableRowData = getExcelData.getTrainTimeTableRowData(path01);
        //线路信息
        List<LineInformationTableRowData> lineInfoData = getExcelData.getLineInfoData(path02);
        //对时刻表数据和线路信息数据进行处理
        RollingStockConnecting rollingStockConnecting = new RollingStockConnecting();
        List<RollingStockTask> rollingStockTasks = rollingStockConnecting.generateRollingStockPlan(trainTimeTableRowData, lineInfoData, constraint);
        StockCplex cplex = new StockCplex();
        cplex.solver(rollingStockTasks);


//        ExcelExporter excelExporter = new ExcelExporter();
//        excelExporter.exportToExcel(rollingStockTasks,path03);
    }
}