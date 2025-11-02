package br.com.henrique.trackify.Models;

import br.com.henrique.trackify.Enums.MediaStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_media")
@Data
public class UserMediaEntryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "status", nullable = false, unique = false)
    private MediaStatusEnum status;

    @Column(name = "rating", nullable = true, unique = false)
    private Integer rating;

    @Column(name = "personal_notes", columnDefinition = "TEXT", nullable = true, unique = false)
    private String personalNotes;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private MediaModel media;
}
