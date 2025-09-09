package fikra.store.adapters.persistence.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import fikra.store.adapters.persistence.jpa.entities.UserEntity;
import fikra.store.domain.User;
import fikra.store.domain.Admin;
import fikra.store.domain.Customer;
import fikra.store.domain.Role;

@Component
public class UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public User toDomain(UserEntity e) {
        if (e == null) return null;

        Role role = e.getRole() == null ? Role.CUSTOMER : e.getRole();

        if (role == Role.ADMIN) {
            // create Admin domain object
            Admin a = new Admin(e.getId(), e.getUsername(), e.getPassword());
            return a;
        } else {
            // default to Customer
            Customer c = new Customer(e.getId(), e.getUsername(), e.getPassword());
            return c;
        }
    }

    // public List<User> toDomainList(List<UserEntity> entities);

    public UserEntity toEntity(User d) {
        if (d == null) return null;
        UserEntity e = new UserEntity();
        if (d.getId() != null) e.setId(d.getId());
        e.setUsername(d.getUsername());
        e.setPassword(d.getPassword());
        e.setRole(d.getRole() == null ? Role.CUSTOMER : d.getRole());
        return e;
    }
}
