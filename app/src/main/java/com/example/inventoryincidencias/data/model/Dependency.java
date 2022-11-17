package com.example.inventoryincidencias.data.model;

import java.util.Objects;

public class Dependency implements Comparable<Dependency> {
    private String name;
    private String shortName;
    private String description;
    private String imageName;

    public Dependency(String name, String shortName, String description, String imageName) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.imageName = imageName;
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

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        return getShortName() != null ? getShortName().equals(that.getShortName()) : that.getShortName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getShortName() != null ? getShortName().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Dependency dependency) {
        return this.getName().compareTo(dependency.getName());
    }
}
