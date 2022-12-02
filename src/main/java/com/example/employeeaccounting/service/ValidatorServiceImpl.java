package com.example.employeeaccounting.service;

import com.example.employeeaccounting.exception.WrongNameException;
import com.example.employeeaccounting.exception.WrongSurnameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public String validateName(String firstName) {
        if (StringUtils.isAlpha(firstName)) {
           return StringUtils.capitalize(firstName.toLowerCase());

        }
        throw new WrongNameException();
    }

    @Override
    public String validateSurname(String lastName) {
        String[] words = lastName.split("-");
        for (int i = 0; i < words.length; i++) {
            if (StringUtils.isAlpha(words[i])) {
                words[i] = StringUtils.capitalize(words[i].toLowerCase());
            } else {
                throw new WrongSurnameException();
            }
        }
        return String.join("-", words);
    }
}
