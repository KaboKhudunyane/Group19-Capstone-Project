package za.ac.cput.domain;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class CarInsurance{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carInsuranceID;
    private String insuranceCompany;
    private int policyNumber;
    private String coverageType;
    private double coverageAmount;


    protected CarInsurance() {

    }


    private CarInsurance(Builder builder) {
        this.carInsuranceID = builder.carInsuranceID;
        this.insuranceCompany = builder.insuranceCompany;
        this.policyNumber = builder.policyNumber;
        this.coverageType = builder.coverageType;
        this.coverageAmount = builder.coverageAmount;
    }


    public Long getCarInsuranceID() {
        return carInsuranceID;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }
    public int getPolicyNumber() {
        return policyNumber;
    }
    public String getCoverageType() {
        return coverageType;
    }
    public double getCoverageAmount() {
        return coverageAmount;
    }


    public void setCarInsuranceID(Long carInsuranceID) {
        this.carInsuranceID = carInsuranceID;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsurance that = (CarInsurance) o;
        return policyNumber == that.policyNumber && Double.compare(coverageAmount, that.coverageAmount) == 0 && Objects.equals(carInsuranceID, that.carInsuranceID) && Objects.equals(insuranceCompany, that.insuranceCompany) && Objects.equals(coverageType, that.coverageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carInsuranceID, insuranceCompany, policyNumber, coverageType, coverageAmount);
    }

    @Override
    public String toString() {
        return "CarInsurance{" +
                "carInsuranceID=" + carInsuranceID +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", policyNumber=" + policyNumber +
                ", coverageType='" + coverageType + '\'' +
                ", coverageAmount=" + coverageAmount +
                '}';
    }

    public static class Builder {
        private Long carInsuranceID;
        private String insuranceCompany;
        private int policyNumber;
        private String coverageType;
        private double coverageAmount;

        public Builder setCarInsuranceID(Long carInsuranceID) {
            this.carInsuranceID = carInsuranceID;
            return this;
        }

        public Builder setInsuranceCompany(String insuranceCompany) {
            this.insuranceCompany = insuranceCompany;
            return this;
        }
        public Builder setPolicyNumber(int policyNumber) {
            this.policyNumber = policyNumber;
            return this;
        }
        public Builder setCoverageType(String coverageType) {
            this.coverageType = coverageType;
            return this;
        }
        public Builder setCoverageAmount(double coverageAmount) {
            this.coverageAmount = coverageAmount;
            return this;
        }
        public Builder copyCarInsurance(CarInsurance carInsurance) {
            this.carInsuranceID = carInsurance.carInsuranceID;
            this.insuranceCompany = carInsurance.insuranceCompany;
            this.policyNumber = carInsurance.policyNumber;
            this.coverageType = carInsurance.coverageType;
            this.coverageAmount = carInsurance.coverageAmount;
            return this;
        }
        public CarInsurance buildCarInsurance() {
            return new CarInsurance(this);
        }
    }
}