package com.itdan.mytensupare.base.controller;

import com.itdan.mytensupare.base.pojo.Lable;
import com.itdan.mytensupare.base.service.LableService;
import entiy.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签控制层
 */
@CrossOrigin//跨域
@RestController//该标签包含@Controller和@ResponseBody两种标签的属性
@RequestMapping("/lable")
public class LableController {

    @Autowired
    private LableService lableService;

    /**
     * 查询标签的全部列表
     * @return
     */
    @RequestMapping(method =RequestMethod.GET)
    public Result getAllLable(){
        return Result.ok(lableService.findAll());
    }

    /**
     * 保存标签
     * @param lable 便签对象
     * @return
     */
    @RequestMapping(method =RequestMethod.POST)
    public Result saveLable(@RequestBody Lable lable){
        lableService.save(lable);
        return Result.ok("添加成功");
    }

    /**
     * 查询推荐标签列表
     * @return
     */
    @RequestMapping(value = "/toplist",method = RequestMethod.GET)
    public Result topList(){
        return Result.ok(lableService.findAll());
    }

    /**
     * 查询有效的标签列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result effectiveList(){
        return Result.ok(lableService.findAll());
    }

    /**
     * 根据ID获取标签类表
     * @return
     */
    @RequestMapping(value = "/{lableId}",method = RequestMethod.GET)
    public Result getLableById(@PathVariable("lableId") String lableId){
        return Result.ok(lableService.getById(lableId));
    }


    /**
     * 更新标签信息
     * @param lableId
     * @return
     */
    @RequestMapping(value = "/{lableId}",method = RequestMethod.PUT)
    public Result updatelable(@PathVariable("lableId") String lableId,
                              @RequestBody Lable lable){
        lable.setId(lableId);
        lableService.update(lable);
        return Result.ok("更新成功");
    }

    /**
     * 删除标签
     * @param lableId
     * @return
     */
    @RequestMapping(value = "/{lableId}",method = RequestMethod.DELETE)
    public Result deleteLable(@PathVariable("lableId") String lableId){
        lableService.delete(lableId);
        return Result.ok("删除成功");
    }

    /**
     * 根据条件进行标签查询
     * @param lable 便签对象
     * @return
     */
    @RequestMapping(value ="/search",method = RequestMethod.POST)
    public Result search(@RequestBody Lable lable){
        List<Lable> list= lableService.search(lable);
     return  Result.ok(list) ;
    }

    /**
     * 标签分页查询
     * @param lable 便签对象
     * @param page 当前页码
     * @param size 每页显示条数
     * @return
     */
    @RequestMapping(value ="/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Lable lable,
                            @PathVariable int page,
                            @PathVariable int size){
        Page<Lable> pageResult = lableService.pageQuery(lable,page,size);
        return  Result.ok(pageResult) ;
    }



}
