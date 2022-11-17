package com.example.inventoryincidencias.data.model;

public class User implements Comparable<Dependency>{
    private String email;
    private String password;
    private String name;
    private String surname;
    private int image;

    public User(String email, String password, String name, String surname, int image) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null;
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    @Override
    public int compareTo(Dependency dependency) {
        return 0;
    }
}
