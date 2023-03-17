package com.bkavun.shorturlrestapi.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class ShortUrlCodeGenerator implements IdentifierGenerator {

    public static String generateCode() {
        // generate rand hex number based on current time
        String temp = Long.toHexString(System.currentTimeMillis());
        String code = "";

        // randomly convert chars to uppercase to reduce the chance of code repetition
        for (int i = 0; i < temp.length(); i++) {
            code += new Random().nextBoolean() ?
                    temp.charAt(i) :
                    Character.toUpperCase(temp.charAt(i));
        }

        return code;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return generateCode();
    }
}
