package com.example.rentacarv1.services.constants.user;

public class UserMessages {
    public static final String USER_NOT_NULL = "The field cannot be null";
    public static final String ID_NOT_FOUND = "User id was not found";
    public static final String USER_NOT_BLANK = "The field cannot be empty!";
    public static final String USER_NOT_EXIST="This user is not exist";
    public static final String POSITIVE_NUMBER = "The field must be a positive number.";
    public static final String NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS = "The field should be between 2 and 20 characters.";
    public static final String PHONE_NUMBER_FORMAT="Invalid phone number format. It must be in the format 05xxxxxxxxx.";
    public static final String EMAIL_FORMAT ="Invalid email address format";
    public static final String PASSWORD_FORMAT=  "At least 8 characters\n" +
            "\n" +
            "Contains at least one digit\n" +
            "\n" +
            "Contains at least one lowercase and one uppercase letter\n" +
            "\n" +
            "Contains at least one special character from the set (@#%$^.*etc.)\n" +
            "\n" +
            "Does not contain spaces, tabs, etc.";
    public static final String EMAIL_ALREADY_EXISTS="This email address is already in use.";
    public static final String PHONE_ALREADY_EXISTS="This phone number is already in use.";

}
