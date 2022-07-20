package com.example.sagicoupon.enums;

public enum ErrorType {
    GENERAL_ERROR(601, "General error", true),
    USER_DOES_NOT_EXIST(602, "User does not exist", false),
    USER_ALREADY_EXIST(603, "User already exist", false),
    USER_USERNAME_IS_TOO_SHORT(604, "User username is too short", false),
    USER_FIRST_NAME_IS_INVALID(605, "User name is too invalid", false),
    USER_LAST_NAME_IS_INVALID(606, "User last name is invalid", false),
    USER_USERNAME_IS_INVALID(607, "User username is invalid", false),
    USER_PASSWORD_IS_INVALID(608, "User password is invalid", false),
    USER_DOES_NOT_HAVE_COMPANY_ID(609, "User does not have company id", false),
    USER_MUST_INSERT_COMPANY_ID(610, "User must insert company id", false),
    USER_FAILED_TO_LOG_IN(611, "User failed to log in, username or password is not correct", false),
    USER_LIST_IS_EMPTY(612, "User list is empty", false),
    CUSTOMER_DOES_NOT_EXIST(613, "Customer does not exist", false),
    CUSTOMER_ALREADY_EXIST(614, "Customer already exist", false),
    CUSTOMER_USERNAME_IS_TOO_SHORT(615, "Customer username is to short", false),
    CUSTOMER_USERNAME_IS_INVALID(616, "Customer username is invalid", false),
    CUSTOMER_PASSWORD_IS_INVALID(617, "Customer password is invalid", false),
    CUSTOMER_DOES_NOT_HAVE_COMPANY_ID(618, "Customer does not have company id", false),
    CUSTOMER_MUST_INSERT_COMPANY_ID(619, "Customer must insert company id", false),
    CUSTOMER_LIST_IS_EMPTY(620, "Customer list is empty", false),
    CUSTOMER_FIRST_NAME_IS_INVALID(621, "Customer first name is invalid", false),
    CUSTOMER_LAST_NAME_IS_INVALID(622, "Customer last name is invalid", false),
    CUSTOMER_IS_UNDER_EIGHTEEN_YEARS_OLD(623, "Customer age is under 18 years old", false),
    CUSTOMER_ADDRESS_IS_INVALID(624, "Customer address is invalid", false),
    CUSTOMER_AGE_IS_LESS_THAN_18(625, "Customer age is less than 18", false),
    COMPANY_DOES_NOT_EXIST(626, "Company does not exist", false),
    COMPANY_ALREADY_EXIST(627, "Company already exist", false),
    COMPANY_LIST_IS_EMPTY(628, "Company list is empty", false),
    COMPANY_NAME_IS_INVALID(629, "Company name is invalid", false),
    COMPANY_ADDRESS_IS_INVALID(630, "Company address is invalid", false),
    COMPANY_PHONE_NUMBER_IS_INVALID(631, "Company phone number is invalid", false),
    COUPON_DOES_NOT_EXIST(630, "Coupon does not exist", false),
    COUPON_AMOUNT_IS_LESS_THAN_ZERO(632, "Coupon amount is less than zero", false),
    COUPON_IS_OUT_OF_DATE(633, "Coupon is out of date", false),
    COUPON_ALREADY_EXIST(634, "Coupon already exist", false),
    COUPON_LIST_IS_EMPTY(635, "Coupon list is empty", false),
    COUPON_HAS_EXPIRED(636, "Coupon has expired", false),
    COUPON_TITLE_IS_INVALID(637, "Coupon title is invalid", false),
    COUPON_DESCRIPTION_IS_INVALID(638, "Coupon description is invalid", false),
    COUPON_START_DATE_IS_INVALID(639, "Coupon start date cannot be before the current date and 100 days after the current date ", false),
    COUPON_AMOUNT_IS_LESS_THAN_1(640, "Coupon amount is less than 1", false),
    COUPON_PRICE_IS_0(641, "Coupon price is less than 0.1", false),
    COUPON_IMAGE_URL_IS_INVALID(642, "Coupon image url is invalid", false),
    PURCHASE_DOES_NOT_EXIST(643, "Purchase does not exist", false),
    PURCHASE_LIST_IS_EMPTY(644, "Purchase list is empty", false),
    PURCHASE_ALREADY_EXIST(645, "Purchase already exist", false),
    PURCHASE_AMOUNT_IS_LESS_THAN_1(646, "Purchase amount is less than 1", false),
    SQL_ERROR(647, "Something went wrong when trying to execute sql query", true);

    private int errorNumber;
    private String errorMessage;
    private boolean isShowStackTrace;

    ErrorType(int errorNumber, String errorMessage, boolean isShowStackTrace) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    ErrorType(int errorNumber, String errorMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public String getterOfMessage(){
        return errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }
}
