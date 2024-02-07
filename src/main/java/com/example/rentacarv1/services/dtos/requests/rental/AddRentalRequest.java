package com.example.rentacarv1.services.dtos.requests.rental;

import com.example.rentacarv1.services.constants.rental.RentalMessages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = RentalMessages.START_DATE_CANNOT_BE_FURTHER_BACK_THAN_TODAY)
    private LocalDate startDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message =RentalMessages.END_DATE_CANNOT_BE_FURTHER_BACK_THAN_TODAY)
    private LocalDate endDate;

    @Min(value = 0,message = RentalMessages.DISCOUNT_CANNOT_BE_LOWER_ZERO)
    private Double discount;

    @Positive(message =RentalMessages.POSITIVE_NUMBER)
    @Max(value = 2,message = RentalMessages.ID_SHOULD_BE_BETWEEN_0_AND_2_CHARACTERS)
    private int carId;

    @Positive(message = RentalMessages.POSITIVE_NUMBER)
    @Max(value = 2,message =RentalMessages.ID_SHOULD_BE_BETWEEN_0_AND_2_CHARACTERS )
    private int customerId;

    @Positive(message = RentalMessages.POSITIVE_NUMBER)
    @Max(value = 999999,message =RentalMessages.EMPLOYEE_ID_AD_A_NUMBER)
    private int employeeId;
    @AssertTrue(message = RentalMessages.END_DATE_CANNOT_BEFORE_THAN_START_DATE)
    private boolean isStartDateBeforeEndDate(){
        return startDate.isBefore(endDate);
    }
    @AssertTrue(message =RentalMessages.THE_RENTAL_DURATION_CANNOT_EXCEED_25_DAYS)
    private boolean isRentalLimitOK(){
        int rentalLimit = startDate.until(endDate).getDays() + 1;
        if (rentalLimit > 25) {
           return false;
        }
        return true;
    }

}
