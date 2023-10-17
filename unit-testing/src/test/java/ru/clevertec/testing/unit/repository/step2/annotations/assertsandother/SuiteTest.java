package ru.clevertec.testing.unit.repository.step2.annotations.assertsandother;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags("tag")
@SelectPackages("ru.clevertec.testing.unit.repository")
public class SuiteTest {
}
