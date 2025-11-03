package br.com.henrique.trackify.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.trackify.Models.UserMediaEntryModel;
import br.com.henrique.trackify.Models.UserModel;

@Repository
public interface UserMediaEntryRepository extends JpaRepository<UserMediaEntryModel, String>{
    List<UserMediaEntryModel> findByUser (UserModel user);
}
