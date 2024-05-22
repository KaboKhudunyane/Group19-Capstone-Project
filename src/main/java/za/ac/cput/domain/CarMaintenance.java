package za.ac.cput.domain;

import java.io.Serializable;
import java.util.Objects;

public class CarMaintenance implements Serializable {
    private String maintenanceID;
    private String carID;
    private String maintenanceDate;
    private String maintenanceDetails;
    private String cost;

    protected CarMaintenance() {}

    private CarMaintenance(Builder builder) {
        this.maintenanceID = builder.maintenanceID;
        this.carID = builder.carID;
        this.maintenanceDate = builder.maintenanceDate;
        this.maintenanceDetails = builder.maintenanceDetails;
        this.cost = builder.cost;
    }

    public String getMaintenanceID() {
        return maintenanceID;
    }

    public String getCarID() {
        return carID;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public String getMaintenanceDetails() {
        return maintenanceDetails;
    }

    public String getCost() {
        return cost;
    }

    public void setMaintenanceID(String maintenanceID) {
        this.maintenanceID = maintenanceID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public void setMaintenanceDetails(String maintenanceDetails) {
        this.maintenanceDetails = maintenanceDetails;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object maintenanceObject) {
        if (this == maintenanceObject) return true;
        if (maintenanceObject == null || getClass() != maintenanceObject.getClass()) return false;
        CarMaintenance maintenance = (CarMaintenance) maintenanceObject;
        return Objects.equals(maintenanceID, maintenance.maintenanceID) &&
                Objects.equals(carID, maintenance.carID) &&
                Objects.equals(maintenanceDate, maintenance.maintenanceDate) &&
                Objects.equals(maintenanceDetails, maintenance.maintenanceDetails) &&
                Objects.equals(cost, maintenance.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maintenanceID, carID, maintenanceDate, maintenanceDetails, cost);
    }

    @Override
    public String toString() {
        return "CarMaintenance{" +
                "maintenanceID='" + maintenanceID + '\'' +
                ", carID='" + carID + '\'' +
                ", maintenanceDate='" + maintenanceDate + '\'' +
                ", maintenanceDetails='" + maintenanceDetails + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

    public static class Builder {
        private String maintenanceID;
        private String carID;
        private String maintenanceDate;
        private String maintenanceDetails;
        private String cost;

        public Builder setMaintenanceID(String maintenanceID) {
            this.maintenanceID = maintenanceID;
            return this;
        }

        public Builder setCarID(String carID) {
            this.carID = carID;
            return this;
        }

        public Builder setMaintenanceDate(String maintenanceDate) {
            this.maintenanceDate = maintenanceDate;
            return this;
        }

        public Builder setMaintenanceDetails(String maintenanceDetails) {
            this.maintenanceDetails = maintenanceDetails;
            return this;
        }

        public Builder setCost(String cost) {
            this.cost = cost;
            return this;
        }

        public Builder copyCarMaintenance(CarMaintenance maintenance) {
            this.maintenanceID = maintenance.maintenanceID;
            this.carID = maintenance.carID;
            this.maintenanceDate = maintenance.maintenanceDate;
            this.maintenanceDetails = maintenance.maintenanceDetails;
            this.cost = maintenance.cost;
            return this;
        }

        public CarMaintenance buildCarMaintenance() {
            return new CarMaintenance(this);
        }
    }
}
