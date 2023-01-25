package com.iapps.epaper.specification;

import com.iapps.epaper.dto.EpaperDTO;
import com.iapps.epaper.entity.EpaperEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EpaperSpecification {

    /**
     * Method to generate JPA Specification for EpaperEntity from DTO
     * @param epaperDTO
     * @return
     */
    public static Specification<EpaperEntity> getEpaperSpecification(EpaperDTO epaperDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (epaperDTO.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), epaperDTO.getId()));
            }
            if (epaperDTO.getNewspaperName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("newspaperName"), epaperDTO.getNewspaperName()));
            }
            if (epaperDTO.getWidth() != null) {
                predicates.add(criteriaBuilder.equal(root.get("width"), epaperDTO.getWidth()));
            }
            if (epaperDTO.getHeight() != null) {
                predicates.add(criteriaBuilder.equal(root.get("height"), epaperDTO.getHeight()));
            }
            if (epaperDTO.getDpi() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dpi"), epaperDTO.getDpi()));
            }
            if (epaperDTO.getFilename() != null) {
                predicates.add(criteriaBuilder.equal(root.get("filename"), epaperDTO.getFilename()));
            }
            // User can filter uploadTime by passing uploadDate
            // As it will be difficult for user to entered exact uploadTime
            // So user can pass uploadDate to see records to uploaded on that date
            if (epaperDTO.getUploadDate() != null) {
                LocalDateTime startDate = LocalDateTime.of(epaperDTO.getUploadDate(), LocalTime.MIN);
                LocalDateTime endDate = LocalDateTime.of(epaperDTO.getUploadDate(), LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get("uploadTime"), Timestamp.valueOf(startDate).getTime(),
                        Timestamp.valueOf(endDate).getTime()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        };
    }
}
