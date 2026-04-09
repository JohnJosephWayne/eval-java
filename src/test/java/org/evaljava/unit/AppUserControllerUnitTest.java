package org.evaljava.unit;

import org.evaljava.controller.AppUserController;
import org.evaljava.mock.MockAppUserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class AppUserControllerUnitTest {

    @Test
    public void getByNotExistingId_shouldReturn404() {
        AppUserController controller = new AppUserController(new MockAppUserDao());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,controller.get(99).getStatusCode());
    }

    @Test
    public void getExistingId_shouldReturn200() {
        AppUserController controller = new AppUserController(new MockAppUserDao());

        Assertions.assertEquals(HttpStatus.OK,controller.get(1).getStatusCode());
    }

}
