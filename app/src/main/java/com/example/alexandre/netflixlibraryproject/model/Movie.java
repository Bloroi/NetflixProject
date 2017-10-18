
package com.example.alexandre.netflixlibraryproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Movie {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    private List<String> Genres = new ArrayList<>();
    @SerializedName("id")
    private Long mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vote_average")
    private float mVoteAverage;
    private List<Actor> actors = new ArrayList<>();
    private List<String> companys = new ArrayList<>();

    public Movie(Long Id,String poster, String title,String titleOriginal,float vote,String date){
        mId = Id;
        mPosterPath = poster;
        mTitle = title;
        mOriginalTitle = titleOriginal;
        mVoteAverage = vote;
        mReleaseDate = date;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }


    public List<String> getGenre() {
        return Genres;
    }

    public String getGenreString(){
        String tmp = "";
        for(int i = 0;i<Genres.size();i++){
            tmp+=Genres.get(i);
            if(i!=Genres.size()-1){
                tmp+=", ";
            }
        }
        return tmp;
    }

    public void setGenre(List<String> genres) {
        Genres = genres;
    }

    public void addGenre(String genre){
        this.Genres.add(genre);
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public List<Actor> getActors() {
        return actors;
    }


    public void setActor(List<Actor> listActor) {
        actors = listActor;
    }

    public void addActor(Actor actor){
        this.actors.add(actor);
    }

    public List<String> getCompany() {
        return companys;
    }


    public void setCompany(List<String> listCompanys) {
        companys= listCompanys;
    }

    public void addCompany(String str){
        this.companys.add(str);
    }

    public String getCompanyString(){
        String tmp = "";
        for(int i = 0;i<companys.size();i++){
            tmp+=companys.get(i);
            if(i!=companys.size()-1){
                tmp+=", ";
            }
        }
        return tmp;
    }


    public String toString(){
        return "id : "+mId+" titre : "+getTitle()+" Titre original : "+getOriginalTitle()+" note : "+getVoteAverage()+" année :"+getReleaseDate();
    }
}
