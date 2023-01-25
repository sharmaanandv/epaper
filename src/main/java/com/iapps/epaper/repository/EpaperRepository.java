package com.iapps.epaper.repository;

import com.iapps.epaper.entity.EpaperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpaperRepository extends PagingAndSortingRepository<EpaperEntity, Long> {
    Page<EpaperEntity> findAll(Specification<EpaperEntity> spec, Pageable pageable);

}
