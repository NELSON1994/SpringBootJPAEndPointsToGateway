package com.springJpa.SpringJPA.Repositories;

import com.springJpa.SpringJPA.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Repository
public interface CustomersRepository extends JpaRepository<Customer,Long> {

}
