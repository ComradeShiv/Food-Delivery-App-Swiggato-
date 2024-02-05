package com.example.Swiggato.Service.Impl;

import com.example.Swiggato.DTOs.RequestDTO.DeliveryPartnerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.DeliveryPartnerResponse;
import com.example.Swiggato.Model.DeliveryPartner;
import com.example.Swiggato.Repository.DeliveryPartnerRepository;
import com.example.Swiggato.Service.DeliveryPartnerService;
import com.example.Swiggato.Transformer.DeliveryPartnerTransformer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    final DeliveryPartnerRepository deliveryPartnerRepository;
    final JavaMailSender javaMailSender;
    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository, JavaMailSender javaMailSender) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.javaMailSender = javaMailSender;
    }


    @Override
    public DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        // dto -> entity
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.deliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);
        // save changes
        DeliveryPartner savedDeliveryPartner = deliveryPartnerRepository.save(deliveryPartner);
        // entity -> dto
        return DeliveryPartnerTransformer.deliveryPartnerToDeliveryPartnerResponse(savedDeliveryPartner);
    }

    @Override
    public DeliveryPartnerResponse deliveryPartnerWithMaxDeliveries() {
//        DeliveryPartner deliveryPartner = deliveryPartnerRepository.deliveryPartnerWithMaxDeliveries();
        // list of all DPs
        List<DeliveryPartner> deliveryPartnerList = deliveryPartnerRepository.findAll();
        // sorting reversely and extracting index 0th
        DeliveryPartner deliveryPartner = deliveryPartnerList.stream().sorted( (a,b) -> b.getOrder().size() - a.getOrder().size()).collect(Collectors.toList()).get(0);
        // entity -> dto
        return DeliveryPartnerTransformer.deliveryPartnerToDeliveryPartnerResponse(deliveryPartner);
    }

    @Override
    public List<DeliveryPartnerResponse> warnDeliveryPartners(int deliveries) throws MessagingException {

        List<DeliveryPartner> deliveryPartnerList = deliveryPartnerRepository.findAll();

        List<DeliveryPartner> filteredList = deliveryPartnerList.stream().filter(x -> x.getOrder().size() <= deliveries).collect(Collectors.toList());

        String text = "Hello whiskey," +
                    "\n" +
                    "\nThis is to inform you that your monthly target hasn't completed yet. Hence, we had put you on probation period for 1 month. If you achieve your targets in coming month then you'll be reinstated to your former glory else we had to let you go !!" +
                    "\n" +
                    "\nThanks & regards" +
                    "\nFood Delivery App" +
                    "\nNoida, UP";
        for (DeliveryPartner d: filteredList) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("fooddeliveryapp10@gmail.com");
            simpleMailMessage.setTo(d.getEmail());
            simpleMailMessage.setSubject("Warning regarding In-completion of given monthly target");
            simpleMailMessage.setText(text);

            javaMailSender.send(simpleMailMessage);
        }



//        for (DeliveryPartner d: filteredList) {
            //  Send email to those with less than given number of deliveries
//            MimeMessage message = javaMailSender.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom("fooddeliveryapp@gmail.com");
////            helper.setTo(d.getEmail());
//            helper.setTo("satyamsahu2107@gmail.com");
//            helper.setSubject("Warning regarding In-completion of given monthly target");
//            helper.setText("Hello Employee," +
//                    "" +
//                    "This is to inform you that your monthly target hasn't completed yet. Hence, we had put you on probation period for 1 month. If you achieve your targets in coming month then you'll be reinstated to your former glory else we had to let you go !!" +
//                    "" +
//                    "Thanks & regards" +
//                    "Food Delivery App" +
//                    "Noida, UP");

//            FileSystemResource file
//                    = new FileSystemResource(new File("https://images.unsplash.com/photo-1706452674137-5cf451fc58b3?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
//            helper.addAttachment("Invoice", file);

//            javaMailSender.send(message);
//        }

        return filteredList.stream().map(x -> DeliveryPartnerTransformer.deliveryPartnerToDeliveryPartnerResponse(x)).collect(Collectors.toList());
    }
}
