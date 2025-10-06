/**
 * Creates an interface for establishing different game strategies. Establishes
 * a method for move selection.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public interface Strategy
{
    /**
     * Provides the framework for a method for choosing a move based on the other player's move.
     * @param playerMove the player's chosen move
     * @return a String value representing the computer's chosen move
     */
    String getMove(String playerMove);
}
