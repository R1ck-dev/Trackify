package br.com.henrique.trackify.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.trackify.Models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{
    Optional<UserModel> findByEmail(String email);

}
