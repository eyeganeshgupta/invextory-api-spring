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

    private AppText() {

    }
}
