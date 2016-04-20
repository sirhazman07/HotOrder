package androidcourse.hotorder;

import android.content.Intent;

/**
 * Created by Harold on 14/04/2016.
 */
public class Chilli {
    private String mName;
    private String mSpeciesName;
    private String mCoverImageName;
    private String mDescription;
    private int mFlavourStar;
    private int mHeatStar;

    public Chilli(String name,String speciesName, String coverImageName , String description , int flavourStar, int heatStar){
        mName = name;
        mSpeciesName = speciesName;
        mCoverImageName = coverImageName;
        mDescription = description;
        mFlavourStar = flavourStar;
        mHeatStar = heatStar;

    }

    //Getters for all attributes

    public String getName() { return mName; }

    public String getSpeciesName() {return mSpeciesName;}

    public String getCoverImageName() {return mCoverImageName;}

    public String getDescription() { return mDescription;}

    public int getFlavourStar() { return mFlavourStar;}

    public int getHeatStar() {  return mHeatStar; }
}
