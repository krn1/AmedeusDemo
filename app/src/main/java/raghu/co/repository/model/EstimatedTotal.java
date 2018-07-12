
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimatedTotal implements Parcelable
{

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    public final static Parcelable.Creator<EstimatedTotal> CREATOR = new Creator<EstimatedTotal>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EstimatedTotal createFromParcel(Parcel in) {
            return new EstimatedTotal(in);
        }

        public EstimatedTotal[] newArray(int size) {
            return (new EstimatedTotal[size]);
        }

    }
    ;

    protected EstimatedTotal(Parcel in) {
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.currency = ((String) in.readValue((String.class.getClassLoader())));
    }

    public EstimatedTotal() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public EstimatedTotal withAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public EstimatedTotal withCurrency(String currency) {
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
