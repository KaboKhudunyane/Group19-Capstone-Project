package za.ac.cput.factory;

import za.ac.cput.domain.CarMaintenance;
import za.ac.cput.util.Helper;

public class CarMaintenanceFactory {
    public static CarMaintenance createCarMaintenance(String maintenanceID, String carID, String maintenanceDate,
                                                      String maintenanceDetails, String cost) {

        if (Helper.isNullOrEmpty(maintenanceID) ||
                Helper.isNullOrEmpty(carID) ||
                Helper.isNullOrEmpty(maintenanceDate) ||
                Helper.isNullOrEmpty(maintenanceDetails) ||
                Helper.isNullOrEmpty(cost)) {
            return null;
        }

        return new CarMaintenance.Builder()
                .setMaintenanceID(maintenanceID)
                .setCarID(carID)
                .setMaintenanceDate(maintenanceDate)
                .setMaintenanceDetails(maintenanceDetails)
                .setCost(cost)
                .buildCarMaintenance();
    }
}

