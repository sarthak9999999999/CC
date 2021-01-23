package com.example.cc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardModel
{
    private String CardNumber;
    private String expiry;
    private String cvv;
    private String firstName;
    private String lastName;

    public CardModel() {
        this.CardNumber = null;
        this.expiry = null;
        this.cvv = null;
        this.firstName = null;
        this.lastName = null;
    }

    public CardModel(String CardNumber, String expiry, String cvv, String firstName, String lastName) {
        this.CardNumber = CardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static boolean isValidCardNumber(String card_num) {
        return isValidCard(card_num) && (isValidAmerican(card_num) || isValidDiscover(card_num) || isValidMasterCard(card_num) || isValidVisa(card_num));
    }

    private static boolean isValidCard(String card_num) {
        int sum = 0;
        for (int i = 1; i <= card_num.length(); i++) {
            int digit = Character.getNumericValue(card_num.charAt(card_num.length() - i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        if(sum % 10 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isValidAmerican(String card_num)
    {
        return card_num.length() == 15 && card_num.charAt(0) == '3' && (card_num.charAt(1) == '4' || card_num.charAt(1) == '7');
    }

    public static boolean isValidMasterCard(String card_num) {
        return card_num.length() == 16 &&
                ((card_num.charAt(0) == '5' && card_num.charAt(1) >= '1' && card_num.charAt(1) <= '5') ||
                        (card_num.charAt(0) == '2' && card_num.charAt(1) >= '2' && card_num.charAt(1) <= '7'));
    }

    public static boolean isValidVisa(String card_num)
    {

        return card_num.length() >= 16 && card_num.length() <= 19 && card_num.charAt(0) == '4';
    }

    public static boolean isValidDiscover(String card_num)
    {
        if (card_num.length() == 16) {
            if (card_num.startsWith("6011")) {
                int nextTwoDigits = Integer.parseInt(card_num.substring(4, 6));
                return (nextTwoDigits >= 0 && nextTwoDigits <= 9) || (nextTwoDigits >= 20 && nextTwoDigits <= 49) ||
                        nextTwoDigits == 74 || (nextTwoDigits >= 77 && nextTwoDigits <= 79) ||
                        (nextTwoDigits >= 86 && nextTwoDigits <= 99);
            } else if (card_num.charAt(0) == '6') {
                int s_t = Integer.parseInt(card_num.substring(1, 3));
                return s_t >= 44 && s_t <= 59;
            }
        }
        return false;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        CardNumber = CardNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
