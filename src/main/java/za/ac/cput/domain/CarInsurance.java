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

    @ManyToOne
    private User user;

    @OneToOne
    private CarInformation carInformation;


    protected CarInsurance() {

    }


    private CarInsurance(Builder builder) {
        this.carInsuranceID = builder.carInsuranceID;
        this.insuranceCompany = builder.insuranceCompany;
        this.policyNumber = builder.policyNumber;
        this.coverageType = builder.coverageType;
        this.coverageAmount = builder.coverageAmount;
        this.user = builder.user;
        this.carInformation = builder.carInformation;
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


    public User getUser() {
        return user;
    }

    public CarInformation getCarInformation() {
        return carInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsurance that = (CarInsurance) o;
        return getPolicyNumber() == that.getPolicyNumber() && Double.compare(getCoverageAmount(), that.getCoverageAmount()) == 0 && Objects.equals(getCarInsuranceID(), that.getCarInsuranceID()) && Objects.equals(getInsuranceCompany(), that.getInsuranceCompany()) && Objects.equals(getCoverageType(), that.getCoverageType()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getCarInformation(), that.getCarInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarInsuranceID(), getInsuranceCompany(), getPolicyNumber(), getCoverageType(), getCoverageAmount(), getUser(), getCarInformation());
    }

    @Override
    public String toString() {
        return "CarInsurance{" +
                "carInsuranceID=" + carInsuranceID +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", policyNumber=" + policyNumber +
                ", coverageType='" + coverageType + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", user=" + user +
                ", carInformation=" + carInformation +
                '}';
    }

    public static class Builder {
        private Long carInsuranceID;
        private String insuranceCompany;
        private int policyNumber;
        private String coverageType;
        private double coverageAmount;
        private User user;
        private CarInformation carInformation;

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

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCarInformation(CarInformation carInformation) {
            this.carInformation = carInformation;
            return this;
        }

        public Builder copyCarInsurance(CarInsurance carInsurance) {
            this.carInsuranceID = carInsurance.carInsuranceID;
            this.insuranceCompany = carInsurance.insuranceCompany;
            this.policyNumber = carInsurance.policyNumber;
            this.coverageType = carInsurance.coverageType;
            this.coverageAmount = carInsurance.coverageAmount;
            this.user = carInsurance.user;
            this.carInformation = carInsurance.carInformation;
            return this;
        }
        public CarInsurance buildCarInsurance() {
            return new CarInsurance(this);
        }
    }
}