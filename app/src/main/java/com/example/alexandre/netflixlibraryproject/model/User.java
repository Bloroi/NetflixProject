package com.example.alexandre.netflixlibraryproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Florian on 08-10-17.
 */

public class User implements Parcelable {

    protected int userId;
    protected String username;
    protected String password;
    protected String nom;
    protected String prenom;
    protected String email;
    protected int phone;

    public User(){}


    protected User(Parcel in) {
        userId = in.readInt();
        username = in.readString();
        password = in.readString();
        nom = in.readString();
        prenom = in.readString();
        email = in.readString();
        phone = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) { return new User(in); }

        @Override
        public User[] newArray(int size) { return new User[size]; }
    };

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() {  return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getPhone() { return phone; }
    public void setPhone(int phone) { this.phone = phone; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(email);
        dest.writeInt(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
