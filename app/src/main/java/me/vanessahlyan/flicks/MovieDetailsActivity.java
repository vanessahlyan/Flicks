package me.vanessahlyan.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.vanessahlyan.flicks.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;

    // resolve the view objects
    @BindView(R.id.tvTitle) TextView tvTitle;
    //tvTitle = (TextView) findViewById(R.id.tvTitle);
    @BindView(R.id.tvOverview) TextView tvOverview;
    //tvOverview = (TextView) findViewById(R.id.tvOverview);
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;
    //rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
    @BindView(R.id.popularity) TextView popularity;
    //popularity = (TextView) findViewById(R.id.popularity);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        popularity.setText("Popularity: " + movie.getPopularity());

        // vote average is 0.10, convert to 0.5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage/2.0f: voteAverage);
    }
}
