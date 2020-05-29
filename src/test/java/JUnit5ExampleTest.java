import org.junit.jupiter.api.Test;
import org.ooad_dws4.Application;

import static org.junit.jupiter.api.Assertions.fail;

public class JUnit5ExampleTest {

    @Test
    void printHelloTest() {
        System.out.println("Hello Test!");
    }

    @Test
    void runApplication() {
        Thread t = new Thread() {
            @Override
            public void run() {
                Application.main(null);
            }
        };

        t.start();

        try {
            Thread.sleep(3000);
            t.interrupt();
        } catch (InterruptedException e) {
        } catch (Exception e) {
            fail("unexpected exception");
        }
    }
}
