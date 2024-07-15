package za.ac.cput.domain;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class CarInsurance implements Serializable {
    // Name of the insurance company providing coverage
    private String insuranceCompany;
    // Policy number associated with the car insurance
    private String policyNumber;
    // Type of coverage (e.g., comprehensive, liability)
    private String coverageType;
    // Amount of coverage provided by the insurance policy
    private String coverageAmount;
    protected CarInsurance() {
    }
    private CarInsurance(Builder builder) {
        this.insuranceCompany = builder.insuranceCompany;
        this.policyNumber = builder.policyNumber;
        this.coverageType = builder.coverageType;
        this.coverageAmount = builder.coverageAmount;
    }
    public String getInsuranceCompany() {
        return insuranceCompany;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public String getCoverageType() {
        return coverageType;
    }
    public String getCoverageAmount() {
        return coverageAmount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsurance that = (CarInsurance) o;
        return Objects.equals(insuranceCompany, that.insuranceCompany) &&
                Objects.equals(policyNumber, that.policyNumber) &&
                Objects.equals(coverageType, that.coverageType) &&
                Objects.equals(coverageAmount, that.coverageAmount);
    }
    @Override
    public int hashCode() {
        return Objects.hash(insuranceCompany, policyNumber, coverageType, coverageAmount);
    }
    @Override
    public String toString() {
        return "CarInsurance{" +
                "insuranceCompany='" + insuranceCompany + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", coverageType='" + coverageType + '\'' +
                ", coverageAmount='" + coverageAmount + '\'' +
                '}';
    }
    public static class Builder {
        private String insuranceCompany;
        private String policyNumber;
        private String coverageType;
        private String coverageAmount;
        public Builder setInsuranceCompany(String insuranceCompany) {
            this.insuranceCompany = insuranceCompany;
            return this;
        }
        public Builder setPolicyNumber(String policyNumber) {
            this.policyNumber = policyNumber;
            return this;
        }
        public Builder setCoverageType(String coverageType) {
            this.coverageType = coverageType;
            return this;
        }
        public Builder setCoverageAmount(String coverageAmount) {
            this.coverageAmount = coverageAmount;
            return this;
        }
        public Builder copyCarInsurance(CarInsurance carInsurance) {
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