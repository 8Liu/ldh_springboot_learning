package com.liudehuang.easyexcel.learning.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-04
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-04
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ExcelCellWriteHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer integer, Integer integer1, Boolean aBoolean) {
        if("password".equals(head.getFieldName())){
        }
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer integer, Boolean aBoolean) {
        System.out.println(cell);
        if("password".equals(head.getFieldName())){
            cell.setCellValue("124");

        }
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        if("password".equals(head.getFieldName())&&!aBoolean){
            System.out.println(cell.getStringCellValue());
            cell.setCellValue("147852");
        }
    }
}
