package com.example.sagicoupon.validators;

import com.example.sagicoupon.dto.CustomerDto;
import com.example.sagicoupon.dto.UserDto;
import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.enums.UserType;
import com.example.sagicoupon.exceptions.ServerException;
import com.example.sagicoupon.model.Customer;
import com.example.sagicoupon.model.User;
import com.example.sagicoupon.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerValidators {

    private CustomerRepository customerRepository;
    private static final Date currentDate = new Date(System.currentTimeMillis());

    public boolean addCustomerValidation(Customer customer) {

        customerRepository.findByUsername(customer.getUser().getUsername())
                .ifPresent(username -> {
                    throw new ServerException(ErrorType.CUSTOMER_ALREADY_EXIST);
                });

        Optional.of(customer)
                .map(Customer::getUser)
                .map(User::getFirstName)
                .filter(Regex::nameValidation)
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_FIRST_NAME_IS_INVALID));

        Optional.of(customer)
                .map(Customer::getUser)
                .map(User::getLastName)
                .filter(Regex::nameValidation)
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_LAST_NAME_IS_INVALID));

        Optional.of(customer)
                .map(Customer::getUser)
                .map(User::getUsername)
                .filter(Regex::emailValidation)
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_USERNAME_IS_INVALID));

        Optional.of(customer)
                .map(Customer::getUser)
                .map(User::getPassword)
                .filter(Regex::passwordValidation)
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_PASSWORD_IS_INVALID));

//        Optional.of(customer)
//                .map(Customer::getUserDto)
//                .map(UserDto::getUserType)
//                .filter(userType -> UserType.CUSTOMER.equals(customer.getUserDto().getUserType())
//                        && customer.getUserDto().getCompanyDto() == null)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_DOES_NOT_HAVE_COMPANY_ID,
//                        String.valueOf(customer.getUserDto().getUserType())));

        Optional.of(customer)
                .map(Customer::getUser)
                .map(User::getUserType)
                .map(userType -> UserType.CUSTOMER.equals(customer.getUser().getUserType())
                        && customer.getUser().getCompanyId() == null)
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_MUST_INSERT_COMPANY_ID,
                                String.valueOf(customer.getUser().getUserType())));

        Optional.of(customer)
                .map(Customer::getAddress)
                .filter(Regex::addressValidation)
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_ADDRESS_IS_INVALID));

        Optional.of(customer)
                .map(Customer::getBirthDate)
                .filter(date -> date.isBefore(currentDate.toLocalDate().minusYears(18)))
//                .filter(date -> customer.getBirthDate().toLocalDate().isAfter(currentDate.toLocalDate().minusYears(18)))
                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_AGE_IS_LESS_THAN_18));

        return true;
    }

    public boolean updateCustomerValidation(Customer customer) {
//        Optional<CustomerDto> find = customerRepository.findById(customer.getId());
//        find.ifPresentOrElse(
//                (CustomerDto) -> {
//                    System.out.println(
//                            "Found customer: " + CustomerDto);
//                },
//                () -> {
//                    throw new ServerException(ErrorType.CUSTOMER_DOES_NOT_EXIST);
//                });
//
//        Optional.of(customer)
//                .map(Customer::getUserDto)
//                .map(UserDto::getFirstName)
//                .filter(Regex::nameValidation)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_FIRST_NAME_IS_INVALID));
//
//        Optional.of(customer)
//                .map(Customer::getUserDto)
//                .map(UserDto::getLastName)
//                .filter(Regex::nameValidation)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_LAST_NAME_IS_INVALID));
//
//        Optional.of(customer)
//                .map(Customer::getUserDto)
//                .map(UserDto::getUsername)
//                .filter(Regex::emailValidation)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_USERNAME_IS_INVALID));
//
//        Optional.of(customer)
//                .map(Customer::getUserDto)
//                .map(UserDto::getPassword)
//                .filter(Regex::passwordValidation)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_PASSWORD_IS_INVALID));
//
////        Optional.of(customer)
////                .map(Customer::getUserDto)
////                .map(UserDto::getUserType)
////                .filter(userType -> UserType.CUSTOMER.equals(customer.getUserDto().getUserType())
////                        && customer.getUserDto().getCompanyDto() == null)
////                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_DOES_NOT_HAVE_COMPANY_ID,
////                        String.valueOf(customer.getUserDto().getUserType())));
//
//        Optional.of(customer)
//                .map(Customer::getUserDto)
//                .map(UserDto::getUserType)
//                .map(userType -> UserType.CUSTOMER.equals(customer.getUserDto().getUserType())
//                        && customer.getUserDto().getCompanyDto() == null)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_MUST_INSERT_COMPANY_ID,
//                        String.valueOf(customer.getUserDto().getUserType())));
//
//        Optional.of(customer)
//                .map(Customer::getAddress)
//                .filter(Regex::addressValidation)
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_ADDRESS_IS_INVALID));
//
//        Optional.of(customer)
//                .map(Customer::getBirthDate)
//                .filter(date -> date.isBefore(currentDate.toLocalDate().minusYears(18)))
////                .filter(date -> customer.getBirthDate().toLocalDate().isAfter(currentDate.toLocalDate().minusYears(18)))
//                .orElseThrow(() -> new ServerException(ErrorType.CUSTOMER_AGE_IS_LESS_THAN_18));

        return true;
    }
}
