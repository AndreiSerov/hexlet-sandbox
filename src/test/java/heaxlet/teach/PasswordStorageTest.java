package heaxlet.teach;

import hexlet.teach.archive.PasswordStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author andreiserov
 */
class PasswordStorageTest {
    /**
     *
     * DEBUG - with complex logic
     *
     *
     */
    PasswordStorage sut = new PasswordStorage();

    @Test
    void findByName() {
        assertEquals("google_pass", sut.findByName("google"));
    }

    @Test
    void save() {
        // given
        final String yahoo_pass = "yahoo_pass";
        final String yahoo = "yahoo";

        // when
        sut.save(yahoo, yahoo_pass);

        // Then
        assertEquals(yahoo_pass, sut.findByName(yahoo));
    }

    @Test
    void updateGoogle() {
        // given
        final String new_pass = "new_google_pass";
        final String google = "google";

        // when
        sut.save(google, new_pass);

        // Then
        assertEquals(new_pass, sut.findByName(google));
    }

    @Test
    void whenDelete_thenNothingCanGet() {
        // given
        final String google = "google";

        // when
        sut.deleteByName(google);

        assertNull(sut.findByName(google));
    }


}