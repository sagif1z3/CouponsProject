package com.example.sagicoupon.dto;

import com.example.sagicoupon.enums.UserType;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "userType", nullable = false)
    private UserType userType;

    @ManyToOne
    private CompanyDto companyDto;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "userDto")
    private List<PurchaseDto> purchaseDto;
}
