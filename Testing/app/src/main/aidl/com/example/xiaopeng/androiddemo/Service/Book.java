package com.example.xiaopeng.androiddemo.Service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaopeng on 13/01/2017.
 */

public class Book implements Parcelable {
    String name;
    int price;

    public  Book(){}

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = dest.readString();
        price = dest.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //方便打印数据
    @Override
    public String toString() {
        return "name : " + name + " , price : " + price;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
