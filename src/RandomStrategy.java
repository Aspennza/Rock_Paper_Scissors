import java.util.Random;

public class RandomStrategy implements Strategy
{
    Random gen = new Random();
    String computerMove = "";
    int rpsIndex = 0;

    @Override
    public String getMove(String playerMove)
    {
        rpsIndex = gen.nextInt(3);

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
