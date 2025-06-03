package com.invextory.constants;

public class AppText {

    public static final String NAME_REQUIRED = "Name is required";
    public static final String NAME_TOO_LONG = "Name must not exceed 100 characters";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID = "Email should be valid";
    public static final String EMAIL_TOO_LONG = "Email must not exceed 100 characters";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_LENGTH = "Password must be between 8 and 100 characters";
    public static final String PASSWORD_COMPLEXITY = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character.";
    public static final String PHONE_INVALID = "Phone number should be valid and contain only digits";
    public static final String ROLE_REQUIRED = "User role is required";

    public static final String CATEGORY_NAME_REQUIRED = "Category name is required";
    public static final String CATEGORY_NAME_TOO_LONG = "Category name must not exceed 100 characters";

    public static final String SUPPLIER_NAME_REQUIRED = "Supplier name is required";
    public static final String SUPPLIER_NAME_TOO_LONG = "Supplier name must not exceed 100 characters";
    public static final String SUPPLIER_CONTACT_TOO_LONG = "Contact information must not exceed 150 characters";
    public static final String SUPPLIER_ADDRESS_TOO_LONG = "Address must not exceed 255 characters";

    public static final String PRODUCT_NAME_REQUIRED = "Product name is required";
    public static final String PRODUCT_NAME_TOO_LONG = "Product name must not exceed 100 characters";
    public static final String PRODUCT_SKU_REQUIRED = "Product SKU is required";
    public static final String PRODUCT_SKU_TOO_LONG = "Product SKU must not exceed 50 characters";
    public static final String PRODUCT_DESCRIPTION_TOO_LONG = "Description must not exceed 255 characters";
    public static final String PRODUCT_IMAGE_URL_TOO_LONG = "Image URL must not exceed 255 characters";
    public static final String PRODUCT_CATEGORY_REQUIRED = "Product category is required";

    public static final String TRANSACTION_TOTAL_PRODUCTS_REQUIRED = "Total products must be specified.";
    public static final String TRANSACTION_TOTAL_PRICE_REQUIRED = "Total price must be specified.";
    public static final String TRANSACTION_TYPE_REQUIRED = "Transaction type must be specified.";
    public static final String TRANSACTION_STATUS_REQUIRED = "Transaction status must be specified.";
    public static final String TRANSACTION_DESCRIPTION_TOO_LONG = "Description must not exceed 255 characters.";
    public static final String TRANSACTION_NOTE_TOO_LONG = "Note must not exceed 255 characters.";

    public static final String BATCH_NUMBER_REQUIRED = "Batch number is required.";
    public static final String BATCH_NUMBER_TOO_LONG = "Batch number cannot be more than 50 characters.";
    public static final String STOCK_QUANTITY_REQUIRED = "Stock quantity is required.";
    public static final String STOCK_QUANTITY_NEGATIVE = "Stock quantity cannot be negative.";
    public static final String MRP_REQUIRED = "Maximum Retail Price (MRP) is required.";
    public static final String MRP_POSITIVE = "MRP must be a positive value.";
    public static final String PURCHASE_PRICE_REQUIRED = "Purchase price is required.";
    public static final String PURCHASE_PRICE_POSITIVE = "Purchase price must be a positive value.";
    public static final String DISCOUNT_INVALID = "Discount must be a valid non-negative value.";
    public static final String GST_REQUIRED = "GST rate is required.";
    public static final String GST_INVALID = "GST rate must be a non-negative value.";
    public static final String SELLING_PRICE_REQUIRED = "Selling price is required.";
    public static final String SELLING_PRICE_POSITIVE = "Selling price must be a positive value.";

    public static final String PRODUCT_ID_REQUIRED = "Product ID is required";
    public static final String SUPPLIER_ID_REQUIRED = "Supplier ID is required";

    public static final String USER_ID_REQUIRED = "User ID is required";

    public static final String LOGIN_EMAIL_REQUIRED = "Email is required for login.";
    public static final String LOGIN_PASSWORD_REQUIRED = "Password is required for login.";

    public static final String PRODUCT_BATCH_ID_REQUIRED = "Product batch ID is required.";
    public static final String TRANSACTION_QUANTITY_REQUIRED = "Transaction quantity is required.";
    public static final String TRANSACTION_QUANTITY_POSITIVE = "Transaction quantity must be at least 1.";

    public static final String LOG_REGISTER_INIT = "Initiating registration process for user with email: {}";
    public static final String LOG_ROLE_PROVIDED = "User role specified as: {}";
    public static final String LOG_ROLE_DEFAULT = "No user role specified. Assigning default role: MANAGER.";
    public static final String LOG_USER_SAVE = "Persisting new user record to the database for email: {}";
    public static final String LOG_REGISTER_SUCCESS = "User registration completed successfully for email: {}";
    public static final String USER_REGISTRATION_SUCCESS = "User has been registered successfully.";

