package androidcourse.hotorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Harold on 14/04/2016.
 */
public class ChillisAdapter extends ArrayAdapter<Chilli> {
    private Context mContext;
    private int mLayoutResourceId;
    private Chilli[] mChillis;

    public ChillisAdapter(Context context, int resource, Chilli[] objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutResourceId = resource;
        mChillis = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId, parent, false);

        ImageView chilliImage = (ImageView) row.findViewById(R.id.imageViewChilli);
        TextView chilliName = (TextView) row.findViewById(R.id.textViewChilliName);
        TextView chilliSpecies = (TextView) row.findViewById(R.id.textViewChilliSpecies);
        TextView description = (TextView) row.findViewById(R.id.textViewDescription);
        TextView chilliFlavour = (TextView) row.findViewById(R.id.textViewFlavour);
        TextView chilliHeat = (TextView) row.findViewById(R.id.textViewHeat);
        RatingBar chilliFlavourBar = (RatingBar) row.findViewById(R.id.ratingBarFlavour);
        RatingBar chilliHeatBar = (RatingBar) row.findViewById(R.id.ratingBarHeat);

        Chilli chilli = mChillis[position];

        //Get the id for the drawable (image) for the game
        int resId = mContext.getResources().getIdentifier(chilli.getCoverImageName(),
                "drawable", mContext.getPackageName());


        chilliImage.setImageResource(resId);
        chilliName.setText(chilli.getName());
        chilliSpecies.setText(chilli.getSpeciesName());
        description.setText(chilli.getDescription());
        chilliFlavourBar.setNumStars(chilli.getFlavourStar());
        chilliHeatBar.setNumStars(chilli.getHeatStar());

        return row;
    }
}

