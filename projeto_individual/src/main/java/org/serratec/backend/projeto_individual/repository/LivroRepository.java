package org.serratec.backend.projeto_individual.repository;

import org.serratec.backend.projeto_individual.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}