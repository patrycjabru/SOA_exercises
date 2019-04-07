package pl.agh.kis.soa.ejb3.server.impl;

public class User {
    private String name;
    private String password;
    private double credits;

    public User() {

    }

    public User(String name, String password, double credits) {
        this.name = name;
        this.password = password;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
