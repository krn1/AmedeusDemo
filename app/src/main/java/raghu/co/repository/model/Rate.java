
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private Price price;
    public final static Parcelable.Creator<Rate> CREATOR = new Creator<Rate>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rate createFromParcel(Parcel in) {
            return new Rate(in);
        }

        public Rate[] newArray(int size) {
            return (new Rate[size]);
        }

    }
    ;

    protected Rate(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Price) in.readValue((Price.class.getClassLoader())));
    }

    public Rate() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Rate withType(String type) {
        this.type = type;
        return this;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Rate withPrice(Price price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
