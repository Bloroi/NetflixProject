
package com.example.alexandre.netflixlibraryproject.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Genre {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;

    public Genre(Long mId){
        this.mId =mId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean equals(Object o){
        if(o instanceof Genre){
            Genre g = (Genre) o;
            return this.mId == g.mId;
        }
        return false;
    }

}
