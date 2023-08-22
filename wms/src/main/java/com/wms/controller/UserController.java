package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.User;
import com.wms.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2023-08-10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
   public List<User> list(){
       return userService.list();
   }

   //新增
   @PostMapping("/save")
   public Result save(@RequestBody User user){
       return userService.save(user)?Result.suc():Result.fail();
   }
   //账号唯一性检查
   @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
      List list = userService.lambdaQuery().eq(User::getNo,no).list();
      return list.size()>0?Result.suc(list):Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateById(user)?Result.suc():Result.fail();
    }
   //修改
   @PostMapping("/mod")
   public boolean mod(@RequestBody User user){
       return userService.updateById(user);
   }
   //新增或修改
   @PostMapping("/saveOrMod")
   public boolean saveOrMod(@RequestBody User user){
       return userService.saveOrUpdate(user);
   }
   //删除
   @GetMapping("/delete")
   public boolean saveOrMod(Integer id){
       return userService.removeById(id);
   }
   //查询（模糊，匹配）
   @PostMapping("/listP")
   public Result listP(@RequestBody User user){
       LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
       if(StringUtils.isNotBlank(user.getName())){
           lambdaQueryWrapper.like(User::getName,user.getName());
       }

       return Result.suc(userService.list(lambdaQueryWrapper));
   }
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name==="+(String) param.get("name"));
        //System.out.println("no==="+(String) param.get("no"));
        //LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        //lambdaQueryWrapper.like(User::getName,user.getName());

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

       IPage result = userService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    //分页
    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name==="+(String) param.get("name"));

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC1")//分页
    public Result listPageC1(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String sex = (String)param.get("sex");
        System.out.println("name==="+(String) param.get("name"));

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.like(User::getSex,sex);
        }

        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(), result.getTotal());
    }






}
