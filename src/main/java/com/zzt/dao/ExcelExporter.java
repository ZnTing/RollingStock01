package com.zzt.dao;

import com.zzt.domain.data.RollingStockTask;
import com.zzt.domain.data.StockTask;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @auther 朱振霆~
 */
public class ExcelExporter {
    public void exportToExcel(List<RollingStockTask> rollingStockPlan,String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rolling Stock Plan");
        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("车底号");
        headerRow.createCell(1).setCellValue("出段车场");
        headerRow.createCell(2).setCellValue("入段车场");
        headerRow.createCell(3).setCellValue("出库时间");
        headerRow.createCell(4).setCellValue("入库时间");
        headerRow.createCell(5).setCellValue("车次任务");
        // 遍历数据并写入表格
        int rowNum = 1;
        for (RollingStockTask rollingStockTask : rollingStockPlan) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rollingStockTask.getTrainNum());
            row.createCell(1).setCellValue(rollingStockTask.getOutDepot());
            row.createCell(2).setCellValue(rollingStockTask.getInDepot());
            row.createCell(3).setCellValue(rollingStockTask.getStrStartTime());
            row.createCell(4).setCellValue(rollingStockTask.getStrEndTime());
            // 创建一个 StringBuilder 用于拼接 stockTask 的 plan 值
            StringBuilder plansStringBuilder = new StringBuilder();
            List<StockTask> stockTasks = rollingStockTask.getRollingStockTaskList();
            for (int i = 0; i < stockTasks.size(); i++) {
                StockTask stockTask = stockTasks.get(i);
                plansStringBuilder.append(stockTask.getPlan());
                // 如果不是最后一个元素，添加连接符号
                if (i < stockTasks.size() - 1) {
                    plansStringBuilder.append("-");
                }
            }
            row.createCell(5).setCellValue(plansStringBuilder.toString());
        }
        // 将 workbook 写入文件
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭 workbook
        workbook.close();
    }
}
