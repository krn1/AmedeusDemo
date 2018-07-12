
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price implements Parcelable
{

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    public final static Parcelable.Creator<Price> CREATOR = new Creator<Price>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Price createFromParcel(Parcel in) {
            return new Price(in);
        }

        public Price[] newArray(int size) {
            return (new Price[size]);
        }

    }
    ;

    protected Price(Parcel in) {
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.currency = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Price() {
    }

    public String getAmount() {
        return "$"+amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Price withAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Price withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amount);
        dest.writeValue(currency);
    }

    public int describeContents() {
        return  0;
    }

}
