package strenja.foodapp;

import java.io.Serializable;

public class Recept implements Serializable {

    private String id;
    private String mainName;
    private String descriptName;
    private String urlSlika;
    //private String totalMinute;
    private String rating;


    public Recept(String id, String mainName, String descriptName, String urlSlika, String rating ){
        this.id = id;
        this.mainName =mainName;
        this.descriptName = descriptName;
        this.urlSlika=urlSlika;
        this.rating=rating;


    }
    public Recept(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getDescriptName() {
        return descriptName;
    }

    public void setDescriptName(String descriptName) {
        this.descriptName = descriptName;
    }

    public String getUrlSlika() {
        return urlSlika;
    }

    public void setUrlSlika(String urlSlika) {
        this.urlSlika = urlSlika;
    }

    public String getRating() {        return rating;    }

    public void setRating(String rating) {        this.rating = rating;    }


}
