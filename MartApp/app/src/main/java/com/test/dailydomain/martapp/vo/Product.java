package com.test.dailydomain.martapp.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.dailydomain.martapp.db.MartAppTypeConverters;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(primaryKeys = "id")
@TypeConverters(MartAppTypeConverters.class)
public class Product implements Parcelable
{
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("img")
    @Expose
    public Img img;
    @SerializedName("pricing")
    @Expose
    public Pricing pricing;
    public final static Parcelable.Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    };

    protected Product(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.desc = ((String) in.readValue((String.class.getClassLoader())));
        this.img = ((Img) in.readValue((Img.class.getClassLoader())));
        this.pricing = ((Pricing) in.readValue((Pricing.class.getClassLoader())));
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("title", title).append("desc", desc).append("img", img).append("pricing", pricing).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(desc);
        dest.writeValue(img);
        dest.writeValue(pricing);
    }

    public int describeContents() {
        return 0;
    }

}