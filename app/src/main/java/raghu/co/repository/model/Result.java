
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result implements Parcelable
{

    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("cars")
    @Expose
    private List<Car> cars = null;
    @SerializedName("airport")
    @Expose
    private String airport;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    protected Result(Parcel in) {
        this.provider = ((Provider) in.readValue((Provider.class.getClassLoader())));
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
        in.readList(this.cars, (raghu.co.repository.model.Car.class.getClassLoader()));
        this.airport = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Result withProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Result withLocation(Location location) {
        this.location = location;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Result withAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Result withCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public Result withAirport(String airport) {
        this.airport = airport;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(provider);
        dest.writeValue(location);
        dest.writeValue(address);
        dest.writeList(cars);
        dest.writeValue(airport);
    }

    public int describeContents() {
        return  0;
    }

}
