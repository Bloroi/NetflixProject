package com.example.alexandre.netflixlibraryproject.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utils {

    public static class Intent{
        public static final String TAG_APIKEY= "d3f617c2a1b78f7220853c4627424fe5";
    }

    public static String formatDate(String date) {

        if(date.isEmpty()){
            return "Aucune info";
        }
        else if (date.length() == 10) {
            String oldFormat = "yyyy-MM-dd";
            String newFormat = "dd-MM-yyyy";

            String formatedDate = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
            Date myDate = null;
            try {
                myDate = dateFormat.parse(date);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat timeFormat = new SimpleDateFormat(newFormat);
            formatedDate = timeFormat.format(myDate);
            return formatedDate;
        }
        else{
            return "...";
        }
    }

}
