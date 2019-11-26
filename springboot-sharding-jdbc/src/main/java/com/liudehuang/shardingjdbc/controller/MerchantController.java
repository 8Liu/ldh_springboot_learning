package com.liudehuang.shardingjdbc.controller;

import com.liudehuang.shardingjdbc.entity.Merchant;
import com.liudehuang.shardingjdbc.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title 用户(User)表控制层
 * @author Xingbz
 * @createDate 2018-11-22
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    
    @Autowired
    private MerchantService merchantService;




    @PostMapping("/insert")
    public void insert(@RequestBody Merchant param){

        merchantService.insert(param);
    }

}