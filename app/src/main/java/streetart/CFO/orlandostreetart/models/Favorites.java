package streetart.CFO.orlandostreetart.models;

/**
 * Created by Eric on 3/26/2019.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorites {

    @SerializedName("submissions")
    @Expose
    private List<Submission> submissions = null;

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }


    public class Submission {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("title")
        @Expose
        private Object title;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("photo_url")
        @Expose
        private String photoUrl;
        @SerializedName("thumb_url")
        @Expose
        private String thumbUrl;
        @SerializedName("tiny_url")
        @Expose
        private String tinyUrl;
        @SerializedName("artist")
        @Expose
        private Object artist;
        @SerializedName("location_note")
        @Expose
        private Object locationNote;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("nickname")
        @Expose
        private String nickname;
        @SerializedName("favorite")
        @Expose
        private Boolean favorite;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getTinyUrl() {
            return tinyUrl;
        }

        public void setTinyUrl(String tinyUrl) {
            this.tinyUrl = tinyUrl;
        }

        public Object getArtist() {
            return artist;
        }

        public void setArtist(Object artist) {
            this.artist = artist;
        }

        public Object getLocationNote() {
            return locationNote;
        }

        public void setLocationNote(Object locationNote) {
            this.locationNote = locationNote;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Boolean getFavorite() {
            return favorite;
        }

        public void setFavorite(Boolean favorite) {
            this.favorite = favorite;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

    }
}