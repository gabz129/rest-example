package com.gabz129.restexample.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static java.util.Objects.requireNonNull;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long contactId;
    @NotNull
    @Column(unique = true)
    private String name;
    private String company;
    private Boolean favorite;
    private String smallImageURL;
    private String largeImageURL;
    @NotNull
    @Column(unique = true)
    @Email(message = "Please provide a valid email")
    private String email;
    private String website;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date birthdate;
    @NotNull
    @OneToOne(cascade=CascadeType.ALL)
    private Phone phone;
    @NotNull
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    private Contact(){}

    private Contact(Builder builder){
        requireNonNull(builder.name, "Name is required");
        requireNonNull(builder.email, "Email is required");
        requireNonNull(builder.birthdate, "Birthdate is required");
        requireNonNull(builder.phone, "Phone is required");
        requireNonNull(builder.address, "Address is required");
        this.contactId = builder.contactId;
        this.name = builder.name;
        this.company = builder.company;
        this.favorite = builder.favorite;
        this.smallImageURL = builder.smallImageURL;
        this.largeImageURL = builder.largeImageURL;
        this.email = builder.email;
        this.website = builder.website;
        this.birthdate = builder.birthdate;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public static class Builder{
        private Long contactId;
        private String name;
        private String company;
        private Boolean favorite;
        private String smallImageURL;
        private String largeImageURL;
        private String email;
        private String website;
        private Date birthdate;
        private Phone phone;
        private Address address;

        public Contact build(){
            return new Contact(this);
        }

        public Builder withContactId(Long contactId){
            this.contactId = contactId;
            return this;
        }
        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withCompany(String company){
            this.company = company;
            return this;
        }
        public Builder withFavorite(Boolean favorite){
            this.favorite = favorite;
            return this;
        }
        public Builder withSmallImageURL(String smallImageURL){
            this.smallImageURL = smallImageURL;
            return this;
        }
        public Builder withLargeImageURL(String largeImageURL){
            this.largeImageURL = largeImageURL;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }
        public Builder withWebsite(String website){
            this.website = website;
            return this;
        }
        public Builder withBirthdate(Date birthdate){
            this.birthdate = birthdate;
            return this;
        }
        public Builder withPhone(Phone phone){
            this.phone = phone;
            return this;
        }
        public Builder withAddress(Address address){
            this.address = address;
            return this;
        }
    }
}
