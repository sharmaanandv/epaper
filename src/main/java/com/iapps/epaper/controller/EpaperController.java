package com.iapps.epaper.controller;

import com.iapps.epaper.dto.EpaperDTO;
import com.iapps.epaper.dto.xml.EpaperRequest;
import com.iapps.epaper.exception.IappsException;
import com.iapps.epaper.service.EpaperService;
import com.iapps.epaper.validator.XmlValidator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

@RequestMapping (value = "/v1/epaper")
@RestController
@Slf4j
public class EpaperController {

    private final EpaperService epaperService;
    private final XmlValidator xmlValidator;

    public EpaperController(final EpaperService epaperService, final XmlValidator xmlValidator) {
        this.epaperService = epaperService;
        this.xmlValidator = xmlValidator;
    }

    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Success", content = @Content (mediaType = "application/json")),
            @ApiResponse (responseCode = "404", description = "Server Error", content = @Content (mediaType = "application/json")), })
    @PostMapping (value = "upload", consumes = { "multipart/form-data" })
    @ResponseStatus (HttpStatus.CREATED)
    public Long upload(@RequestParam ("file") MultipartFile xmlFile) {
        log.info("uploadEpaper Entered with filename {}", xmlFile.getOriginalFilename());
        xmlValidator.validate(xmlFile);
        return epaperService.save(parseXmlFileToEpaperRequest(xmlFile), xmlFile.getOriginalFilename());
    }

    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Success", content = @Content (mediaType = "application/json")),
            @ApiResponse (responseCode = "404", description = "Server Error", content = @Content (mediaType = "application/json")), })
    @PostMapping ("getAll")
    @ResponseStatus (HttpStatus.OK)
    public Page<EpaperDTO> getAll(@RequestParam (defaultValue = "0") int pageNo,
            @RequestParam (defaultValue = "10") int pageSize, @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "asc", required = false) String sortDir,
            @RequestBody @Validated EpaperDTO epaperDTO) {
        log.info("getAll method invoke");
        return epaperService.getAll(getPageable(pageNo, pageSize, sortBy, sortDir), epaperDTO);
    }

    /**
     * Method to parse XML file to DTO
     */
    private EpaperRequest parseXmlFileToEpaperRequest(MultipartFile xmlFile) {
        try {
            Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(EpaperRequest.class).createUnmarshaller();
            return (EpaperRequest) jaxbUnmarshaller.unmarshal(xmlFile.getInputStream());
        } catch (Exception e) {
            throw new IappsException(1002, "XML is not valid");
        }
    }

    /**
     * Method to prepare page object from pageNo, pageSize, sortBy and sort direction
     */
    private Pageable getPageable(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        return PageRequest.of(pageNo, pageSize, sort);
    }

}
