package ru.clevertec.testing.unit.repository.step3.depenencyinjection;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.clevertec.testing.unit.model.User;
import ru.clevertec.testing.unit.repository.step3.extensions.CustomRandom;

import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class UserInjectResolverExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return User.class.equals(parameter.getType());
    }

    @Override
    public User resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getUser();
    }

    private User getUser() {
        return new User()
                .setRole(User.Role.GUEST)
                .setUuid(UUID.randomUUID())
                .setName("defaultName");
    }
}
