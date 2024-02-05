package com.example.Swiggato.Service;

import com.example.Swiggato.DTOs.RequestDTO.DeliveryPartnerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.DeliveryPartnerResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface DeliveryPartnerService {
    DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest);

    DeliveryPartnerResponse deliveryPartnerWithMaxDeliveries();

    List<DeliveryPartnerResponse> warnDeliveryPartners(int deliveries) throws MessagingException;
}
