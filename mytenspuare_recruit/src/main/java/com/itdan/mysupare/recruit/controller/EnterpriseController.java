package com.itdan.mysupare.recruit.controller;

import com.itdan.mysupare.recruit.pojo.Enterprise;
import com.itdan.mysupare.recruit.service.EnterpriseService;
import entiy.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 招聘模块企业控制层
 */

@CrossOrigin
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 添加企业操作
     * @param enterprise 企业对象
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Enterprise enterprise){
        enterpriseService.add(enterprise);
        return Result.ok("添加成功");
    }


    /**
     * 查询热门企业
     * @return
     */
    @RequestMapping(value = "/search/hotlist",method = RequestMethod.GET)
    public Result searchIsHost(){
        List<Enterprise> list= enterpriseService.findByIshot("1");//状态为1时为热门企业,非热门为0
        return Result.ok(list);
    }

}
