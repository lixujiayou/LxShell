package com.lx.shell.utils.dao;

import android.content.Context;

import com.lx.shell.mvp.model.bean.GreenDaoBeanDao;
import com.lx.shell.mvp.model.bean.greendao.GreenDaoBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2017/9/11.
 * 在这个类中添加不同的查询条件
 */

public class MyGreenDaoManager extends BaseDao<GreenDaoBean> {
    public MyGreenDaoManager(Context context) {
        super(context);
    }

    /***************************数据库查询*************************/

    /**
     * 通过ID查询对象
     * @param id
     * @return
     */
    private GreenDaoBean loadById(long id){

        return daoSession.getGreenDaoBeanDao().load(id);
    }

    /**
     * 获取某个对象的主键ID
     * @param student
     * @return
     */
    private long getID(GreenDaoBean student){

        return daoSession.getGreenDaoBeanDao().getKey(student);
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<GreenDaoBean> getStudentByName(String key){
        QueryBuilder queryBuilder =  daoSession.getGreenDaoBeanDao().queryBuilder();
        queryBuilder.where(GreenDaoBeanDao.Properties.Name.eq(key));
        int size = queryBuilder.list().size();
        if (size > 0){
            return queryBuilder.list();
        }else{
            return null;
        }
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<Long> getIdByName(String key){
        List<GreenDaoBean> students = getStudentByName(key);
        List<Long> ids = new ArrayList<Long>();
        int size = students.size();
        if (size > 0){
            for (int i = 0;i < size;i++){
                ids.add(students.get(i).getId());
            }
            return ids;
        }else{
            return null;
        }
    }

    /***************************数据库删除*************************/

    /**
     * 根据ID进行数据库的删除操作
     * @param id
     */
    private void deleteById(long id){

        daoSession.getGreenDaoBeanDao().deleteByKey(id);
    }


    /**
     * 根据ID同步删除数据库操作
     * @param ids
     */
    private void deleteByIds(List<Long> ids){

        daoSession.getGreenDaoBeanDao().deleteByKeyInTx(ids);
    }

    /***********************************
     * 在次添加一些Student特有的数据库操作语句
     * ************************************/
}
