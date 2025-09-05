package fikra.store.application.features.user.commands;

import fikra.store.domain.User;

public interface RegisterCustomerCommand {
    User execute(User customer);
}