package com.example.demo.Repository;

import com.example.demo.Model.RolModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Integer> {
    Optional<RolModel> findByNombre(String nombre);

}
