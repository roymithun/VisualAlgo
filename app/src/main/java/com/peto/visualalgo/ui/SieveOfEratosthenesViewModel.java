package com.peto.visualalgo.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peto.visualalgo.model.NumberItem;

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenesViewModel extends ViewModel {
    //    https://developer.android.com/topic/libraries/architecture/livedata
    private MutableLiveData<Integer> numberIdx;

    public MutableLiveData<Integer> getNumberIdx() {
        if (numberIdx == null) {
            numberIdx = new MutableLiveData<>();
        }
        return numberIdx;
    }

    List<NumberItem> numbers = new ArrayList<>();

    public List<NumberItem> getNumbers() {
        return numbers;
    }
}
