package com.lx.shell.mvp.model.bean.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lx.shell.mvp.model.bean.DaoBean_2;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig daoBean_2DaoConfig;
    private final DaoConfig greenDaoBeanDaoConfig;

    private final DaoBean_2Dao daoBean_2Dao;
    private final GreenDaoBeanDao greenDaoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        daoBean_2DaoConfig = daoConfigMap.get(DaoBean_2Dao.class).clone();
        daoBean_2DaoConfig.initIdentityScope(type);

        greenDaoBeanDaoConfig = daoConfigMap.get(GreenDaoBeanDao.class).clone();
        greenDaoBeanDaoConfig.initIdentityScope(type);

        daoBean_2Dao = new DaoBean_2Dao(daoBean_2DaoConfig, this);
        greenDaoBeanDao = new GreenDaoBeanDao(greenDaoBeanDaoConfig, this);

        registerDao(DaoBean_2.class, daoBean_2Dao);
        registerDao(GreenDaoBean.class, greenDaoBeanDao);
    }
    
    public void clear() {
        daoBean_2DaoConfig.clearIdentityScope();
        greenDaoBeanDaoConfig.clearIdentityScope();
    }

    public DaoBean_2Dao getDaoBean_2Dao() {
        return daoBean_2Dao;
    }

    public GreenDaoBeanDao getGreenDaoBeanDao() {
        return greenDaoBeanDao;
    }

}