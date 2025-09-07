package fikra.store.adapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.AfterMapping;

import fikra.store.domain.User;
import fikra.store.adapters.persistence.jpa.entities.UserEntity;
import fikra.store.domain.Admin;
import fikra.store.domain.Customer;
import fikra.store.domain.Role;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserEntity toEntity(User domain);

    public abstract User toDomain(UserEntity entity);

    @AfterMapping
    protected void afterToDomain(UserEntity entity, @MappingTarget User target) {
    }

    public User toDomainWithSubtype(UserEntity entity) {
        if (entity == null) return null;
        Role role = entity.getRole() == null ? Role.CUSTOMER : entity.getRole();
        if (role == Role.ADMIN) {
            return new Admin(entity.getId(), entity.getUsername(), entity.getPassword());
        } else {
            return new Customer(entity.getId(), entity.getUsername(), entity.getPassword());
        }
    }
}
