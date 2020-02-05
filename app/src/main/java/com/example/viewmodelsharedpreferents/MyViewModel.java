package com.example.viewmodelsharedpreferents;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.key);
    String name = getApplication().getResources().getString(R.string.shp_name);

    public MyViewModel(@NonNull Application application, SavedStateHandle handle){
        super(application);
        this.handle = handle;
        if (!handle.contains(key)){
            load();
        }
        }

    private void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(name,Context.MODE_PRIVATE);
        int x = shp.getInt(key,0);
        handle.set(key,x);
    }

   public LiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }



        public void add(int x){
        handle.set(key,getNumber().getValue()+x);
        //save();
    }

    public void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key,getNumber().getValue());
        editor.apply();
    }
}
