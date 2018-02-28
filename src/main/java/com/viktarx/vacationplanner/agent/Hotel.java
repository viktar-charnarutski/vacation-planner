package com.viktarx.vacationplanner.agent;

import java.net.URL;

/**
 * The class contains information about a hotel.
 */
public final class Hotel {

    private final String name;
    private final String address;
    private final String type;
    private final double stars;
    private final double rating;
    private final URL url;

    Hotel(String name, String address, String type, double stars, double rating, URL url) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.stars = stars;
        this.rating = rating;
        this.url = url;
    }

    public String name() {
        return name;
    }

    public String address() {
        return address;
    }

    public String type() {
        return type;
    }

    public double stars() {
        return stars;
    }

    public double rating() {
        return rating;
    }

    public URL url() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (Double.compare(hotel.stars, stars) != 0) return false;
        if (Double.compare(hotel.rating, rating) != 0) return false;
        if (!name.equals(hotel.name)) return false;
        if (!address.equals(hotel.address)) return false;
        if (!type.equals(hotel.type)) return false;
        return url.equals(hotel.url);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + type.hashCode();
        temp = Double.doubleToLongBits(stars);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", stars=" + stars +
                ", rating=" + rating +
                ", url=" + url +
                '}';
    }
}
