
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleInfo implements Parcelable
{

    @SerializedName("acriss_code")
    @Expose
    private String acrissCode;
    @SerializedName("transmission")
    @Expose
    private String transmission;
    @SerializedName("fuel")
    @Expose
    private String fuel;
    @SerializedName("air_conditioning")
    @Expose
    private boolean airConditioning;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("type")
    @Expose
    private String type;
    public final static Parcelable.Creator<VehicleInfo> CREATOR = new Creator<VehicleInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VehicleInfo createFromParcel(Parcel in) {
            return new VehicleInfo(in);
        }

        public VehicleInfo[] newArray(int size) {
            return (new VehicleInfo[size]);
        }

    }
    ;

    protected VehicleInfo(Parcel in) {
        this.acrissCode = ((String) in.readValue((String.class.getClassLoader())));
        this.transmission = ((String) in.readValue((String.class.getClassLoader())));
        this.fuel = ((String) in.readValue((String.class.getClassLoader())));
        this.airConditioning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VehicleInfo() {
    }

    public String getAcrissCode() {
        return acrissCode;
    }

    public void setAcrissCode(String acrissCode) {
        this.acrissCode = acrissCode;
    }

    public VehicleInfo withAcrissCode(String acrissCode) {
        this.acrissCode = acrissCode;
        return this;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public VehicleInfo withTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public VehicleInfo withFuel(String fuel) {
        this.fuel = fuel;
        return this;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public VehicleInfo withAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
        return this;
    }

    public String getAirConditioning() {
        if(airConditioning) {
            return "Has Air Conditioning" ;
        }
        return "None ";
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public VehicleInfo withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VehicleInfo withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(acrissCode);
        dest.writeValue(transmission);
        dest.writeValue(fuel);
        dest.writeValue(airConditioning);
        dest.writeValue(category);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
