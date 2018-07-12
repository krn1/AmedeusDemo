
package raghu.co.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RentalCars implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<RentalCars> CREATOR = new Creator<RentalCars>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RentalCars createFromParcel(Parcel in) {
            return new RentalCars(in);
        }

        public RentalCars[] newArray(int size) {
            return (new RentalCars[size]);
        }

    }
    ;

    protected RentalCars(Parcel in) {
        in.readList(this.results, (raghu.co.repository.model.Result.class.getClassLoader()));
    }

    public RentalCars() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public RentalCars withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}
