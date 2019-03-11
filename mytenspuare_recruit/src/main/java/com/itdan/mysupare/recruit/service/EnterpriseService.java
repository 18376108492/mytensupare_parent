package com.itdan.mysupare.recruit.service;

import com.itdan.mysupare.recruit.dao.EnterpriseDAO;
import com.itdan.mysupare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 企业业务逻辑层
 */
@Service
@Transactional
public class EnterpriseService {

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加企业操作
     * @param enterprise 企业对象
     * @return
     */
    public void add(Enterprise enterprise){
        enterprise.setId( idWorker.nextId()+"" );
       enterpriseDAO.save(enterprise);
    }

    /**
     * 获取企业全部列表
     * @return
     */
    public List<Enterprise> getAll(){
        List<Enterprise> list= enterpriseDAO.findAll();
        return list;
    }

    /**
     * 根据ID获取企业对象
     * @param enterpriseId 企业ID
     * @return
     */
    public Enterprise searchById(String enterpriseId){
        return  enterpriseDAO.findById(enterpriseId).get();
    }

    /**
     * 修改企业信息
     * @param enterprise 企业对象
     */
    public void update(Enterprise enterprise){
        enterpriseDAO.save(enterprise);
    }

    /**
     * 根据企业ID删除企业
     * @param enterpriseId 企业ID
     * @return
     */
    public void delete(String enterpriseId){
        enterpriseDAO.deleteById(enterpriseId);
    }

    /**
     * 根据条件查询企业信息
     * @param searchMap 企业对象信息
     * @return
     */
    public List<Enterprise> search(Map searchMap){
        Specification<Enterprise> specification = createSpecification(searchMap);
        return enterpriseDAO.findAll(specification);
    }

    /**
     * 查询出热门企业
     * @param ishot 是否为热门状态
     * @return
     */
    public  List<Enterprise> findByIshot(String ishot){
        List<Enterprise>hotList= enterpriseDAO.findByIshot(ishot);
        return hotList;
    }


    /**
     * 分页查询
     * @param page 页码
     * @param size 显示条数
     * @param searchMap 查询对象条件信息
     * @return
     */
    public Page<Enterprise> pageSearch(int page, int size, Map searchMap){
        Specification<Enterprise> specification = createSpecification(searchMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return  enterpriseDAO.findAll(specification, pageRequest);
    }



    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<Enterprise> createSpecification(Map searchMap) {

        return new Specification<Enterprise>() {

            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 企业名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 企业简介
                if (searchMap.get("summary")!=null && !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(root.get("summary").as(String.class), "%"+(String)searchMap.get("summary")+"%"));
                }
                // 企业地址
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
                // 标签列表
                if (searchMap.get("labels")!=null && !"".equals(searchMap.get("labels"))) {
                    predicateList.add(cb.like(root.get("labels").as(String.class), "%"+(String)searchMap.get("labels")+"%"));
                }
                // 坐标
                if (searchMap.get("coordinate")!=null && !"".equals(searchMap.get("coordinate"))) {
                    predicateList.add(cb.like(root.get("coordinate").as(String.class), "%"+(String)searchMap.get("coordinate")+"%"));
                }
                // 是否热门
                if (searchMap.get("ishot")!=null && !"".equals(searchMap.get("ishot"))) {
                    predicateList.add(cb.like(root.get("ishot").as(String.class), "%"+(String)searchMap.get("ishot")+"%"));
                }
                // LOGO
                if (searchMap.get("logo")!=null && !"".equals(searchMap.get("logo"))) {
                    predicateList.add(cb.like(root.get("logo").as(String.class), "%"+(String)searchMap.get("logo")+"%"));
                }
                // URL
                if (searchMap.get("url")!=null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%"+(String)searchMap.get("url")+"%"));
                }

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
