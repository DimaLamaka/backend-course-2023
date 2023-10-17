package ru.clevertec.testing.unit.repository.step3.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class TestInstancePostProcessorExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context)
            throws Exception {

        System.out.println("test instance post processor " + testInstance);
    }
}
