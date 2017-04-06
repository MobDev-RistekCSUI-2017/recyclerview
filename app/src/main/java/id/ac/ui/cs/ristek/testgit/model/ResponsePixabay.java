
package id.ac.ui.cs.ristek.testgit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePixabay {

    @SerializedName("totalHits")
    @Expose
    public int totalHits;
    @SerializedName("hits")
    @Expose
    public List<Hit> hits = null;
    @SerializedName("total")
    @Expose
    public int total;

}
