package com.recruitflow.api.repositories;

import com.recruitflow.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
