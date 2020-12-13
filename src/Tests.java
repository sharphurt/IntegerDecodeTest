import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @Test
    public void testWithCorrectNummber() {
        assert 12 == Integer.decode("12");
        assert 012 == Integer.decode("012");
        assert 0x56 == Integer.decode("0x56");
        assert 0x10 == Integer.decode("#10");
    }

    @Test
    public void testWithNegativeNumber() {
        assert -12 == Integer.decode("-12");
        assert -012 == Integer.decode("-012");
        assert -0x56 == Integer.decode("-0x56");
        assert -0x10 == Integer.decode("-#10");
    }

    @Test
    public void testWith16RadixNumber() {
        assert 0X12 == Integer.decode("0X12");
        assert 0xAF == Integer.decode("0xAF");
        assert 0x04 == Integer.decode("0X04");
        assert 0X34 == Integer.decode("#34");
    }

    @Test
    public void testWith8RadixNumber() {
        assert 023 == Integer.decode("023");
        assert 034 == Integer.decode("034");
        assert -043 == Integer.decode("-043");
    }

    @ParameterizedTest
    @ValueSource(strings = {"#-02", "0x-18", "0-55"})
    public void testWrongCharacterPosition(String value) {
        var exception = assertThrows(NumberFormatException.class, () -> Integer.decode(value));
        assert exception.getMessage().equals("Sign character in wrong position");
    }

    @Test
    public void testMinMaxCases() {
        assert Integer.MIN_VALUE == Integer.decode(String.valueOf(Integer.MIN_VALUE));
        assert Integer.MAX_VALUE == Integer.decode(String.valueOf(Integer.MAX_VALUE));
    }

    @Test
    public void testWithEmptyString() {
        var exception = assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        assert "Zero length string".equals(exception.getMessage());
    }
}