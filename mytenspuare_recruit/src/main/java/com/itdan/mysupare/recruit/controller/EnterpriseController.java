package com.itdan.mysupare.recruit.controller;

import com.itdan.mysupare.recruit.pojo.Enterprise;
import com.itdan.mysupare.recruit.service.EnterpriseService;
import entiy.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 获取企业全部列表
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public Result getAll(){
       List<Enterprise> list= enterpriseService.getAll();
       return Result.ok(list);
    }

    /**
     * 根据ID获取企业对象
     * @param enterpriseId 企业ID
     * @return
     */
    @RequestMapping(value = "/{enterpriseId}",method = RequestMethod.GET)
    public Result searchById(@PathVariable String enterpriseId){
       Enterprise enterprise=  enterpriseService.searchById(enterpriseId);
       return Result.ok(enterprise);
    }

    /**
     * 修改企业信息
     * @param enterprise 企业对象
     * @param enterpriseId 企业ID
     * @return
     */
    @RequestMapping(value = "/{enterpriseId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Enterprise enterprise,@PathVariable String enterpriseId){
        enterprise.setId(enterpriseId);
         enterpriseService.update(enterprise);
       return Result.ok("修改成功");
    }

    /**
     * 根据企业ID删除企业
     * @param enterpriseId 企业ID
     * @return
     */
    @RequestMapping(value = "/{enterpriseId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String enterpriseId){
        enterpriseService.delete(enterpriseId);
        return Result.ok("删除成功");
    }


    /**
     * 根据条件查询企业信息
     * @param searchMap 查询对象条件信息
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(@RequestBody Map searchMap){
       List<Enterprise> list=  enterpriseService.search(searchMap);
       return Result.ok(list);
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

    /**
     * 分页查询
     * @param page 页码
     * @param size 显示条数
     * @param searchMap 查询对象条件信息
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageSearch(@PathVariable int page,
                             @PathVariable int size,
                             @RequestBody  Map searchMap){
        Page<Enterprise> list=enterpriseService.pageSearch(page,size,searchMap);
        return Result.ok(list);
    }

}
