package br.com.henrique.trackify.Models;

import br.com.henrique.trackify.Enums.MediaTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "media")
@Data
public class MediaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "author", nullable = false, unique = false)
    private String author;

    @Column(name = "title", length = 255, nullable = false, unique = false)
    private String title;

    @Column(name = "type", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private MediaTypeEnum type;

    @Column(name = "cover_image", columnDefinition = "TEXT", nullable = true, unique = false)
    private String coverImageUrl;
    
    @Column(name = "description", columnDefinition = "TEXT", nullable = true, unique = false)
    private String description;
}
