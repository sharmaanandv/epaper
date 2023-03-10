package com.iapps.epaper.dto.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
    @XmlElement
    private String newspaperName;

    @XmlElement
    private double version;

}