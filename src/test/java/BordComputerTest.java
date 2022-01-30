import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BordComputerTest {

    BordComputer bordcomputer= new BordComputer();

    @ParameterizedTest
    @MethodSource("BordComputerData")
    void test(String testName,String optionName, boolean expectedResult)throws SecurityException, IllegalArgumentException, ReflectiveOperationException  {
        //String[] checkOptions = bordcomputer.showOptions();
        String[] checkOptions = new String[]{"next","prev","getOptions","lauder","play","chooseOption","quieter","getVolume","getInfoText","setTrackNumber","getCDName","setCDName","getTrackNumber"};

        List<String>newList=new ArrayList<>();
        for(int i=0;i<checkOptions.length;i++){
            newList.add(checkOptions[i]);
        }

        boolean existOption=checkOptionList(newList,optionName);
        assertThat(existOption).describedAs(testName).isEqualTo(expectedResult);
    }



    static Stream<Arguments> BordComputerData () {
        return Stream.of(
                Arguments.of("return Option[next] : exists", "next", true),
                Arguments.of("return Option[prev] : exists", "prev",true),
                Arguments.of("return Option[getOptions] : exists", "getOptions",true),
                Arguments.of("return Option[lauder] : exists", "lauder",true),
                Arguments.of("return Option[play] : exists", "play",true),
                Arguments.of("return Option[chooseOption] : exists", "chooseOption",true),
                Arguments.of("return Option[quieter] : exists", "quieter",true),
                Arguments.of("return Option[getVolume] : exists", "getVolume",true),
                Arguments.of("return Option[getInfoText] : exists", "getInfoText",true),
                Arguments.of("return Option[setTrackNumber] : exists", "setTrackNumber",true),
                Arguments.of("return Option[getCDName] : exists", "getCDName",true),
                Arguments.of("return Option[setCDName] : exists", "setCDName",true),
                Arguments.of("return Option[getTrackNumber] : exists", "getTrackNumber",true)

        );
    }

    //Help-Functions
    public boolean checkOptionList(List<String> optionList,String optionName){
       if(optionList.contains(optionName)){
           return true;
       }
        return false;
    }

}