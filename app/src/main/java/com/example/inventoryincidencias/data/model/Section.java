package com.example.inventoryincidencias.data.model;

public class Section implements Comparable<Section> {
    private String name;
    private String shortName;
    private Dependency dependency;

    public Section(String nombre, String nombreCorto, Dependency dependency) {
        this.name = nombre;
        this.shortName = nombreCorto;
        this.dependency = dependency;
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

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;

        Section section = (Section) o;

        if (!getName().equals(section.getName())) return false;
        if (!getShortName().equals(section.getShortName())) return false;
        return getDependency().equals(section.getDependency());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getShortName().hashCode();
        result = 31 * result + getDependency().hashCode();
        return result;
    }

    @Override
    public int compareTo(Section section) {
        return this.getName().compareTo(section.getName());
    }
}
