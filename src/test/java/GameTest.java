import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }

    private void assertIllegalArgument(String guessNumber) {
        try{
            game.guess(guessNumber);
            fail();
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    @Test
    void returnSolvedResultIfMatchedNumber() {
        generateQunestion("123");
        assertMatchNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolvedResultIfUnmatchedNumber() {
        generateQunestion("123");
        assertMatchNumber(game.guess("456"), false, 0, 0);
    }

    @Test
    void returnSolvedResultIfSomeMatchedNumber() {
        generateQunestion("123");
        assertMatchNumber(game.guess("120"), false, 2, 0);
        assertMatchNumber(game.guess("061"), false, 0, 1);
        assertMatchNumber(game.guess("136"), false, 1, 1);
    }

    private void generateQunestion(String questionNumber) {
        game.question = questionNumber;
    }

    private void assertMatchNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }
}