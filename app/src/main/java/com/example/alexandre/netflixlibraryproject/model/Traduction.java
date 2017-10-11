
package com.example.alexandre.netflixlibraryproject.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Traduction {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("lang")
    private String mLang;
    @SerializedName("text")
    private List<String> mText;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        mLang = lang;
    }

    public List<String> getText() {
        return mText;
    }

    public void setText(List<String> text) {
        mText = text;
    }

}
