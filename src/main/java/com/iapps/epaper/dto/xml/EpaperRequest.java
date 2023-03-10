package com.iapps.epaper.dto.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class EpaperRequest {

    @XmlElement
    private DeviceInfo deviceInfo;

    @XmlElement
    private GetPages getPages;

}

