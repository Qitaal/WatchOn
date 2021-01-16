package com.example.watchon.ui.movie;

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

import com.example.watchon.databinding.FragmentMovieListBinding;
import com.example.watchon.viewmodel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;

public class MovieListFragment extends Fragment {

    private FragmentMovieListBinding fragmentMovieListBinding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMovieListBinding = FragmentMovieListBinding.inflate(inflater, container, false);
        return fragmentMovieListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
        MovieListViewModel movieListViewModel = new ViewModelProvider(this, viewModelFactory).get(MovieListViewModel.class);
        MovieListAdapter movieListAdapter = new MovieListAdapter();

        movieListViewModel.getMovies().observe(this, movies -> {
            if (movies != null){
                switch (movies.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        movieListAdapter.submitList(movies.data);
                        break;
                    case ERROR:
                        Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        fragmentMovieListBinding.rvMovieList.setAdapter(movieListAdapter);
        fragmentMovieListBinding.rvMovieList.setHasFixedSize(true);
        fragmentMovieListBinding.rvMovieList.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentMovieListBinding = null;
    }
}