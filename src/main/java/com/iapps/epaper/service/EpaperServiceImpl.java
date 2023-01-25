package com.iapps.epaper.service;

import com.iapps.epaper.dto.EpaperDTO;
import com.iapps.epaper.dto.xml.EpaperRequest;
import com.iapps.epaper.entity.EpaperEntity;
import com.iapps.epaper.repository.EpaperRepository;
import com.iapps.epaper.specification.EpaperSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
@Slf4j
public class EpaperServiceImpl implements EpaperService {

    private final EpaperRepository epaperRepository;

    public EpaperServiceImpl(final EpaperRepository epaperRepository) {
        this.epaperRepository = epaperRepository;
    }

    /**
     * Method to save EpaperEntity
     * @param epaperRequest XML parsed DTO
     * @param filename original name of XML file
     * @return pkId of record persisted in EpaperEntity
     */
    @Override
    public Long save(EpaperRequest epaperRequest, String filename) {
        log.info("save Entered: {} with filename {}", epaperRequest, filename);
        EpaperEntity epaperEntity = epaperRepository.save(
                EpaperEntity.builder().dpi(epaperRequest.getDeviceInfo().getScreenInfo().getDpi()).filename(filename)
                        .height(epaperRequest.getDeviceInfo().getScreenInfo().getHeight())
                        .width(epaperRequest.getDeviceInfo().getScreenInfo().getWidth())
                        .newspaperName(epaperRequest.getDeviceInfo().getAppInfo().getNewspaperName())
                        .uploadTime(Instant.now().toEpochMilli()).build());
        return epaperEntity.getId();
    }

    /**
     * Method to getAll from EpaperEntity with paging, sorting and filtering functionality
     * @param pageable pageable object
     * @param epaperDTO DTO to generate JPA specification for filtering purpose
     * @return page object of EpaperDTO
     */
    @Override
    public Page<EpaperDTO> getAll(Pageable pageable, EpaperDTO epaperDTO) {
        return epaperRepository.findAll(EpaperSpecification.getEpaperSpecification(epaperDTO), pageable)
                .map((entity) -> {
                    EpaperDTO dto = new EpaperDTO();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                });
    }

}
