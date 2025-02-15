package com.mysystem.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUidUtils {

    public static String getNewString(Integer length){
        String randomString = RandomStringUtils.randomAlphanumeric(length);
        return randomString;
    }

}
