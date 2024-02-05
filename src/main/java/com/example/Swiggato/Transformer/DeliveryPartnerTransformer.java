package com.example.Swiggato.Transformer;

import com.example.Swiggato.DTOs.RequestDTO.DeliveryPartnerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.DeliveryPartnerResponse;
import com.example.Swiggato.Model.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerTransformer {

    public static DeliveryPartner deliveryPartnerRequestToDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        return DeliveryPartner.builder()
                .partnerName(deliveryPartnerRequest.getPartnerName())
                .age(deliveryPartnerRequest.getAge())
                .email(deliveryPartnerRequest.getEmail())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .order(new ArrayList<>())
                .build();
    }

    public static DeliveryPartnerResponse deliveryPartnerToDeliveryPartnerResponse(DeliveryPartner deliveryPartner) {
        return DeliveryPartnerResponse.builder()
                .partnerName(deliveryPartner.getPartnerName())
                .age(deliveryPartner.getAge())
                .email(deliveryPartner.getEmail())
                .mobileNo(deliveryPartner.getMobileNo())
                .build();
    }
}
