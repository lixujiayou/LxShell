package com.lx.shell.mvp.model.bean.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lx.shell.mvp.model.bean.DaoBean_2;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAO_BEAN_2".
*/
public class DaoBean_2Dao extends AbstractDao<DaoBean_2, Long> {

    public static final String TABLENAME = "DAO_BEAN_2";

    /**
     * Properties of entity DaoBean_2.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property My = new Property(1, String.class, "my", false, "MY");
    }


    public DaoBean_2Dao(DaoConfig config) {
        super(config);
    }
    
    public DaoBean_2Dao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAO_BEAN_2\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MY\" TEXT);"); // 1: my
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAO_BEAN_2\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DaoBean_2 entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String my = entity.getMy();
        if (my != null) {
            stmt.bindString(2, my);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DaoBean_2 entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String my = entity.getMy();
        if (my != null) {
            stmt.bindString(2, my);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DaoBean_2 readEntity(Cursor cursor, int offset) {
        DaoBean_2 entity = new DaoBean_2( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // my
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DaoBean_2 entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMy(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DaoBean_2 entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DaoBean_2 entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DaoBean_2 entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
