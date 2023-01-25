package com.iapps.epaper.converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateConverter extends XmlAdapter<String, LocalDate> {

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String marshal(LocalDate dateTime) {
        return dateTime.format(dateFormat);
    }

    @Override
    public LocalDate unmarshal(String dateTime) {
        return LocalDate.parse(dateTime, dateFormat);
    }

}