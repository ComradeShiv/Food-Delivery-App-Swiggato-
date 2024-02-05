package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "transaction_id")
    String transactionId; // UUID

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    Date transactionTime;

    @Column(name = "total_amount")
    double totalAmount;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToOne
    @JoinColumn
    OrderEntity order;
}
