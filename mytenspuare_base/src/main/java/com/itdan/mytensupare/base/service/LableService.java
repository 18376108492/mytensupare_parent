package com.itdan.mytensupare.base.service;

import com.itdan.mytensupare.base.dao.LableDAO;
import com.itdan.mytensupare.base.pojo.Lable;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

@Service
@Transactional
public class LableService {

    @Autowired
    private LableDAO lableDAO;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Lable> findAll() {
        List<Lable> list = lableDAO.findAll();
        return list;
    }

    /**
     * 根据Id查询标签
     *
     * @param lableId 标签的ID
     * @return
     */
    public Lable getById(String lableId) {
        return lableDAO.getOne(lableId);
    }

    /**
     * 新增标签
     *
     * @param lable 新增对象
     */
    public void save(Lable lable) {
        //添加Id
        lable.setId(idWorker.nextId() + "");
        lableDAO.save(lable);
    }

    /**
     * 更新标签
     *
     * @param lable 更新对象
     */
    public void update(Lable lable) {
        //save既能做保存也能做更新，没ID时为保存，有ID时为更新
        lableDAO.save(lable);
    }

    /**
     * 删除标签
     *
     * @param lableId 标签的ID
     */
    public void delete(String lableId) {
        lableDAO.deleteById(lableId);
    }

    /**根据条件进行标签查询
     * @param lable
     * @return
     */
    public List<Lable> search(Lable lable) {

        List<Lable>lableList= lableDAO.findAll(new Specification<Lable>() {
            //创建一个集合来存放所有查询条件
            List<Predicate> list = new ArrayList<>();

            /**
             * @param root 根对象，也就是把条件封装到哪一个对象中，对应的sql语句为（where 类名=lable.getId()）
             * @param criteriaQuery 封装查询都是查询的关键字，对应为（group by ,order by 等语句）
             * @param criteriaBuilder 用来封装查询语句的对象，如果为null,表示不需要查询语句
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
              return   addQuery(lable,criteriaBuilder,root,list);
            }
        });
        return lableList;
    }

    /**
     * 标签分页查询
     * @param lable 便签对象
     * @param page 当前页码
     * @param size 每页显示条数
     * @return
     */
    public Page<Lable> pageQuery(Lable lable, int page, int size){
        Pageable pageable=PageRequest.of(page-1,size);

        Page<Lable>pageResult=lableDAO.findAll(new Specification<Lable>() {
            //创建一个集合来存放所有查询条件
            List<Predicate> list = new ArrayList<>();
            /**
             * @param root 根对象，也就是把条件封装到哪一个对象中，对应的sql语句为（where 类名=lable.getId()）
             * @param criteriaQuery 封装查询都是查询的关键字，对应为（group by ,order by 等语句）
             * @param criteriaBuilder 用来封装查询语句的对象，如果为null,表示不需要查询语句
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
              return   addQuery(lable,criteriaBuilder,root,list);
            }
        },pageable);
        return pageResult;

    }

    /**
     * 封装查询语句的条件
     * @param lable 标签对象
     * @param criteriaBuilder
     * @param root
     * @param list
     * @return
     */
    public Predicate addQuery(Lable lable,CriteriaBuilder criteriaBuilder,Root root,List<Predicate>list){
        if (StringUtils.isNotBlank(lable.getLabelname())) {
            //添加查询语句
            // root.get("str"),str不能乱写，填写对象中的属性名
            Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),
                    "%" + lable.getLabelname() + "%");//相当于sql语句（where lablename like '%lablename%'）
            list.add(predicate);
        }

        if (StringUtils.isNotBlank(lable.getState())) {
            //添加查询语句
            // root.get("str"),str不能乱写，填写对象中的属性名
            Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),
                    lable.getState());//相当于sql语句（where status = '1100'）
            list.add(predicate);
        }
        //创建一个Predicate数组
        Predicate[] p = new Predicate[list.size()];
        p = list.toArray(p);//将所有的条件赋值给Predicate数组
        //相当于 where lablename like '%lablename%' AND status = '%10000%'
        return criteriaBuilder.and(p);
    }


}
