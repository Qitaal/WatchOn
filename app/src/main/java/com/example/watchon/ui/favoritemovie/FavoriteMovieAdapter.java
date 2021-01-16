package com.example.watchon.ui.favoritemovie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.remote.api.ApiConfig;
import com.example.watchon.databinding.ItemFavoriteListFilmBinding;
import com.example.watchon.ui.detailfilm.DetailFilmActivity;

public class FavoriteMovieAdapter extends PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder> {

    public FavoriteMovieAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteListFilmBinding itemFavoriteListFilmBinding = ItemFavoriteListFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemFavoriteListFilmBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieEntity movieEntity = getItem(position);
        if (movieEntity != null){
            holder.bind(getItem(position));
        }
    }

    public MovieEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemFavoriteListFilmBinding itemFavoriteListFilmBinding;

        public ViewHolder(ItemFavoriteListFilmBinding itemFavoriteListFilmBinding) {
            super(itemFavoriteListFilmBinding.getRoot());
            this.itemFavoriteListFilmBinding = itemFavoriteListFilmBinding;
        }

        public void bind(MovieEntity movie) {

            String posterPath = ApiConfig.BASE_IMAGE_URL + movie.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(posterPath)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(itemFavoriteListFilmBinding.ivItemFavoriteList);
            itemFavoriteListFilmBinding.tvTitleItemFavoriteList.setText(movie.getTitle());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_FILM, movie);
                intent.putExtra(DetailFilmActivity.EXTRA_TYPE, DetailFilmActivity.MOVIE_TYPE);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
