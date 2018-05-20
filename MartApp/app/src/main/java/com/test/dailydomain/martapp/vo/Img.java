package com.test.dailydomain.martapp.vo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Img implements Parcelable
{

    @SerializedName("h")
    @Expose
    public Integer h;
    @SerializedName("w")
    @Expose
    public Integer w;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("position")
    @Expose
    public Integer position;
    public final static Parcelable.Creator<Img> CREATOR = new Creator<Img>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Img createFromParcel(Parcel in) {
            return new Img(in);
        }

        public Img[] newArray(int size) {
            return (new Img[size]);
        }

    }
            ;

    protected Img(Parcel in) {
        this.h = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.w = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.position = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Img() {
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("h", h).append("w", w).append("name", name).append("position", position).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(h);
        dest.writeValue(w);
        dest.writeValue(name);
        dest.writeValue(position);
    }

    public int describeContents() {
        return 0;
    }

}
