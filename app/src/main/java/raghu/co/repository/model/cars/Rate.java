
package raghu.co.repository.model.cars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private Price price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
