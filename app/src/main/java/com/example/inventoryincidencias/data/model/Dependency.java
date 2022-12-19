package com.example.inventoryincidencias.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class Dependency implements Comparable<Dependency>, Serializable, Parcelable {
    public static final String TAG = "dependency";
    private int id;
    private String name;
    private String shortName;
    private String description;
    private String imageName;

    public Dependency() {
    }

    public Dependency(int id, String name, String shortName, String description, String imageName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.imageName = imageName;
    }

    public Dependency(String name, String shortName, String description, String imageName) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.imageName = imageName;
    }

    protected Dependency(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        imageName = in.readString();
    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependency)) return false;

        Dependency that = (Dependency) o;

        return getShortName().equals(that.getShortName());
    }

    @Override
    public int hashCode() {
        return getShortName().hashCode();
    }

    @Override
    public int compareTo(Dependency dependency) {
        return this.getName().compareTo(dependency.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(shortName);
        parcel.writeString(description);
        parcel.writeString(imageName);
    }

    public Dependency clone() {
        Dependency d = new Dependency(this.id, this.name, this.shortName, this.description, this.imageName);
        return d;
    }
}
