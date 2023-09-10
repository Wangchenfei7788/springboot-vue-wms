package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;

import com.wms.entity.Record;
import com.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2023-09-08
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @PostMapping("/listPage")//分页
    public Result listPage(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodType = (String)param.get("goodType");
        String storage = (String)param.get("storage");


        System.out.println("name==="+(String) param.get("name"));


        Page<Record> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Record> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            //lambdaQueryWrapper.like(Record::getName,name);
        }
        if(StringUtils.isNotBlank(goodType) && !"null".equals(goodType)){
            //lambdaQueryWrapper.eq(Record::getGoodType,goodType);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            //lambdaQueryWrapper.eq(Record::getStorage,storage);
        }


        //IPage result = userService.pageC(page);
        IPage result = recordService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(), result.getTotal());
    }
}
