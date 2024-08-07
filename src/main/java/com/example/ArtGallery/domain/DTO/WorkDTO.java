package com.example.ArtGallery.domain.DTO;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDTO {
    private Long id;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date createdAt;

    private Long categoryId;
    private int artStyle;
    private boolean comition;
    private Long userId; // Ссылка на User
    private String description;
    private String image;

}
