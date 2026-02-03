package uce.edu.web.api.infraestructure;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import uce.edu.web.api.domain.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
