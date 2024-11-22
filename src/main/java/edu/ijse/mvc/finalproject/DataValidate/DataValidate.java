package edu.ijse.mvc.finalproject.DataValidate;

import java.util.regex.Pattern;

public class DataValidate {
    public boolean validatePhoneNumber(String data) {
        String userPattern = "^0(7[0-8]|11|2[0-9])[0-9]{7}$";
        if (data.matches(userPattern)) {
            return true;
        }else {
            return false;
        }
    }
    public boolean validateEmail(String data) {
        Pattern userPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        if (userPattern.matcher(data).matches()) {
            return true;
        }else {
            return false;
        }
    }
    public boolean validateName(String data) {
        Pattern userPattern = Pattern.compile("^[A-Za-z ]+$");
        if (userPattern.matcher(data).matches()) {
            return true;
        }else {
            return false;
        }
    }
    public boolean validateAddress(String data) {
        Pattern userPattern = Pattern.compile("^[A-Za-z0-9.,' -/]+$");
        if (userPattern.matcher(data).matches()) {
            return true;
        }else {
            return false;
        }
    }
    public boolean validateIntNumbers(String data) {
        Pattern userPattern = Pattern.compile("^(0|[1-9][0-9]?)$");
        if (userPattern.matcher(data).matches()) {
            return true;
        }else {
            return false;
        }
    }
    public boolean validateDoubleAndIntNumbers(String data) {
        Pattern userPattern = Pattern.compile("^(0|[1-9][0-9]?)(\\.[0-9]{1,2})?$");
        if (userPattern.matcher(data).matches()) {
            return true;
        }else {
            return false;
        }
    }

    public boolean validatePasswoord(String data) {
        String userPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,20}$";
        return data.matches(userPattern);
    }
}
