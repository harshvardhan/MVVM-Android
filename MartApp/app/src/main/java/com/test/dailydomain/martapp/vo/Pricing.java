package com.test.dailydomain.martapp.vo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Pricing implements Parcelable
{

    @SerializedName("on_sale")
    @Expose
    public Integer onSale;
    @SerializedName("price")
    @Expose
    public Double price;
    @SerializedName("promo_price")
    @Expose
    public Double promoPrice;
    @SerializedName("savings")
    @Expose
    public Double savings;
    @SerializedName("savings_type")
    @Expose
    public Integer savingsType;
    public final static Parcelable.Creator<Pricing> CREATOR = new Creator<Pricing>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Pricing createFromParcel(Parcel in) {
            return new Pricing(in);
        }

        public Pricing[] newArray(int size) {
            return (new Pricing[size]);
        }

    }
            ;

    protected Pricing(Parcel in) {
        this.onSale = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.price = ((Double) in.readValue((Double.class.getClassLoader())));
        this.promoPrice = ((Double) in.readValue((Double.class.getClassLoader())));
        this.savings = ((Double) in.readValue((Double.class.getClassLoader())));
        this.savingsType = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Pricing() {
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public Integer getSavingsType() {
        return savingsType;
    }

    public void setSavingsType(Integer savingsType) {
        this.savingsType = savingsType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("onSale", onSale).append("price", price).append("promoPrice", promoPrice).append("savings", savings).append("savingsType", savingsType).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(onSale);
        dest.writeValue(price);
        dest.writeValue(promoPrice);
        dest.writeValue(savings);
        dest.writeValue(savingsType);
    }

    public int describeContents() {
        return 0;
    }

}
