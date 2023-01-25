package com.iapps.epaper.validator;

import com.iapps.epaper.exception.IappsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

@Component
@Slf4j
public class XmlValidator {

    private static final String EPAPER_XSD_PATH = "Epaper.xsd";

    /**
     * Method will validate user input XML file with predefined XSD
     */
    public void validate(MultipartFile xmlFile) {
        try {
            log.info("validate Entered");
            Validator validator = initValidator();
            validator.validate(new StreamSource(xmlFile.getInputStream()));
        } catch (Exception e) {
            log.error("Error occurred while validating XML {}", e.getMessage());
            throw new IappsException(1001, "XML is not valid");
        }
    }

    /**
     * Method to initialize javax.xml.validation.Validator Object
     */
    private Validator initValidator() throws SAXException, IOException {
        log.info("initValidator Entered");
        Source schemaFile = new StreamSource(new ClassPathResource(EPAPER_XSD_PATH).getInputStream());
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaFile).newValidator();
    }
}
