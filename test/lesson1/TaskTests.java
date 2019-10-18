package lesson1;

import org.junit.jupiter.api.Test;

public class TaskTests extends AbstractTaskTests {

    @Test
    public void testTemp(){
        JavaTasks.sortTemperatures("input/temp_in1.txt", "input/temp_in1.txt");
    }
}
