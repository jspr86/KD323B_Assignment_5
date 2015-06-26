package se.k3.antonochisak.kd323bassignment5.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import android.R.*;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.kd323bassignment5.R;
import se.k3.antonochisak.kd323bassignment5.helpers.StaticHelpers;
import se.k3.antonochisak.kd323bassignment5.models.movie.Movie;

/**
 * Created by Jesper on 2015-06-26.
 */
public class TrendingMoviesAdapter extends BaseAdapter {

    ArrayList<Movie> trendMovies;
    private int itemWidth, itemHeight, mMargin;
    LayoutInflater layoutInflater;

    public TrendingMoviesAdapter(ArrayList<Movie> moviesArr, LayoutInflater layoutInflater){
        this.trendMovies = moviesArr;
        this.layoutInflater = layoutInflater;
    }

    class ViewHolder {

        @InjectView(R.id.trend_poster)
        ImageView poster;

        @InjectView(R.id.trend_title)
        TextView movieTitle;

        @InjectView(R.id.trend_year)
        TextView year;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);

            int screenWidth = StaticHelpers.getScreenWidth(view.getContext());
            itemWidth = (screenWidth / 2);
            itemHeight = (int) ((double) itemWidth / 0.677);
            mMargin = StaticHelpers.getPixelsFromDp(view.getContext(), 2);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.trending_list_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //Load image
        Picasso.with(view.getContext()).load(trendMovies.get(i).getPoster()).resize(itemWidth, itemHeight).into(viewHolder.poster);

        //Set title
        viewHolder.movieTitle.setText(trendMovies.get(i).getTitle());

        //Set year
        String movieYear = String.valueOf(trendMovies.get(i).getYear());
        viewHolder.year.setText(movieYear);

        //Scale poster
        viewHolder.poster.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return view;
    }

    @Override
    public int getCount() {
        return trendMovies.size();
    }

    @Override
    public Object getItem(int i) {
        return trendMovies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
