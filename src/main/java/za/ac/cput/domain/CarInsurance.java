package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class CarInsurance {
    @Id
    private String insuranceID;
    private String insuranceName;
    private String userID;
    private String policyNumber;

    public CarInsurance() {}

    private  CarInsurance(Builder builder) {
        this.insuranceID = builder.insuranceID;
        this.insuranceName = builder.insuranceName;
        this.userID = builder.userID;
        this.policyNumber = builder.policyNumber;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public String getUserID() {
        return userID;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsurance that = (CarInsurance) o;
        return Objects.equals(insuranceID, that.insuranceID) && Objects.equals(insuranceName, that.insuranceName) && Objects.equals(userID, that.userID) && Objects.equals(policyNumber, that.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insuranceID, insuranceName, userID, policyNumber);
    }

    @Override
    public String toString() {
        return "CarInsurance{" +
                "insuranceID='" + insuranceID + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                ", userID='" + userID + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                '}';
    }

    public static class Builder{
        private String insuranceID;
        private String insuranceName;
        private String userID;
        private String policyNumber;


    public Builder setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
        return this;
    }

    public Builder setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
        return this;
    }

    public Builder setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public Builder setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
        return this;
    }

    public Builder copy (CarInsurance carInsurance){
        this.insuranceID = carInsurance.insuranceID;
        this.insuranceName = carInsurance.insuranceName;
        this.userID = carInsurance.userID;
        this.policyNumber = carInsurance.policyNumber;
        return this;
    }
    public CarInsurance build(){return new CarInsurance(this);}
 }

}