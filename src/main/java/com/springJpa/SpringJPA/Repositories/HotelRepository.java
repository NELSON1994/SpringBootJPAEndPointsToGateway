package com.springJpa.SpringJPA.Repositories;

import com.springJpa.SpringJPA.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
List<Hotel> findAllByActionStatusAndAction(String actionStatus,String action);

    List<Hotel> findByActionStatus(String approved);
}
