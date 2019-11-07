package com.liudehuang.easyexcel.learning.controller;

import com.alibaba.excel.EasyExcel;
import com.liudehuang.easyexcel.learning.handler.ExcelCellWriteHandler;
import com.liudehuang.easyexcel.learning.model.User;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-10-31
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-10-31
 * @UpdateRemark:
 * @Version: 1.0
 */
@Controller
public class ExcelController {

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), User.class).registerWriteHandler(new ExcelCellWriteHandler())
                .sheet("模板").doWrite(data());
    }

    private List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUid(i+"1");
            user.setUserName(null);
            user.setPassword("123"+i);
            user.setSchool("实验小学"+i);
            list.add(user);
        }
        return list;
    }

    @GetMapping("/excludeOrIncludeWrite")
    public void excludeOrIncludeWrite(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("根据参数导出指定列", "UTF-8");
        Set<String> incloudeColumnFiledNames = new HashSet<>();
        incloudeColumnFiledNames.add("userName");
        incloudeColumnFiledNames.add("password");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), User.class).sheet("模板")
                .doWrite(data());
    }

    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(head()).sheet("模板").doWrite(dataList());
    }


    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("username");
        List<String> head1 = new ArrayList<String>();
        head1.add("password");
        List<String> head2 = new ArrayList<String>();
        head2.add("school");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> objList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User();
            user.setUid("100"+i);
            user.setUserName("张三"+i);
            user.setPassword("wangwu"+i);
            user.setSchool("学校"+i);
            userList.add(user);
        }
        for(User user:userList){
            Object userName = getFieldValueByObject(user, "userName");
            Object password = getFieldValueByObject(user,"password");
            Object school = getFieldValueByObject(user,"school");
            List<Object> list = new ArrayList<>();
            list.add(userName);
            list.add(password);
            list.add(school);
            objList.add(list);
        }
        return objList;
    }

    private static Object getFieldValueByObject(Object object, String targetFieldName) {
        // 获取方法参数对象的商户编号字段属性值
        PropertyAccessor propertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(object);
        Object fieldVal = propertyAccessor.getPropertyValue(targetFieldName);
        return fieldVal;
    }
}
