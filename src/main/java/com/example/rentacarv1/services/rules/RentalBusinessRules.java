package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.BusinessException;
import com.example.rentacarv1.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private RentalRepository rentalRepository;
    private static final int MAX_RENTAL_DAYS = 25;

    //Araç kiralarken verilen bitiş tarihi başlangıç tarihinden daha geçmiş bi tarih olamaz.
    public void isEndDateAfterStartDate(LocalDate endDate, LocalDate startDate) {
        if (endDate.isBefore(startDate)) {
            throw new BusinessException("End date can not before than start date");
        }
        long rentalLimit = startDate.until(endDate).getDays() + 1;
        if (rentalLimit > MAX_RENTAL_DAYS) {
            throw new IllegalArgumentException("Araç maksimum 25 gün kiralanabilir.");
        }

    }
}
