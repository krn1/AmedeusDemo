
package raghu.co.repository.model.cars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleInfo {

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

    public String getAcrissCode() {
        return acrissCode;
    }

    public void setAcrissCode(String acrissCode) {
        this.acrissCode = acrissCode;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
