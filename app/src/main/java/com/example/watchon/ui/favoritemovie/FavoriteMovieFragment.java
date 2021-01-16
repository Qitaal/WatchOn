package com.example.watchon.ui.favoritemovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watchon.R;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.databinding.FragmentFavoriteMovieBinding;
import com.example.watchon.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class FavoriteMovieFragment extends Fragment {

    private FragmentFavoriteMovieBinding fragmentFavoriteMovieBinding;
    private FavoriteMovieViewModel favoriteMovieViewModel;
    private FavoriteMovieAdapter favoriteMovieAdapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false);
        return fragmentFavoriteMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemTouchHelper.attachToRecyclerView(fragmentFavoriteMovieBinding.rvFavoriteMovieList);

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
        favoriteMovieViewModel = new ViewModelProvider(this, viewModelFactory).get(FavoriteMovieViewModel.class);
        favoriteMovieAdapter = new FavoriteMovieAdapter();

        favoriteMovieViewModel.getFavoriteMovie().observe(this, favoriteMovieAdapter::submitList);

        fragmentFavoriteMovieBinding.rvFavoriteMovieList.setAdapter(favoriteMovieAdapter);
        fragmentFavoriteMovieBinding.rvFavoriteMovieList.setHasFixedSize(true);
        fragmentFavoriteMovieBinding.rvFavoriteMovieList.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
    }

    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null){
                int swipePosition = viewHolder.getAdapterPosition();
                MovieEntity movieEntity = favoriteMovieAdapter.getSwipedData(swipePosition);
                favoriteMovieViewModel.setFavorite(movieEntity);

                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> favoriteMovieViewModel.setFavorite(movieEntity));
                snackbar.show();
            }
        }
    });
}