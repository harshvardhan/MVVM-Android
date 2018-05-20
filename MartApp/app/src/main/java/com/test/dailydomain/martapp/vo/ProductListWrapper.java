package com.test.dailydomain.martapp.vo;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductListWrapper implements Parcelable
{

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    public final static Parcelable.Creator<ProductListWrapper> CREATOR = new Creator<ProductListWrapper>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductListWrapper createFromParcel(Parcel in) {
            return new ProductListWrapper(in);
        }

        public ProductListWrapper[] newArray(int size) {
            return (new ProductListWrapper[size]);
        }

    }
            ;

    protected ProductListWrapper(Parcel in) {
        in.readList(this.products, (Product.class.getClassLoader()));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pageSize = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ProductListWrapper() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("products", products).append("total", total).append("page", page).append("pageSize", pageSize).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(products);
        dest.writeValue(total);
        dest.writeValue(page);
        dest.writeValue(pageSize);
    }

    public int describeContents() {
        return 0;
    }

}
