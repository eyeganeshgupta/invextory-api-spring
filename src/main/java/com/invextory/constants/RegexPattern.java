package com.invextory.constants;

public class RegexPattern {
    public static final String PHONE_NUMBER = "^\\+?[0-9]{7,15}$";
    public static final String PASSWORD_COMPLEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private RegexPattern() {

    }
}
