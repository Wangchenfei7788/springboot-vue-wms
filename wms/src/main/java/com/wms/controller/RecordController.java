package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;

import com.wms.entity.Good;
import com.wms.entity.Record;
import com.wms.service.GoodService;
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
    @Autowired
    private GoodService goodService;
    @PostMapping("/listPage")//分页
    public Result listPage(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodType = (String)param.get("goodType");
        String storage = (String)param.get("storage");
        String roleId = (String)param.get("roleId");
        String userId = (String)param.get("userId");

        System.out.println("name==="+(String) param.get("name"));

        Page<Record> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

       QueryWrapper<Record> queryWrapper=new QueryWrapper();
        queryWrapper.apply(" record.good=good.id and good.storage=storage.id and good.goodType=goodtype.id ");

       if ("2".equals(roleId)){
           //queryWrapper.eq("goodtype.id",roleId);
           queryWrapper.apply(" record.userId= "+userId);
       }

        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            //lambdaQueryWrapper.like(Record::getName,name);

            queryWrapper.like("good.name",name);
        }
        if(StringUtils.isNotBlank(goodType) && !"null".equals(goodType)){
            //lambdaQueryWrapper.eq(Record::getGoodType,goodType);
            queryWrapper.eq("goodtype.id",goodType);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            //lambdaQueryWrapper.eq(Record::getStorage,storage);
            queryWrapper.like("storage.id",storage);
        }


        //IPage result = userService.pageC(page);
        IPage result = recordService.pageCC(page,queryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(), result.getTotal());
    }
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        Good good = goodService.getById(record.getGood());
        int oldNum = record.getCount();
        //出库
        if ("2".equals(record.getAction())){
            oldNum = -oldNum;
            record.setCount(oldNum);
        }
        int newNum = good.getCount()+oldNum;
        good.setCount(newNum);
        goodService.updateById(good);
        return recordService.save(record)?Result.suc():Result.fail();
    }
}
