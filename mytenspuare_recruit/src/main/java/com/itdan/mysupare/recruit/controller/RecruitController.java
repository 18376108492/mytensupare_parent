package com.itdan.mysupare.recruit.controller;

import com.itdan.mysupare.recruit.pojo.Recruit;
import com.itdan.mysupare.recruit.service.RecruitService;
import entiy.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 招聘业务控制层
 */

@CrossOrigin
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /**
     * 增加招聘
     * @param recruit 招聘对象
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Recruit recruit){
       recruitService.add(recruit);
       return Result.ok("添加成功");
    }

    /**
     * 获取招聘全部列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result getAll(){
       List<Recruit> list= recruitService.getAll();
       return Result.ok(list);
    }

    /**
     *根据id获取招聘信息
     * @param recruitId 招聘信息的ID
     * @return
     */
    @RequestMapping(value = "/{recruitId}",method = RequestMethod.GET)
    public Result getById(@PathVariable String recruitId){
        Recruit recruit=recruitService.getById(recruitId);
        return Result.ok(recruit);
    }

    /**
     *修改招聘信息
     * @param recruitId 招聘信息的ID
     * @return
     */
    @RequestMapping(value = "/{recruitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String recruitId,@RequestBody Recruit recruit){
        recruit.setId(recruitId);
        recruitService.update(recruit);
        return Result.ok("修改成功");
    }

    /**
     *删除招聘信息
     * @param recruitId 招聘信息的ID
     * @return
     */
    @RequestMapping(value = "/{recruitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String recruitId){
        recruitService.delete(recruitId);
        return Result.ok("删除成功");
    }

    /**
     * 根据条件查询招聘列表
     * @param searchMap 招聘信息
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(@RequestBody Map searchMap){
           List<Recruit>list=recruitService.search(searchMap);
           return Result.ok(list);
    }

    /**
     * 招聘分页
     * @param searchMap 招聘信息
     * @param page 页码
     * @param size 条数
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageSearch(@RequestBody Map searchMap,
                             @PathVariable int page,
                             @PathVariable int size){
        Page<Recruit> list=recruitService.pageSearch(page,size,searchMap);
        return Result.ok(list);
    }

    /**
     * 职位推荐
     * @return
     */
    @RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
    public Result recommend(){
         List<Recruit> list= recruitService.recommend();
         return Result.ok(list);
    }

    /**
     * 最新职位
     * @return
     */
    @RequestMapping(value = "/search/newlist",method = RequestMethod.GET)
    public Result newlist(){
        List<Recruit> list= recruitService.newlist();
        return  Result.ok(list);
    }

}
