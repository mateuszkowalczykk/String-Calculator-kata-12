import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    void should_return_zero_for_empty_string() {
        Assertions.assertThat(Calculator.add("")).isEqualTo(0);
    }

    @Test
    void should_return_number_for_one_number() {
        Assertions.assertThat(Calculator.add("5")).isEqualTo(5);
    }
//
    @Test
    void should_return_sum_of_two_numbers() {
        Assertions.assertThat(Calculator.add("2,3")).isEqualTo(5);
    }
//
    @Test
    void should_return_sum_of_unknown_amount_of_numbers() {
        Assertions.assertThat(Calculator.add("1,2,3")).isEqualTo(6);
    }
//
    @Test
    void should_return_sum_of_numbers_split_by_new_lines_and_commas() {
        Assertions.assertThat(Calculator.add("1,2\n3")).isEqualTo(6);
    }
//
    @Test
    void should_return_sum_of_numbers_split_by_custom_delimiter() {
        Assertions.assertThat(Calculator.add("//;\n1;2;3")).isEqualTo(6);
    }
//
    @Test
    void should_throw_exception_for_comma_next_to_delimiter() {
        Assertions.assertThatThrownBy(() ->Calculator.add("1,\n2"))
            .isInstanceOf(NumberFormatException.class);
    }
    @Test
    void should_throw_exception_for_negatives() {
        Assertions.assertThatThrownBy(() ->Calculator.add("1,-2,-3,4,-5"))
            .hasMessageContaining("negatives not allowed")
            .hasMessageContaining("-2 -3 -5");
    }
}
