package com.iapps.epaper.dto.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenInfo {

    @XmlAttribute
    private int width;

    @XmlAttribute
    private int height;

    @XmlAttribute
    private int dpi;

}