package com.peto.visualalgo.algorithm;

import android.app.Activity;

import com.peto.visualalgo.model.NumberItem;
import com.peto.visualalgo.ui.SieveOfEratosthenesViewModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;


// The time complexity is O(N * loglog(N))
public class SieveOfEratosthenes {
    public static void applyAlgo(WeakReference<Activity> contextWeakReference, SieveOfEratosthenesViewModel viewModel) {
        List<NumberItem> numberItems = viewModel.getNumbers();
        int n = numberItems.size();
        setPrime(contextWeakReference, viewModel, 0, numberItems.get(0));
        setPrime(contextWeakReference, viewModel, 1, numberItems.get(1));

        for (int i = 2; i * i <= n; i++) {
            NumberItem current = numberItems.get(i);
            if (current.isPrime()) {
                postEventToLiveData(contextWeakReference, viewModel, i, current);
                for (int j = i * i; j < n; j += i) {
                    setPrime(contextWeakReference, viewModel, j, numberItems.get(j));
                }
            }
        }
        for (int x = (int) Math.sqrt(n); x < n; x++) {
            NumberItem current = numberItems.get(x);
            if (current.isPrime()) {
                postEventToLiveData(contextWeakReference, viewModel, x, current);
            }
        }
    }

    private static void setPrime(WeakReference<Activity> contextWeakReference, SieveOfEratosthenesViewModel viewModel, int i, NumberItem numberItem) {
        numberItem.setPrime(false);
        postEventToLiveData(contextWeakReference, viewModel, i, numberItem);
    }

    private static void postEventToLiveData(WeakReference<Activity> contextWeakReference, SieveOfEratosthenesViewModel viewModel, int i, NumberItem numberItem) {
        numberItem.setTraversed();
        contextWeakReference.get().runOnUiThread(() -> viewModel.getNumberIdx().setValue(i));

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
