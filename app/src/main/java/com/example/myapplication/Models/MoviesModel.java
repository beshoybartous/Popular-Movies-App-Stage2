package com.example.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class MoviesModel implements Parcelable {


    /**
     * page : 1
     * total_results : 19906
     * total_pages : 996
     * results : [{"vote_count":1395,"id":429617,"video":false,"vote_average":7.8,"title":"Spider-Man: Far from Home","popularity":515.884,"poster_path":"/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg","original_language":"en","original_title":"Spider-Man: Far from Home","genre_ids":[],"backdrop_path":"/dihW2yTsvQlust7mSuAqJDtqW7k.jpg","adult":false,"overview":"Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.","release_date":"2019-06-28"},{"vote_count":2396,"id":399579,"video":false,"vote_average":6.7,"title":"Alita: Battle Angel","popularity":313.246,"poster_path":"/xRWht48C2V8XNfzvPehyClOvDni.jpg","original_language":"en","original_title":"Alita: Battle Angel","genre_ids":[28,878,53,12],"backdrop_path":"/8RKBHHRqOMOLh5qW3sS6TSFTd8h.jpg","adult":false,"overview":"When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.","release_date":"2019-01-31"},{"vote_count":1434,"id":458156,"video":false,"vote_average":7.1,"title":"John Wick: Chapter 3 \u2013 Parabellum","popularity":182.447,"poster_path":"/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg","original_language":"en","original_title":"John Wick: Chapter 3 \u2013 Parabellum","genre_ids":[80,28,53],"backdrop_path":"/vVpEOvdxVBP2aV166j5Xlvb5Cdc.jpg","adult":false,"overview":"Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin\u2019s guild, the High Table, John Wick is excommunicado, but the world\u2019s most ruthless hit men and women await his every turn.","release_date":"2019-05-15"},{"vote_count":1263,"id":301528,"video":false,"vote_average":7.8,"title":"Toy Story 4","popularity":177.796,"poster_path":"/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg","original_language":"en","original_title":"Toy Story 4","genre_ids":[12,16,35,10751],"backdrop_path":"/m67smI1IIMmYzCl9axvKNULVKLr.jpg","adult":false,"overview":"Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.","release_date":"2019-06-19"},{"vote_count":33,"id":420818,"video":false,"vote_average":6.4,"title":"The Lion King","popularity":172.074,"poster_path":"/dzBtMocZuJbjLOXvrl4zGYigDzh.jpg","original_language":"en","original_title":"The Lion King","genre_ids":[12,16,10751,18],"backdrop_path":"/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg","adult":false,"overview":"Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother\u2014and former heir to the throne\u2014has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.","release_date":"2019-07-12"},{"vote_count":602,"id":456740,"video":false,"vote_average":5,"title":"Hellboy","popularity":140.641,"poster_path":"/bk8LyaMqUtaQ9hUShuvFznQYQKR.jpg","original_language":"en","original_title":"Hellboy","genre_ids":[28,12,14,27,878],"backdrop_path":"/xx65EuzQim0DN2zzJ9v9YhUVai6.jpg","adult":false,"overview":"Hellboy comes to England, where he must defeat Nimue, Merlin's consort and the Blood Queen. But their battle will bring about the end of the world, a fate he desperately tries to turn away.","release_date":"2019-04-10"},{"vote_count":2384,"id":287947,"video":false,"vote_average":7.1,"title":"Shazam!","popularity":125.538,"poster_path":"/xnopI5Xtky18MPhK40cZAGAOVeV.jpg","original_language":"en","original_title":"Shazam!","genre_ids":[28,35,14],"backdrop_path":"/bi4jh0Kt0uuZGsGJoUUfqmbrjQg.jpg","adult":false,"overview":"A boy is given the ability to become an adult superhero in times of need with a single magic word.","release_date":"2019-03-23"},{"vote_count":6262,"id":299537,"video":false,"vote_average":7,"title":"Captain Marvel","popularity":108.273,"poster_path":"/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg","original_language":"en","original_title":"Captain Marvel","genre_ids":[28,12,878],"backdrop_path":"/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg","adult":false,"overview":"The story follows Carol Danvers as she becomes one of the universe\u2019s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.","release_date":"2019-03-06"},{"vote_count":259,"id":521029,"video":false,"vote_average":6.1,"title":"Annabelle Comes Home","popularity":107.076,"poster_path":"/qWsHMrbg9DsBY3bCMk9jyYCRVRs.jpg","original_language":"en","original_title":"Annabelle Comes Home","genre_ids":[27],"backdrop_path":"/dBt0DoFfbhOI4ypUfRj1uTq623M.jpg","adult":false,"overview":"Determined to keep Annabelle from wreaking more havoc, demonologists Ed and Lorraine Warren bring the possessed doll to the locked artifacts room in their home, placing her \u201csafely\u201d behind sacred glass and enlisting a priest\u2019s holy blessing. But an unholy night of horror awaits as Annabelle awakens the evil spirits in the room, who all set their sights on a new target\u2014the Warrens' ten-year-old daughter, Judy, and her friends.","release_date":"2019-06-26"},{"vote_count":1408,"id":537915,"video":false,"vote_average":5.8,"title":"After","popularity":105.166,"poster_path":"/u3B2YKUjWABcxXZ6Nm9h10hLUbh.jpg","original_language":"en","original_title":"After","genre_ids":[18,10749],"backdrop_path":"/997ToEZvF2Obp9zNZbY5ELVnmrW.jpg","adult":false,"overview":"Tessa Young is a dedicated student, dutiful daughter and loyal girlfriend to her high school sweetheart. Entering her first semester of college, Tessa's guarded world opens up when she meets Hardin Scott, a mysterious and brooding rebel who makes her question all she thought she knew about herself -- and what she wants out of life.","release_date":"2019-04-11"},{"vote_count":15,"id":511987,"video":false,"vote_average":6.9,"title":"Crawl","popularity":102.211,"poster_path":"/mKxpYRIrCZLxZjNqpocJ2RdQW8v.jpg","original_language":"en","original_title":"Crawl","genre_ids":[53,28,27],"backdrop_path":"/2cAce3oD0Hh7f5XxuXmNXa1WiuZ.jpg","adult":false,"overview":"While struggling to save her father during a Category 5 hurricane, a young woman finds herself trapped inside a flooding house and fighting for her life against Florida\u2019s most savage and feared predators.","release_date":"2019-07-11"},{"vote_count":7699,"id":920,"video":false,"vote_average":6.7,"title":"Cars","popularity":101.348,"poster_path":"/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg","original_language":"en","original_title":"Cars","genre_ids":[16,12,35,10751],"backdrop_path":"/a1MlbLBk5Sy6YvMbSuKfwGlDVlb.jpg","adult":false,"overview":"Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters.","release_date":"2006-06-08"},{"vote_count":14339,"id":299536,"video":false,"vote_average":8.3,"title":"Avengers: Infinity War","popularity":90.396,"poster_path":"/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg","original_language":"en","original_title":"Avengers: Infinity War","genre_ids":[12,28,14],"backdrop_path":"/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg","adult":false,"overview":"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.","release_date":"2018-04-25"},{"vote_count":21,"id":566555,"video":false,"vote_average":5,"title":"Detective Conan: The Fist of Blue Sapphire","popularity":84.375,"poster_path":"/86Y6qM8zTn3PFVfCm9J98Ph7JEB.jpg","original_language":"ja","original_title":"名探偵コナン 紺青の拳（フィスト）","genre_ids":[16,28,18,9648,35],"backdrop_path":"/wf6VDSi4aFCZfuD8sX8JAKLfJ5m.jpg","adult":false,"overview":"23rd movie in the \"Detective Conan\" franchise.","release_date":"2019-04-12"},{"vote_count":7620,"id":299534,"video":false,"vote_average":8.4,"title":"Avengers: Endgame","popularity":83.128,"poster_path":"/or06FN3Dka5tukK1e9sl16pB3iy.jpg","original_language":"en","original_title":"Avengers: Endgame","genre_ids":[12,878,28],"backdrop_path":"/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg","adult":false,"overview":"After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.","release_date":"2019-04-24"},{"vote_count":2160,"id":420817,"video":false,"vote_average":7.1,"title":"Aladdin","popularity":82.503,"poster_path":"/3iYQTLGoy7QnjcUYRJy4YrAgGvp.jpg","original_language":"en","original_title":"Aladdin","genre_ids":[12,14,10749,35,10751],"backdrop_path":"/v4yVTbbl8dE1UP2dWu5CLyaXOku.jpg","adult":false,"overview":"A kindhearted street urchin named Aladdin embarks on a magical adventure after finding a lamp that releases a wisecracking genie while a power-hungry Grand Vizier vies for the same lamp that has the power to make their deepest wishes come true.","release_date":"2019-05-22"},{"vote_count":7,"id":553100,"video":false,"vote_average":7.4,"title":"Wild and Free","popularity":80.906,"poster_path":"/jLGNqaymD0ygyhafhv5fM3nXcge.jpg","original_language":"tl","original_title":"Wild and Free","genre_ids":[18],"backdrop_path":"/9v4nPAyVvtGONV5NtPkDHA9bczS.jpg","adult":false,"overview":"Ellie and Jake fall in love, but struggle with their relationship when they discover an unexpected connection between their pasts.","release_date":"2018-10-10"},{"vote_count":1513,"id":329996,"video":false,"vote_average":6.5,"title":"Dumbo","popularity":79.693,"poster_path":"/A7XkpLfNH0El2yyDLc4b0KLAKvE.jpg","original_language":"en","original_title":"Dumbo","genre_ids":[12,10751,14],"backdrop_path":"/5tFt6iuGnKapHl5tw0X0cKcnuVo.jpg","adult":false,"overview":"A young elephant, whose oversized ears enable him to fly, helps save a struggling circus, but when the circus plans a new venture, Dumbo and his friends discover dark secrets beneath its shiny veneer.","release_date":"2019-03-27"},{"vote_count":327,"id":486131,"video":false,"vote_average":6.3,"title":"Shaft","popularity":76.118,"poster_path":"/kfZqwGuvEBAysAbCsa0QLKoSYR.jpg","original_language":"en","original_title":"Shaft","genre_ids":[28,80,35],"backdrop_path":"/103d4ObBCWbB6PtOOjZ7C1FSpVl.jpg","adult":false,"overview":"JJ, aka John Shaft Jr., may be a cyber security expert with a degree from MIT, but to uncover the truth behind his best friend\u2019s untimely death, he needs an education only his dad can provide. Absent throughout JJ\u2019s youth, the legendary locked-and-loaded John Shaft agrees to help his progeny navigate Harlem\u2019s heroin-infested underbelly.","release_date":"2019-06-14"},{"vote_count":10324,"id":245891,"video":false,"vote_average":7.2,"title":"John Wick","popularity":71.197,"poster_path":"/b9uYMMbm87IBFOq59pppvkkkgNg.jpg","original_language":"en","original_title":"John Wick","genre_ids":[28,53],"backdrop_path":"/mFb0ygcue4ITixDkdr7wm1Tdarx.jpg","adult":false,"overview":"Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.","release_date":"2014-10-22"}]
     */

    private int page;
    private int total_results;
    private int total_pages;
    private List<ResultsBean> results;

    protected MoviesModel(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( page );
        dest.writeInt( total_results );
        dest.writeInt( total_pages );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel( in );
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * vote_count : 1395
         * id : 429617
         * video : false
         * vote_average : 7.8
         * title : Spider-Man: Far from Home
         * popularity : 515.884
         * poster_path : /rjbNpRMoVvqHmhmksbokcyCr7wn.jpg
         * original_language : en
         * original_title : Spider-Man: Far from Home
         * genre_ids : []
         * backdrop_path : /dihW2yTsvQlust7mSuAqJDtqW7k.jpg
         * adult : false
         * overview : Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.
         * release_date : 2019-06-28
         */

        private int vote_count;
        private int id;
        private boolean video;
        private double vote_average;
        private String title;
        private double popularity;
        private String poster_path;
        private String original_language;
        private String original_title;
        private String backdrop_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private List<?> genre_ids;

        protected ResultsBean(Parcel in) {
            vote_count = in.readInt();
            id = in.readInt();
            video = in.readByte() != 0;
            vote_average = in.readDouble();
            title = in.readString();
            popularity = in.readDouble();
            poster_path = in.readString();
            original_language = in.readString();
            original_title = in.readString();
            backdrop_path = in.readString();
            adult = in.readByte() != 0;
            overview = in.readString();
            release_date = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel in) {
                return new ResultsBean( in );
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPoster_path() {
            return "http://image.tmdb.org/t/p/w500"+poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<?> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<?> genre_ids) {
            this.genre_ids = genre_ids;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt( vote_count );
            parcel.writeInt( id );
            parcel.writeByte( (byte) (video ? 1 : 0) );
            parcel.writeDouble( vote_average );
            parcel.writeString( title );
            parcel.writeDouble( popularity );
            parcel.writeString( poster_path );
            parcel.writeString( original_language );
            parcel.writeString( original_title );
            parcel.writeString( backdrop_path );
            parcel.writeByte( (byte) (adult ? 1 : 0) );
            parcel.writeString( overview );
            parcel.writeString( release_date );
        }
    }
}