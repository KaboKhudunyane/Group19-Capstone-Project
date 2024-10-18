package za.ac.cput.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class Address {

    private String streetName;
    private String suburb;
    private String city;
    private String province;
    private String zipCode;

    protected Address() {
    }

    private Address(Builder builder){
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.zipCode = builder.zipCode;

    }



    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getStreetName(), address.getStreetName()) && Objects.equals(getSuburb(), address.getSuburb()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getProvince(), address.getProvince()) && Objects.equals(getZipCode(), address.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetName(), getSuburb(), getCity(), getProvince(), getZipCode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public static class Builder {
        private String streetName;
        private String suburb;
        private String city;
        private String province;
        private String zipCode;



        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }


        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder copy(Address address){
            this.streetName = address.streetName;
            this.suburb = address.suburb;
            this.city = address.city;
            this.province = address.province;
            this.zipCode = address.zipCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

    }
}
