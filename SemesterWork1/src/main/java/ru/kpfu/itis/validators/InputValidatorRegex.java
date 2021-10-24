package ru.kpfu.itis.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorRegex implements InputValidator{
private final String emailReg = "^([\\w!#$%&'*+\\/=?^_`\\-{|}~]+|\"" +
        "[\\w!#$%&'*+\\/=?^_`\\s{|}~\\.?(),:;<>@\\\\\\[\\]]*\")+" +
        "[\\w!#$%&'*+\\/=?^_`{|}~\\.]*@\\w+(\\w\\-)*\\.([A-Za-z]+)$";
private final String dateReg = "^(?:[0-2][0-9]|3[0-1])\\.(?:0[0-9]|1[0-2])\\.(?:19[0-9][0-9]|20[0-1][0-9]|202[0-1])$";
private final String nameReg = "[A-Za-zА-Яа-я_0-9]{2,}";

    @Override
    public String checkPassword(String password) {
        if(password.length() < 5) {
            return "Password must be at least 5 characters long";
        }
        String message = "Password must contains ";
        if(!Pattern.compile("[A-Z]").matcher(password).find()){
            return message + "capital letter";
        }
        if(!Pattern.compile("[a-z]").matcher(password).find()){
            return message + "lowercase letter";
        }
        if(!Pattern.compile("[0-9]").matcher(password).find()){
            return message + "digit";
        }
        return null;
    }

    @Override
    public String checkEmail(String email){
        Pattern pattern = Pattern.compile(emailReg);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            return "Input string is not an email";
        }
        return null;
    }

    @Override
    public String checkDate(String date){
        Pattern pattern = Pattern.compile(dateReg);
        Matcher matcher = pattern.matcher(date);
        if(!matcher.matches()){
            return "Enter real birthdate in dd.mm.yyyy format";
        }
        return null;
    }

    @Override
    public String checkName(String name){

        if(name.length() < 2){
            return "Name must be at least 2 characters long";
        }
        Pattern pattern = Pattern.compile(nameReg);
        Matcher matcher = pattern.matcher(name);
        if(!matcher.matches()){
            return "Name can only contain letters, numbers or symbol \"_\"";
        }
        return null;
    }
}
