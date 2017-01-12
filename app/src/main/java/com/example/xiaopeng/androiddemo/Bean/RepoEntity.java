package com.example.xiaopeng.androiddemo.Bean;

/**
 * Created by xiaopeng on 16/6/28.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepoEntity implements Parcelable{

    private Integer id;

    private String name;

    private String fullName;

    public RepoEntity() {

    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(fullName);
    }

    public RepoEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        fullName = in.readString();
    }

    public static final Parcelable.Creator<RepoEntity> CREATOR = new Parcelable.Creator<RepoEntity>() {
        public RepoEntity createFromParcel(Parcel in) {
            return new RepoEntity(in);
        }

        public RepoEntity[] newArray(int size) {
            return new RepoEntity[size];
        }
    };
}
