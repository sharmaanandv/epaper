package com.iapps.epaper.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpaperDTO {

    private Long id;
    private String newspaperName;
    private Integer width;
    private Integer height;
    private Integer dpi;

    // User can filter uploadTime by passing uploadDate
    // As it will be difficult for user to entered exact uploadTime
    // So user can pass uploadDate to see records to uploaded on that date
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate uploadDate;

    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    private long uploadTime;
    private String filename;

}
