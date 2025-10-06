import java.util.Random;

/**
 * Creates an implementation of the Strategy interface called RandomStrategy. Randomizes the
 * computer's chosen move based on a randomly-generated integer.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class RandomStrategy implements Strategy
{
    //A random number generator
    Random gen = new Random();

    //A String containing the computer's move
    String computerMove = "";

    //An int that stores the randomly generated integer
    int rpsIndex = 0;

    /**
     * This method randomly generates an int value representing rock, paper, or scissors,
     * and returns the computer's chosen move accordingly.
     * @param playerMove the player's chosen move
     * @return a String with the computer's chosen move
     */
    @Override
    public String getMove(String playerMove)
    {
        rpsIndex = gen.nextInt(3);

        //This algorithm determines which move the computer picks based on the index generated
        switch (rpsIndex)
        {
            case 0:
                computerMove = "R";
                break;
            case 1:
                computerMove = "P";
                break;
            case 2:
                computerMove = "S";
                break;
        }
        return computerMove;
    }
}