    // Login-related messages
    public static final String LOG_LOGIN_INIT = "Initiating login process for user with email: {}";
    public static final String LOG_USER_NOT_FOUND = "No user found with the provided email: {}";
    public static final String LOG_INVALID_PASSWORD = "Password mismatch for user with email: {}";
    public static final String LOG_LOGIN_SUCCESS = "User logged in successfully with email: {}";
    public static final String USER_LOGIN_SUCCESS_MESSAGE = "User has been successfully authenticated and logged in.";

    // Error messages
    public static final String ERROR_EMAIL_NOT_FOUND = "Email not found in the system.";
    public static final String ERROR_PASSWORD_MISMATCH = "The provided password does not match our records.";

    // JWT Token related
    public static final String JWT_TOKEN_EXPIRATION = "6 months";
    public static final String JWT_LOGIN_SUCCESS = "Token generated and login successful for user with email: {}";

    // Get All Users
    public static final String LOG_GET_ALL_USERS_INIT = "Fetching all users from the database.";
    public static final String LOG_USERS_RETRIEVED = "Successfully retrieved {} users from the database.";
    public static final String USERS_FETCH_SUCCESS_MESSAGE = "The users have been successfully fetched.";

    // Get Current LoggedIn User
    public static final String LOG_GET_CURRENT_USER_INIT = "Fetching current logged-in user.";
    public static final String LOG_AUTHENTICATION_NULL = "Authentication object is null.";
    public static final String LOG_AUTHENTICATION_EMAIL = "Authenticated user's email: {}";
    public static final String LOG_GET_CURRENT_USER_SUCCESS = "Successfully retrieved current user: {}";

    // Get User By ID
    public static final String LOG_GET_USER_BY_ID_INIT = "Fetching user with ID: {}";
    public static final String LOG_GET_USER_BY_ID_SUCCESS = "Successfully retrieved user with ID: {}";
    public static final String ERROR_USER_ID_NOT_FOUND = "User ID not found.";
    public static final String USER_FETCH_SUCCESS_MESSAGE = "User retrieved successfully.";

    public static final String LOG_UPDATE_USER_INIT = "Updating user with ID: {}";
    public static final String ERROR_USER_NOT_FOUND = "User Not Found";
    public static final String USER_UPDATE_SUCCESS_MESSAGE = "User successfully updated";

    public static final String LOG_DELETE_USER_INIT = "Attempting to delete user with ID: {}";
    public static final String USER_DELETE_SUCCESS_MESSAGE = "User successfully deleted";

    public static final String TRANSACTION_FETCH_SUCCESS_MESSAGE = "User transactions successfully retrieved.";
    public static final String TRANSACTION_FETCH_ERROR_MESSAGE = "An error occurred while fetching user transactions.";

    public static final String LOG_GET_USER_TRANSACTIONS_INIT = "Fetching transactions for user with ID: {}";
    public static final String LOG_USERID_NOT_FOUND = "User with ID: {} not found.";
    public static final String LOG_TRANSACTION_FETCH_SUCCESS = "Successfully retrieved transactions for user with ID: {}";

    // =================== CATEGORY MESSAGES ===================
    public static final String CATEGORY_CREATE_SUCCESS_MESSAGE = "Category has been successfully created.";
    public static final String CATEGORY_FETCH_SUCCESS_MESSAGE = "Category data fetched successfully.";
    public static final String CATEGORY_UPDATE_SUCCESS_MESSAGE = "Category has been successfully updated.";
    public static final String CATEGORY_DELETE_SUCCESS_MESSAGE = "Category has been successfully deleted.";
    public static final String ERROR_CATEGORY_NOT_FOUND = "Category not found.";

    // =================== CATEGORY LOG MESSAGES ===================
    public static final String LOG_CREATE_CATEGORY_INIT = "Initiating category creation for name: {}";
    public static final String LOG_CREATE_CATEGORY_SUCCESS = "Category created successfully with name: {}";

    public static final String LOG_GET_ALL_CATEGORIES_INIT = "Fetching all categories from the database.";
    public static final String LOG_GET_ALL_CATEGORIES_SUCCESS = "Retrieved {} categories from the database.";

    public static final String LOG_GET_CATEGORY_BY_ID_INIT = "Fetching category with ID: {}";
    public static final String LOG_GET_CATEGORY_BY_ID_SUCCESS = "Successfully retrieved category with ID: {}";
    public static final String LOG_CATEGORY_NOT_FOUND_BY_ID = "No category found with ID: {}";

    public static final String LOG_UPDATE_CATEGORY_INIT = "Updating category with ID: {}";
    public static final String LOG_UPDATE_CATEGORY_SUCCESS = "Category updated successfully with ID: {}";

    public static final String LOG_DELETE_CATEGORY_INIT = "Deleting category with ID: {}";
    public static final String LOG_DELETE_CATEGORY_SUCCESS = "Category deleted successfully with ID: {}";

    private AppText() {

    }
}
