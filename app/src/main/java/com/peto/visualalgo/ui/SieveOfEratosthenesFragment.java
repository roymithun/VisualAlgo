package com.peto.visualalgo.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.peto.visualalgo.R;
import com.peto.visualalgo.algorithm.SieveOfEratosthenes;
import com.peto.visualalgo.model.NumberItem;

import java.lang.ref.WeakReference;

public class SieveOfEratosthenesFragment extends Fragment {

    private SieveOfEratosthenesViewModel viewModel;
    private RecyclerView recyclerView;
    private SieveAdapter sieveAdapter;

    public static SieveOfEratosthenesFragment newInstance() {
        return new SieveOfEratosthenesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sieve_of_eratosthenes_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.rv_number_palate);
        Button btnFindPrime = rootView.findViewById(R.id.btn_find_prime);
        btnFindPrime.setOnClickListener(v -> {
            v.setEnabled(false);
            new Thread(() -> SieveOfEratosthenes.applyAlgo(new WeakReference<>(requireActivity()), viewModel)).start();

        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider.NewInstanceFactory().create(SieveOfEratosthenesViewModel.class);
        // populate data
        for (int i = 0; i < 100; i++) {
            viewModel.numbers.add(new NumberItem(i, true));
        }

        configureRecyclerView(viewModel);
//        viewModel.getNumberIdx().observe(getViewLifecycleOwner(), i -> sieveAdapter.notifyItemChanged(i));
        viewModel.getNumberIdx().observe(getViewLifecycleOwner(), sieveAdapter::notifyItemChanged);
    }

    private void configureRecyclerView(SieveOfEratosthenesViewModel dataSource) {
        sieveAdapter = new SieveAdapter(dataSource.numbers,
                requireContext().getColor(android.R.color.holo_blue_bright),
                requireContext().getColor(android.R.color.holo_orange_light));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 10);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(5));
        recyclerView.setAdapter(sieveAdapter);
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.right = space / 2;
            outRect.left = space / 2;
            outRect.bottom = space;
        }
    }
}
