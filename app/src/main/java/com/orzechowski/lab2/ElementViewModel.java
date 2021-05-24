package com.orzechowski.lab2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ElementViewModel extends AndroidViewModel {
    private final ElementRepository mRepository;
    private final LiveData<List<Phones>> mAllElements;

    public ElementViewModel(@NonNull Application application){
        super(application);
        mRepository = new ElementRepository(application);
        mAllElements = mRepository.getAllElements();
    }

    public LiveData<List<Phones>> getAllElements(){
        return mAllElements;
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void insert(Phones phone){
        mRepository.insert(phone);
    }

    public void delete(Phones phone){
        mRepository.delete(phone);
    }
}
