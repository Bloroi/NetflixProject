
package com.example.alexandre.netflixlibraryproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Actor {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("id")
    private Long mId;
    @SerializedName("known_for")
    private List<KnownFor> mKnownFor;
    @SerializedName("name")
    private String mName;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("profile_path")
    private String mProfilePath;
    private String mcharacter;

    public Actor(String name,String character,String poster){
        mName = name;
        mcharacter = character;
        mProfilePath=poster;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<KnownFor> getKnownFor() {
        return mKnownFor;
    }

    public void setKnownFor(List<KnownFor> knownFor) {
        mKnownFor = knownFor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    public String getCharacter() {
        return mcharacter;
    }

    public void setCharacter(String chara) {
        mcharacter = chara;
    }

    public String toString(){
        return getName()+" "+getCharacter()+" "+getProfilePath();
    }

}
