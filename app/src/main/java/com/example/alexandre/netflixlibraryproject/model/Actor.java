
package com.example.alexandre.netflixlibraryproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Actor {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("profile_path")
    private String mProfilePath;
    private String mcharacter;
    private String mbirthday;
    private String mdeathday;
    private String mbiography;
    private String mplace_of_birth;
    private List<Movie> movies= new ArrayList<>();
    private List<Serie> series = new ArrayList<>();


    public Actor(String name,String character,String poster){
        mName = name;
        mcharacter = character;
        mProfilePath=poster;
    }

    public Actor(Long id, String name, String poster){
        mId = id;
        mName = name;
        mProfilePath=poster;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public String getProfilePath() { return mProfilePath; }

    public void setProfilePath(String profilePath) { mProfilePath = profilePath; }

    public String getCharacter() { return mcharacter; }

    public void setCharacter(String mcharacter) { this.mcharacter = mcharacter; }

    public String getbirthday() { return mbirthday; }

    public void setbirthday(String mbirthday) { this.mbirthday = mbirthday; }

    public String getdeathday() { return mdeathday; }

    public void setdeathday(String mdeathday) { this.mdeathday = mdeathday; }

    public String getbiography() { return mbiography; }

    public void setbiography(String mbiography) { this.mbiography = mbiography; }

    public String getPlace_of_birth() { return mplace_of_birth; }

    public void setplace_of_birth(String mplace_of_birth) { this.mplace_of_birth = mplace_of_birth; }

    public List<Movie> getMovies() { return movies; }

    public void setMovies(List<Movie> movies) { this.movies = movies; }

    public void addMovie(Movie m){
        this.movies.add(m);
    }

    public void setSeries(List<Serie> series) { this.series = series; }

    public void addSerie(Serie s){
        this.series.add(s);
    }

    @Override
    public String toString() {
        return "id :" + mId + " name : " + mName + " profilePath : " + mProfilePath + " character : " + mcharacter + " birdday : " + mbirthday + " deathday : " + mdeathday + " biography : " + mbirthday;
    }


}

