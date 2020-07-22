package com.springJpa.SpringJPA.Repositories;

import com.springJpa.SpringJPA.Entities.HotelServicesOffered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Repository
public interface ServicesRepositories extends JpaRepository<HotelServicesOffered,Long> {

    List<HotelServicesOffered> findByActionStatus(String approved);
    Optional<HotelServicesOffered> findHotelServicesOfferedByServiceCodeStartingWithAndActionStatus(String serviceCode,String action);
}
