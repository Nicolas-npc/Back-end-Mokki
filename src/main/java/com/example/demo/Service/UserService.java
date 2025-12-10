package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.Model.UserModel;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;
            
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel crearUsuario(UserModel usuario) {
        return userRepository.save(usuario);
    }

    public List<UserModel> obtenerTodos() {
        return userRepository.findAll();
    }

    public UserModel actualizarUsuario(int id, UserModel nuevo) {
        UserModel existente = userRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setNombre(nuevo.getNombre());
            existente.setEmail(nuevo.getEmail());
            existente.setRol(nuevo.getRol());
            existente.setContraseña(nuevo.getContraseña());
            return userRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(int id) {
        userRepository.deleteById(id);
    }

    public UserModel buscarPorId(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
