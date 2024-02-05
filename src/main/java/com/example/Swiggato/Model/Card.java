package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "account_number")
    @Size(min = 12, max = 12, message = "{validation.name.size.has_to_be_12_Characters}")
    String accountNo;

    @Column(name = "name", nullable = false)
    String name;

    Gender gender;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
