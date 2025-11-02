package br.com.henrique.trackify.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.trackify.Models.UserMediaEntryModel;

@Repository
public interface UserMediaEntryRepository extends JpaRepository<UserMediaEntryModel, String>{
    
}
