package com.example.Swiggato.Repository;

import com.example.Swiggato.Model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

//    @Query(value = "select * from delivery_partner AS d JOIN order_entity AS o ON d.order = o.id GROUP BY d.id")
//    DeliveryPartner deliveryPartnerWithMaxDeliveries();
}
