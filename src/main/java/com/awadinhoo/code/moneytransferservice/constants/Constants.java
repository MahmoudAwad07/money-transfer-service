package com.awadinhoo.code.moneytransferservice.constants;

public class Constants {

    public static class StatusMessages {
        public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account is not found for this id :: ";
        public static final String ACCOUNT_NUMBER_NOT_FOUND_MESSAGE = "Account is not found for this account number :: ";
        public static final String ACCOUNT_NOT_FOUND_FOR_ACCOUNT_NUMBER_MESSAGE = "Account is not found for this account number :: ";
        public static final String ACCOUNT_DELETED_SUCCESSFULLY_MESSAGE = "Account successfully deleted! Account Id :: ";
        public static final String ACCOUNT_SUSPENDED_SUCCESSFULLY_MESSAGE = "Account successfully suspended! Account Id :: ";
        public static final String USER_NOT_FOUND_MESSAGE = "User is not found for this id :: ";
        public static final String TRANSFER_MUST_BE_ON_THE_SAME_CURRENCY_MESSAGE = "Transfer must be on the same currency of the account";
        public static final String TRANSFER_TYPE_IS_NOT_SUPPORTED_MESSAGE = "Transfer type is not supported :: ";
        public static final String CASH_DEPOSITED_SUCCESSFULLY_MESSAGE = "Cash deposited successfully ";
        public static final String CASH_WITHDRAWN_SUCCESSFULLY_MESSAGE = "Cash withdrawn successfully ";
        public static final String CASH_TRANSFER_SUCCESSFULLY_MESSAGE = "Cash transfer done successfully ";
        public static final String ACCOUNT_HAS_REQUEST_UNDER_PROCESSING_MESSAGE = "This account number has already a request under processing now, please try again later!";
        public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error! ";

    }

    public static class DeletionStatusValues {
        public static final Integer NOT_DELETED = 0;
        public static final Integer DELETED = 1;

    }
}
