package com.gabz129.restexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long addressId;
    @NotNull
    private String street;
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private Integer zip;
    private Float latitude;
    private Float longitude;

    private Address(){}

    private Address(Builder builder){
        requireNonNull(builder.street, "Street is required");
        requireNonNull(builder.state, "State is required");
        requireNonNull(builder.country, "Country is required");
        requireNonNull(builder.zip, "Zip code is required");
        this.addressId = builder.addressId;
        this.street = builder.street;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
        this.zip = builder.zip;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public static class Builder{
        private Long addressId;
        private String street;
        private String city;
        private String state;
        private String country;
        private Integer zip;
        private Float latitude;
        private Float longitude;

        public Address build(){
            return new Address(this);
        }

        public Builder withAddressId(Long addressId){
            this.addressId = addressId;
            return this;
        }

        public Builder withStreet(String street){
            this.street = street;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Builder withState(String state){
            this.state = state;
            return this;
        }

        public Builder withCountry(String country){
            this.country = country;
            return this;
        }

        public Builder withZip(Integer zip){
            this.zip = zip;
            return this;
        }

        public Builder withLatitude(Float latitude){
            this.latitude = latitude;
            return this;
        }

        public Builder withLongitude(Float longitude){
            this.longitude = longitude;
            return this;
        }
    }
}
