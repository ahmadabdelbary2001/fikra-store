package fikra.store.application.features.user.queries;

import fikra.store.domain.User;

public interface GetUserByUsernameQuery {
    User execute(String username);
}