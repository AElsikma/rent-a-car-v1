package com.example.rentacarv1.services.constants.rental;

public class RentalMessages {
    public static final String RENTAL_NOT_NULL = "The field cannot be null";
    public static final String ID_NOT_FOUND = "Rental id was not found";

    public static final String POSITIVE_NUMBER = "The field must be a positive number.";
    public static final String START_DATE_CANNOT_BE_FURTHER_BACK_THAN_TODAY="The starting date cannot be further back than today.";
    public static final String END_DATE_CANNOT_BE_FURTHER_BACK_THAN_TODAY= "The end date cannot be further back than today.";
    public static final String RETURN_DATE_CANNOT_BE_FURTHER_BACK_THAN_TODAY="The end date cannot be further back than today.";
    public static final String KILOMETER_RULE ="Kilometer must be greater than or equal to 0.";
    public static final String DISCOUNT_CANNOT_BE_LOWER_ZERO= "The discount value can not be lower than 0";

    public static final String END_DATE_CANNOT_BEFORE_THAN_START_DATE= "End date can not before than start date";
    public static final String THE_RENTAL_DURATION_CANNOT_EXCEED_25_DAYS = "The rental duration cannot exceed 25 days.";

}
