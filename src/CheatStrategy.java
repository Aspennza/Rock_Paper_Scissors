/**
 * Creates an implementation of the Strategy interface allowing the computer to
 * know what move the player selected and always choose the move that beats it.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class CheatStrategy implements Strategy
{
    /**
     * This method identifies the player's move and returns the move that will beat it.
     * @param playerMove the player's chosen move
     * @return the computer's move
     */
    @Override
    public String getMove(String playerMove)
    {
        //This String stores the computer's move
        String computerMove = "";

        //This algorithm identifies the player's move and chooses the computer a move that will always beat it
        switch (playerMove)
        {
            case "R":
                computerMove = "P";
                break;
            case "P":
                computerMove = "S";
                break;
            case "S":
                computerMove = "R";
                break;
            default:
                computerMove = "X";
                break;
        }
        return computerMove;
    }
}
