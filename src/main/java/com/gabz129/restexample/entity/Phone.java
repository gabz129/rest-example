package com.gabz129.restexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long phoneId;
    private String work;
    private String home;
    @NotNull
    private String mobile;

    private Phone(){}

    private Phone(Builder builder){
        requireNonNull(builder.mobile, "Mobile number is required");
        this.phoneId = builder.phoneId;
        this.work = builder.work;
        this.home = builder.home;
        this.mobile = builder.mobile;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public static class Builder {
        private Long phoneId;
        private String work;
        private String home;
        private String mobile;

        public Phone build(){
            return new Phone(this);
        }

        public Builder withId(Long phoneId){
            this.phoneId = phoneId;
            return this;
        }

        public Builder withWork(String work){
            this.work = work;
            return this;
        }
        public Builder withHome(String home){
            this.home = home;
            return this;
        }
        public Builder withMobile(String mobile){
            this.mobile = mobile;
            return this;
        }
    }
}
