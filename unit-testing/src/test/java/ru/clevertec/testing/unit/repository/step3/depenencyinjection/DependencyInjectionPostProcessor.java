package ru.clevertec.testing.unit.repository.step3.depenencyinjection;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class DependencyInjectionPostProcessor implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        System.out.println("post proccesor");
    }
}
