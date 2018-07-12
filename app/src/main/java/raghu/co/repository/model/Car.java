
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import raghu.co.util.DistanceUtil;

public class Car implements Parcelable {

    @SerializedName("vehicle_info")
    private VehicleInfo vehicleInfo;
    @SerializedName("rates")
    private List<Rate> rates;
    @SerializedName("estimated_total")
    private EstimatedTotal estimatedTotal;
    @SerializedName("image")
    private Image image;

    private String companyName;
    private Location location;
    private Location userLocation;
    private Address address;
    private String airport;
    private float distance;
    private int id;

    public Car() {
    }

    public Car(VehicleInfo vehicleInfo, List<Rate> rates, EstimatedTotal estimatedTotal, Image image, String companyName, Location
            location,Location userLocation, Address address, String airport, float distance,int id) {
        this.vehicleInfo = vehicleInfo;
        this.rates = rates;
        this.estimatedTotal = estimatedTotal;
        this.image = image;
        this.companyName = companyName;
        this.location = location;
        this.userLocation = userLocation;
        this.address = address;
        this.airport = airport;
        this.distance = distance;
        this.id = id;
    }

    protected Car(Parcel in) {
        vehicleInfo = in.readParcelable(VehicleInfo.class.getClassLoader());
        rates = in.createTypedArrayList(Rate.CREATOR);
        estimatedTotal = in.readParcelable(EstimatedTotal.class.getClassLoader());
        image = in.readParcelable(Image.class.getClassLoader());
        companyName = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        userLocation = in.readParcelable(Location.class.getClassLoader());
        address = in.readParcelable(Address.class.getClassLoader());
        airport = in.readString();
        distance = in.readFloat();
        id= in.readInt();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public EstimatedTotal getEstimatedTotal() {
        return estimatedTotal;
    }

    public void setEstimatedTotal(EstimatedTotal estimatedTotal) {
        this.estimatedTotal = estimatedTotal;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Location getCarLocation() {
        return location;
    }

    public void setCarLocation(Location location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }


    public String getDistance() {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);

        String distanceString = decimalFormat.format(distance);
        return "Distance : " + distanceString + " m";
    }

    public void setDistance(float userLatitude, float userLongitude) {
        distance = (float) DistanceUtil.distance(userLatitude, userLongitude,
                location.getLatitude(), location.getLongitude());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(vehicleInfo, flags);
        dest.writeTypedList(rates);
        dest.writeParcelable(estimatedTotal, flags);
        dest.writeParcelable(image, flags);
        dest.writeString(companyName);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(userLocation, flags);
        dest.writeParcelable(address, flags);
        dest.writeString(airport);
        dest.writeFloat(distance);
        dest.writeInt(id);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car that = (Car) o;
        return Objects.equals(vehicleInfo, that.vehicleInfo) &&
                Objects.equals(rates, that.rates) &&
                Objects.equals(id, that.id) &&
                Objects.equals(estimatedTotal, that.estimatedTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicleInfo);
    }
}

