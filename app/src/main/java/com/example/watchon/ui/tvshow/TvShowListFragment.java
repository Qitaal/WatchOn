package com.example.watchon.ui.tvshow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.watchon.databinding.FragmentTvShowListBinding;
import com.example.watchon.viewmodel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;

public class TvShowListFragment extends Fragment {

    private FragmentTvShowListBinding fragmentTvShowListBinding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentTvShowListBinding = FragmentTvShowListBinding.inflate(inflater, container, false);
        return fragmentTvShowListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
        TvShowListViewModel tvShowListViewModel = new ViewModelProvider(this, viewModelFactory).get(TvShowListViewModel.class);
        TvShowListAdapter tvShowListAdapter = new TvShowListAdapter();

        tvShowListViewModel.getTvShows().observe(this, tvShows -> {
            if (tvShows != null){
                switch (tvShows.status){
                    case LOADING:
                        break;
                    case SUCCESS:
                        tvShowListAdapter.submitList(tvShows.data);
                        break;
                    case ERROR:
                        Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        fragmentTvShowListBinding.rvTvShowList.setAdapter(tvShowListAdapter);
        fragmentTvShowListBinding.rvTvShowList.setHasFixedSize(true);
        fragmentTvShowListBinding.rvTvShowList.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
    }
}