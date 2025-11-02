package br.com.henrique.trackify.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.trackify.Models.MediaModel;

@Repository
public interface MediaRepository extends JpaRepository<MediaModel, String>{
    Optional<MediaModel> findByTitleAndAuthor(String title, String author);
} 
