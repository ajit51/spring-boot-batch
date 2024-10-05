package com.batch.spring_boot_batch.utility;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Utility {
    public String getValidData(String value) {
        if (null != value && value.length() > 0) {
            String value1 = value.replace("\r", " ")
                    .replace("\n", " ")
                    .replace("\t", " ")
                    .replace("\0", "");
            if (!value1.equalsIgnoreCase("NULL")) {
                return value1;
            }
        }
        return null;
    }

    public SimpleDateFormat getDateFormatwithTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
