package com.itdan.mysupare.recruit.service;

import com.itdan.mysupare.recruit.dao.RecruitDAO;
import com.itdan.mysupare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 招聘业务逻辑层
 */
@Service
@Transactional
public class RecruitService {

    @Autowired
    private RecruitDAO recruitDAO;
    @Autowired

    private IdWorker idWorker;

    /**
     * 添加企业操作
     * @param recruit 招聘对象
     * @return
     */
    public void add(Recruit recruit){
        recruit.setId( idWorker.nextId()+"" );
        recruit.setCreatetime(new Date());
        recruitDAO.save(recruit);
    }

    /**
     * 获取招聘全部列表
     * @return
     */
    public List<Recruit> getAll(){
        List<Recruit> list= recruitDAO.findAll();
        return list;
    }

    /**
     * 根据ID获取招聘信息
     * @param recruitId 招聘ID
     * @return
     */
    public Recruit getById(String recruitId){
        return  recruitDAO.findById(recruitId).get();
    }

    /**
     * 修改招聘信息
     * @param recruit 招聘信息
     */
    public void update(Recruit recruit){
        recruitDAO.save(recruit);
    }

    /**
     * 根据招聘ID删除企业
     * @param recruitId 招聘ID
     * @return
     */
    public void delete(String recruitId){
        recruitDAO.deleteById(recruitId);
    }


    /**
     * 根据条件查询招聘信息
     * @param searchMap 招聘信息
     * @return
     */
    public List<Recruit> search(Map searchMap){
        Specification<Recruit> specification = createSpecification(searchMap);
        return recruitDAO.findAll(specification);
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 显示条数
     * @param searchMap 查询对象条件信息
     * @return
     */
    public Page<Recruit> pageSearch(int page, int size, Map searchMap){
        Specification<Recruit> specification = createSpecification(searchMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return  recruitDAO.findAll(specification, pageRequest);
    }

    /**
     * 职位推荐
     * @return
     */
    public List<Recruit> recommend(){
        //职位推荐应该根据，经常搜索和浏览的信息来推荐，这里不太会弄，告辞了。
        return recruitDAO.findAll();
    }

    /**
     * 最新职位
     * @return
     */
    public List<Recruit> newlist(){
        //对创建时间进行排序，获取集合返回
      List<Recruit> list= recruitDAO.findAll(new Sort(Sort.Direction.DESC,"createtime"));
      return list;
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<Recruit> createSpecification(Map searchMap) {

        return new Specification<Recruit>() {

            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 职位名称
                if (searchMap.get("jobname")!=null && !"".equals(searchMap.get("jobname"))) {
                    predicateList.add(cb.like(root.get("jobname").as(String.class), "%"+(String)searchMap.get("jobname")+"%"));
                }
                // 薪资范围
                if (searchMap.get("salary")!=null && !"".equals(searchMap.get("salary"))) {
                    predicateList.add(cb.like(root.get("salary").as(String.class), "%"+(String)searchMap.get("salary")+"%"));
                }
                // 经验要求
                if (searchMap.get("condition")!=null && !"".equals(searchMap.get("condition"))) {
                    predicateList.add(cb.like(root.get("condition").as(String.class), "%"+(String)searchMap.get("condition")+"%"));
                }
                // 学历要求
                if (searchMap.get("education")!=null && !"".equals(searchMap.get("education"))) {
                    predicateList.add(cb.like(root.get("education").as(String.class), "%"+(String)searchMap.get("education")+"%"));
                }
                // 任职方式
                if (searchMap.get("type")!=null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%"+(String)searchMap.get("type")+"%"));
                }
                // 办公地址
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
                // 企业ID
                if (searchMap.get("eid")!=null && !"".equals(searchMap.get("eid"))) {
                    predicateList.add(cb.like(root.get("eid").as(String.class), "%"+(String)searchMap.get("eid")+"%"));
                }
                // 状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                // 网址
                if (searchMap.get("url")!=null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%"+(String)searchMap.get("url")+"%"));
                }
                // 标签
                if (searchMap.get("label")!=null && !"".equals(searchMap.get("label"))) {
                    predicateList.add(cb.like(root.get("label").as(String.class), "%"+(String)searchMap.get("label")+"%"));
                }
                // 职位描述
                if (searchMap.get("content1")!=null && !"".equals(searchMap.get("content1"))) {
                    predicateList.add(cb.like(root.get("content1").as(String.class), "%"+(String)searchMap.get("content1")+"%"));
                }
                // 职位要求
                if (searchMap.get("content2")!=null && !"".equals(searchMap.get("content2"))) {
                    predicateList.add(cb.like(root.get("content2").as(String.class), "%"+(String)searchMap.get("content2")+"%"));
                }

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
