package fikra.store.application.features.user.commands;

import fikra.store.domain.User;

public interface RegisterAdminCommand {
    User execute(User admin);
}