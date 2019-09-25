package com.liudehuang.springboot.learning.async.controller;

import com.liudehuang.springboot.learning.async.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@RestController
public class AsyncController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/async")
    public String async(){
        taskService.asyncInvokeSimplest();
        taskService.asyncInvokeWithParameter("nihaoa");
        taskService.asyncInvokeReturnFuture(100);
        return "hello";
    }
}
