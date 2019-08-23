package com.example.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "movies_table")
public class MoviesDB implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id=-1;
    private double vote_average;
    private String poster_path;
    private String overview;
    private String title;
    @ColumnInfo(name = "exist")
    @NonNull
    private boolean exist;
    private String ImagePath;
    private String release_date;



    public MoviesDB( double vote_average, String poster_path, String overview, String title,boolean exist,String ImagePath,String release_date) {
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.overview = overview;
        this.title = title;
        this.exist=exist;
        this.ImagePath=ImagePath;
        this.release_date=release_date;
    }


    protected MoviesDB(Parcel in) {
        id = in.readInt();
        vote_average = in.readDouble();
        poster_path = in.readString();
        overview = in.readString();
        title = in.readString();
        exist = in.readByte() != 0;
        ImagePath = in.readString();
        release_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( id );
        dest.writeDouble( vote_average );
        dest.writeString( poster_path );
        dest.writeString( overview );
        dest.writeString( title );
        dest.writeByte( (byte) (exist ? 1 : 0) );
        dest.writeString( ImagePath );
        dest.writeString( release_date );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviesDB> CREATOR = new Creator<MoviesDB>() {
        @Override
        public MoviesDB createFromParcel(Parcel in) {
            return new MoviesDB( in );
        }

        @Override
        public MoviesDB[] newArray(int size) {
            return new MoviesDB[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }


    public void setImagePath(String imagePath) {
        ImagePath=imagePath;
    }

    public int getId() {
        return id;
    }


    public double getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }
    /**
     public byte[] getImage() {
     return image;
     }*/
    public boolean isExist() {
        return exist;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public String getRelease_date() {
        return release_date;
    }


}