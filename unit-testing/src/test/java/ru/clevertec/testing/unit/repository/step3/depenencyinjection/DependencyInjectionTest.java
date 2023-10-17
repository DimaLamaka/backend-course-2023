package ru.clevertec.testing.unit.repository.step3.depenencyinjection;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.clevertec.testing.unit.model.User;
import ru.clevertec.testing.unit.util.UuidUtil;

import java.util.UUID;

import static ru.clevertec.testing.unit.model.User.Role.GUEST;


@ExtendWith({UserInjectResolverExtension.class})
@AllArgsConstructor
class DependencyInjectionTest {

    @InjectUser
    User defaultUser;

    //TestInfo - display name, tags, class and method info
    //Repetitions info - repeated test info

    @Test
    void testDefaultUser() {

        Assertions.assertNotNull(defaultUser);
        Assertions.assertEquals("defaultName", defaultUser.getName());
        Assertions.assertEquals(GUEST, defaultUser.getRole());
    }

    @Test
    @Tag("new")
    void testGenerateUuidTestInfo(TestInfo testInfo) {
        System.out.println("testGenerateUuidTestInfo " + this);
//        System.out.println("Random value is: " + value);
        UUID uuid = UuidUtil.generateUuid();

        Assertions.assertNotNull(uuid);
    }

    @RepeatedTest(2)
    @Tag("new")
    void testGenerateRepetitionInfo(RepetitionInfo repetitionInfo) {
        System.out.println("testGenerateRepetitionInfo " + this);
        UUID uuid = UuidUtil.generateUuid();

        Assertions.assertNotNull(uuid);
    }

    @Test
    @DisplayName("Test Reporter")
    @Tag("new")
//    @Disabled("Test reporter not working")
    void testGenerateTestReporter(TestReporter testReporter) {
        System.out.println("testGenerateTestReporter " + this);
        UUID uuid = UuidUtil.generateUuid();
        testReporter.publishEntry("KEY", uuid.toString());
    }

}
