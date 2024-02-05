package com.example.Swiggato.Controller;

import com.example.Swiggato.DTOs.RequestDTO.DeliveryPartnerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.DeliveryPartnerResponse;
import com.example.Swiggato.Service.DeliveryPartnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {

    final DeliveryPartnerService deliveryPartnerService;
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    // add delivery partner
    @PostMapping("/addDeliveryPartner")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {
        try{
            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
            return new ResponseEntity<>(deliveryPartnerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give delivery partner with max number of deliveries
    @GetMapping("/maxDeliveries")
    public ResponseEntity deliveryPartnerWithMaxDeliveries() {
        try {
            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.deliveryPartnerWithMaxDeliveries();
            return new ResponseEntity<>(deliveryPartnerResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // send email to all the partners who have done less than given number of deliveries.
    @PutMapping("/warnDeliveryPartners")
    public ResponseEntity warnDeliveryPartners(@RequestParam("min") int deliveries) {
        try {
            List<DeliveryPartnerResponse> deliveryPartnerResponseList = deliveryPartnerService.warnDeliveryPartners(deliveries);
            return new ResponseEntity<>(deliveryPartnerResponseList + "  " + "Email: Sent", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
