package uce.edu.web.api.application;

import uce.edu.web.api.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.application.representation.UserRepresentation;
import uce.edu.web.api.infraestructure.UserRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Transactional
    public void crearUser(User user) {
        userRepository.persist(user);
    }

    @Transactional
    public void borrarUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void actualizarUser(Long id, User userR) {
        User user = userRepository.findById(id);
        user.setNombre(userR.getNombre());
        user.setPassword(userR.getPassword());
        user.setRole(userR.getRole());
        userRepository.persist(user);
    }

    @Transactional
    public void actualizarParcial(Long id, User userR) {
        User user = userRepository.findById(id);
        if (userR.getNombre() != null) {
            user.setNombre(userR.getNombre());
        }
        if (userR.getPassword() != null) {
            user.setPassword(userR.getPassword());
        }
        if (userR.getRole() != null) {
            user.setRole(userR.getRole());
        }
        userRepository.persist(user);
    }

    public UserRepresentation consultarPorId(Long id) {
        return mapperToUR(userRepository.findById(id));
    }

    public List<UserRepresentation> listarUsers() {
        List<User> users = userRepository.findAll().list();
        List<UserRepresentation> userRepresentations = new ArrayList<>();
        for (User user : users) {
            userRepresentations.add(mapperToUR(user));
        }
        return userRepresentations;
    }

    public UserRepresentation mapperToUR(User user) {
        return new UserRepresentation(user.getId(), user.getNombre(), user.getRole());
    }

    public User validateUser(String nombre, String password) {
        User user = userRepository.find("nombre", nombre).firstResult();
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
