package com.iapps.epaper.dto.xml;

import com.iapps.epaper.converter.CustomLocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPages {

    @XmlAttribute
    private int editionDefId;

    @XmlJavaTypeAdapter (value = CustomLocalDateConverter.class)
    @XmlAttribute
    private LocalDate publicationDate;

}