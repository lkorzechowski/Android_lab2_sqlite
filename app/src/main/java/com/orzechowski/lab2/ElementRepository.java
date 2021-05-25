package com.orzechowski.lab2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ElementRepository {
    private final ElementDao mElementDao;
    private final LiveData<List<Phones>> mAllElements;

    ElementRepository(Application application){
        ElementRoomDatabase elementRoomDatabase =
                ElementRoomDatabase.getDatabase(application);
        mElementDao = elementRoomDatabase.elementDao();
        mAllElements = mElementDao.getAlphabetizedElements();
    }

    LiveData<List<Phones>> getAllElements(){
        return mAllElements;
    }

    void deleteAll(){
        ElementRoomDatabase.databaseWriteExecutor.execute(mElementDao::deleteAll);
    }

    void insert(Phones phone){
        ElementRoomDatabase.databaseWriteExecutor.execute(()-> mElementDao.insert(phone));
    }

    void delete(Phones phone){
        ElementRoomDatabase.databaseWriteExecutor.execute(()-> mElementDao.deleteOne(phone));
    }

    void update(Phones phone){
        ElementRoomDatabase.databaseWriteExecutor.execute(()-> mElementDao.update(phone));
    }
}
