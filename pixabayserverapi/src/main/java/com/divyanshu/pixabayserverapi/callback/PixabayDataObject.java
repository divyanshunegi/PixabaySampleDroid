package com.divyanshu.pixabayserverapi.callback;
import java.util.List;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */
public class PixabayDataObject {

    public int totalHits;
    public List<Hits> hits;
    public int total;

    public static class Hits {
        public int previewHeight;
        public int likes;
        public int favorites;
        public String tags;
        public int webformatHeight;
        public int views;
        public int webformatWidth;
        public int previewWidth;
        public int comments;
        public int downloads;
        public String pageURL;
        public String previewURL;
        public String webformatURL;
        public int imageWidth;
        public int user_id;
        public String user;
        public String type;
        public int id;
        public String userImageURL;
        public int imageHeight;
    }
}
