package next.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class UserTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void isSameTest() {
        User user = new User("id", "pass", "newname", "newemail");
        User user2 = new User("id", "pass", "newname", "newemail");

        assertEquals(true, user.isSameUser(user2));
    }
}