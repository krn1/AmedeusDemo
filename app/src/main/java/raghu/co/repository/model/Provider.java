
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provider implements Parcelable
{

    @SerializedName("company_code")
    @Expose
    private String companyCode;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    public final static Parcelable.Creator<Provider> CREATOR = new Creator<Provider>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        public Provider[] newArray(int size) {
            return (new Provider[size]);
        }

    }
    ;

    protected Provider(Parcel in) {
        this.companyCode = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Provider() {
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Provider withCompanyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Provider withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(companyCode);
        dest.writeValue(companyName);
    }

    public int describeContents() {
        return  0;
    }

}
