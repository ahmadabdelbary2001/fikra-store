package fikra.store.application.features.user.queries;

import java.util.List;

import fikra.store.domain.Role;
import fikra.store.domain.User;

public interface FindUsersByRoleQuery {
    List<User> execute(Role role);
}