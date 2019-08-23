package com.example.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieTrailerModel implements Parcelable {
    /**
     * id : 550
     * results : [{"id":"533ec654c3a36854480003eb","iso_639_1":"en","iso_3166_1":"US","key":"SUXWAEX2jlg","name":"Trailer 1","site":"YouTube","size":720,"type":"Trailer"}]
     */

    private int id;
    private List<ResultsBean> results;

    protected MovieTrailerModel(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<MovieTrailerModel> CREATOR = new Creator<MovieTrailerModel>() {
        @Override
        public MovieTrailerModel createFromParcel(Parcel in) {
            return new MovieTrailerModel( in );
        }

        @Override
        public MovieTrailerModel[] newArray(int size) {
            return new MovieTrailerModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt( id );
    }

    public static class ResultsBean {
        /**
         * id : 533ec654c3a36854480003eb
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : SUXWAEX2jlg
         * name : Trailer 1
         * site : YouTube
         * size : 720
         * type : Trailer
         */

        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
