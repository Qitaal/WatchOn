package com.example.watchon.ui.detailfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.watchon.R;
import com.example.watchon.data.source.remote.api.ApiConfig;
import com.example.watchon.databinding.ActivityDetailFilmBinding;
import com.example.watchon.viewmodel.ViewModelFactory;

public class DetailFilmActivity extends AppCompatActivity {

    private ActivityDetailFilmBinding activityDetailFilmBinding;

    public static final String EXTRA_TYPE = "TYPE";
    public static final String TV_SHOW_TYPE = "TV_SHOW";
    public static final String MOVIE_TYPE = "MOVIE";
    public static final String EXTRA_FILM = "EXTRA_FILM";

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(getLayoutInflater());
        setContentView(activityDetailFilmBinding.getRoot());

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        DetailFilmViewModel detailFilmViewModel = new ViewModelProvider(this, viewModelFactory).get(DetailFilmViewModel.class);

        this.type = getIntent().getStringExtra(EXTRA_TYPE);
        if (getIntent().getParcelableExtra(EXTRA_FILM) != null){
            if (type.equals(MOVIE_TYPE)){
                detailFilmViewModel.setSelectedMovie(getIntent().getParcelableExtra(EXTRA_FILM));
            } else {
                detailFilmViewModel.setSelectedTvShow(getIntent().getParcelableExtra(EXTRA_FILM));
            }
        }
        detailFilmViewModel.getDetailFilm().observe(this, filmDetail -> {

            String posterPath = ApiConfig.BASE_IMAGE_URL + filmDetail.getPosterPath();
            String runTime = filmDetail.getRuntime() + "m";

            Glide.with(this)
                    .load(posterPath)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(activityDetailFilmBinding.ivPoster);
            activityDetailFilmBinding.tvTitle.setText(filmDetail.getTitle());
            activityDetailFilmBinding.tvGenre.setText(filmDetail.getGenre());
            activityDetailFilmBinding.tvLength.setText(runTime);
            activityDetailFilmBinding.tvDescription.setText(filmDetail.getOverview());
            activityDetailFilmBinding.tvReleaseDateDetail.setText(filmDetail.getReleaseDate());
            activityDetailFilmBinding.fabFavorite.setImageDrawable(
                    filmDetail.isFavorite() ? getDrawable(R.drawable.ic_baseline_favorite_24) : getDrawable(R.drawable.ic_baseline_favorite_border_24)
            );

            activityDetailFilmBinding.fabFavorite.setOnClickListener(v -> {
                if (type.equals(MOVIE_TYPE)) {
                    detailFilmViewModel.setFavoriteMovie(!filmDetail.isFavorite());
                } else {
                    detailFilmViewModel.setFavoriteTvShow(!filmDetail.isFavorite());
                }
            });
        });
    }
}