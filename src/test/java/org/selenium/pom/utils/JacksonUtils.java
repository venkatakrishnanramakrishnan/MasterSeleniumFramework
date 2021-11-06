package org.selenium.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pom.objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    ////We are creating a 'static' method that will return java object >>>here the object is BillingAddress
    public static BillingAddress deserializeJson(InputStream is, BillingAddress billingAddress) throws IOException {
        ////here we are using the object mapper from jackson
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,billingAddress.getClass());
    }
}
