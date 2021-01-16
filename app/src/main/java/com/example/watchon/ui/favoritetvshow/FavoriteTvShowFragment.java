package com.example.watchon.ui.favoritetvshow;

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
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.databinding.FragmentFavoriteTvShowBinding;
import com.example.watchon.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class FavoriteTvShowFragment extends Fragment {

    private FragmentFavoriteTvShowBinding fragmentFavoriteTvShowBinding;
    private FavoriteTvShowViewModel favoriteTvShowViewModel;
    private FavoriteTvShowAdapter favoriteTvShowAdapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentFavoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false);
        return fragmentFavoriteTvShowBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemTouchHelper.attachToRecyclerView(fragmentFavoriteTvShowBinding.rvFavoriteTvShowList);

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity());
        favoriteTvShowViewModel = new ViewModelProvider(this, viewModelFactory).get(FavoriteTvShowViewModel.class);
        favoriteTvShowAdapter = new FavoriteTvShowAdapter();

        favoriteTvShowViewModel.getFavoriteTvShow().observe(this, favoriteTvShowAdapter::submitList);

        fragmentFavoriteTvShowBinding.rvFavoriteTvShowList.setAdapter(favoriteTvShowAdapter);
        fragmentFavoriteTvShowBinding.rvFavoriteTvShowList.setHasFixedSize(true);
        fragmentFavoriteTvShowBinding.rvFavoriteTvShowList.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
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
                TvShowEntity tvShowEntity = favoriteTvShowAdapter.getSwipedData(swipePosition);
                favoriteTvShowViewModel.setFavorite(tvShowEntity);

                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> favoriteTvShowViewModel.setFavorite(tvShowEntity));
                snackbar.show();
            }
        }
    });
}