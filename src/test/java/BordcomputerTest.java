import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BordcomputerTest {

    Bordcomputer BCObj = new Bordcomputer();

    //@ParameterizedTest
    //@MethodSource("fizzBuzzData")
   /* @Test
    void testFile() {
        BCObj.readConfig();//"Geraete"
        File file = new File("src/Geraete.txt");
        assertThat(file).exists();
       // assertThat(calculatedState).describedAs(testName).isEqualTo(expectedResult);
    }*/

    @Test
    @DisplayName("look for the File")
    public void Filetest() {
        assertThat("abc").isEqualTo(123);
    }



}