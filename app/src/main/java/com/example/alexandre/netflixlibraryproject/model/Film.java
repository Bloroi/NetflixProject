
package com.example.alexandre.netflixlibraryproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Film implements Parcelable{

    @SerializedName("category")
    private String mCategory;
    @SerializedName("director")
    private String mDirector;
    @SerializedName("mediatype")
    private Long mMediatype;
    @SerializedName("poster")
    private String mPoster;
    @SerializedName("rating")
    private String mRating;
    @SerializedName("release_year")
    private String mReleaseYear;
    @SerializedName("show_cast")
    private String mShowCast;
    @SerializedName("show_id")
    private Long mShowId;
    @SerializedName("show_title")
    private String mShowTitle;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("unit")
    private Long mUnit;

    public Film(){

    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public Long getMediatype() {
        return mMediatype;
    }

    public void setMediatype(Long mediatype) {
        mMediatype = mediatype;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getReleaseYear() {
        return mReleaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        mReleaseYear = releaseYear;
    }

    public String getShowCast() {
        return mShowCast;
    }

    public void setShowCast(String showCast) {
        mShowCast = showCast;
    }

    public Long getShowId() {
        return mShowId;
    }

    public void setShowId(Long showId) {
        mShowId = showId;
    }

    public String getShowTitle() {
        return mShowTitle;
    }

    public void setShowTitle(String showTitle) {
        mShowTitle = showTitle;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public Long getUnit() {
        return mUnit;
    }

    public void setUnit(Long unit) {
        mUnit = unit;
    }


    protected Film(Parcel in) {
        mShowTitle = in.readString();
        mCategory = in.readString();
        mDirector = in.readString();
        mPoster = in.readString();
        mSummary = in.readString();
        mReleaseYear = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getShowTitle());
        parcel.writeString(getCategory());
        parcel.writeString(getDirector());
        parcel.writeString(getPoster());
        parcel.writeString(getSummary());
        parcel.writeString(getReleaseYear());

    }
}
