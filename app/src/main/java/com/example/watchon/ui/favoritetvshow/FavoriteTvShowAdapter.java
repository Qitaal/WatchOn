package com.example.watchon.ui.favoritetvshow;

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

import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.data.source.remote.api.ApiConfig;
import com.example.watchon.databinding.ItemFavoriteListFilmBinding;
import com.example.watchon.ui.detailfilm.DetailFilmActivity;

public class FavoriteTvShowAdapter extends PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.ViewHolder> {

    public FavoriteTvShowAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
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
        holder.bind(getItem(position));
    }

    public TvShowEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemFavoriteListFilmBinding itemFavoriteListFilmBinding;

        public ViewHolder(ItemFavoriteListFilmBinding itemFavoriteListFilmBinding) {
            super(itemFavoriteListFilmBinding.getRoot());
            this.itemFavoriteListFilmBinding = itemFavoriteListFilmBinding;
        }

        public void bind(TvShowEntity tvShow) {

            String posterPath = ApiConfig.BASE_IMAGE_URL + tvShow.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(posterPath)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(itemFavoriteListFilmBinding.ivItemFavoriteList);
            itemFavoriteListFilmBinding.tvTitleItemFavoriteList.setText(tvShow.getTitle());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_FILM, tvShow);
                intent.putExtra(DetailFilmActivity.EXTRA_TYPE, DetailFilmActivity.TV_SHOW_TYPE);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
