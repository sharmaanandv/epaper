package com.iapps.epaper.service;

import com.iapps.epaper.dto.EpaperDTO;
import com.iapps.epaper.dto.xml.EpaperRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EpaperService {
    Long save(EpaperRequest epaperRequest, String filename);

    Page<EpaperDTO> getAll(Pageable pageable, EpaperDTO spec);

}
