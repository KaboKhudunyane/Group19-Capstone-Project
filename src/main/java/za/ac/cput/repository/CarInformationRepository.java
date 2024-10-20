package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.User;

import java.util.List;

@Repository
public interface CarInformationRepository extends JpaRepository<CarInformation, Long> {


    List<CarInformation> findCarsByUserUserID(Long userID);

    @Query("SELECT COUNT(c) FROM CarInformation c")
    long countCars();

    @Query("SELECT c FROM CarInformation c WHERE c.user.address.city = ?1")
    List<CarInformation> findCarsByCity(String city);


    @Query("SELECT c FROM CarInformation c WHERE c.user.address.province = ?1")
    List<CarInformation> findCarsByProvince(String province);

    @Query("SELECT c FROM CarInformation c WHERE c.user.address.city = ?1 AND c.user.address.suburb = ?2")
    List<CarInformation> findCarsByCityAndSuburb(String city, String suburb);

    @Query("SELECT c FROM CarInformation c WHERE c.user.address.city = ?1 OR c.user.address.province = ?2 OR c.user.address.zipCode = ?3")
    List<CarInformation> findCarsByLocation(String city, String province, String zipCode);



}
