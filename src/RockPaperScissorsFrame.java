import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


//write javadoc
//create UML diagrams

/**
 * Creates an extension of the JFrame class called RockPaperScissorsFrame. Users can select either rock, paper, or scissors
 * and play against a series of algorithms based on the Strategy interface.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class RockPaperScissorsFrame extends javax.swing.JFrame
{
    //A JPanel containing every other GUI element in the JFrame
    JPanel mainPnl;

    //A JPanel containing a JLabel with the title of the game
    JPanel titlePnl;

    //A JPanel containing the Rock, Paper, and Scissors buttons and their ActionListener
    JPanel rpsPnl;

    //A JPanel that tracks and displays the number of wins, ties, losses, and games
    JPanel statsPnl;

    //A JPanel that displays the results of each individual game and what strategy the computer used
    JPanel resultsPnl;

    //A JLabel that displays the name of the game
    JLabel titleLbl;

    //A Font for styling the titleLbl
    Font titlePnlFont;

    //A JButton representing rock as the player's choice
    JButton rockBtn;

    //A JButton representing paper as the player's choice
    JButton paperBtn;

    //A JButton representing scissors as the player's choice
    JButton scissorsBtn;

    //A JButton for quitting the game and closing the JFrame
    JButton quitBtn;

    //An ImageIcon for the rock button
    ImageIcon rockIcon;

    //An ImageIcon for the paper button
    ImageIcon paperIcon;

    //An ImageIcon for the scissors button
    ImageIcon scissorsIcon;

    //An ImageIcon for the quit button
    ImageIcon quitIcon;

    //A String for storing the player's move choice
    String playerMove;

    //An int tracking how many times the player has played Rock
    int playerRockCount;

    //An int tracking how many times the player has played Paper
    int playerPaperCount;

    //An int tracking how many times the player has played Scissors
    int playerScissorsCount;

    //An int tracking how many times the computer has played Rock
    int compRockCount;

    //An int tracking how many times the computer has played Paper
    int compPaperCount;

    //An int tracking how many times the computer has played Scissors
    int compScissorsCount;

    //An int tracking how many times the computer has won
    int compWins;

    //An int tracking how many times the player has won
    int playWins;

    //An int tracking how many games were ties
    int ties;

    //An int tracking how many total games were played
    int totalGamesPlayed;

    //A String tracking the computer's last move
    String lastCompMove = "";

    //A String tracking the player's last move
    String lastPlayerMove = "";

    //A String tracking what strategy the computer used on a particular turn
    String compStrategy = "";

    //A JLabel for the playerWins text field
    JLabel playerWinsLbl;

    //A JLabel for the computerWins text field
    JLabel computerWinsLbl;

    //A JLabel for the ties text field
    JLabel tiesLbl;

    //A JLabel for the totalGamesPlayed text field
    JLabel totalGamesPlayedLbl;

    //A JTextField displaying how many times the player has won
    JTextField playerWinsTF;

    //A JTextField displaying how many times the computer has won
    JTextField computerWinsTF;

    //A JTextField displaying how many times the game has tied
    JTextField tiesTF;

    //A JTextField displaying how many games have been played
    JTextField totalGamesPlayedTF;

    //A JTextArea displaying the result of every game played
    JTextArea resultsTA;

    //A JScrollPane to allow the resultsTA to scroll
    JScrollPane scroller;

    //An instance of the computer's CheatStrategy class
    CheatStrategy cheatStrat = new CheatStrategy();

    //An instance of the computer's RandomStrategy class
    RandomStrategy randomStrat = new RandomStrategy();

    /**
     * A class implementing the Strategy interface. Determines the computer's move based on
     * the player's least used move and selects the move that beats it.
     * @author Zoe Aspenns aspennza@mail.uc.edu
     */
    class LeastUsed implements Strategy
    {
        /**
         * This method calculates the player's least used move and returns the move that beats it.
         * @param playerMove the player's selected move
         * @return A String representing Rock, Paper, or Scissors
         */
        @Override
        public String getMove(String playerMove)
        {
            int min = Math.min(playerRockCount, Math.min(playerPaperCount, playerScissorsCount));

            //This algorithm selects a move for the computer that will beat the player's least used move
            if(playerRockCount == min)
                return "P";
            else if (playerPaperCount == min)
                return "S";
            else
                return "R";
        }
    }


    //An instance of the LeastUsed class
    LeastUsed leastUsedStrategy = new LeastUsed();

    /**
     * A class implementing the Strategy interface. Determines the player's most used move and returns the move that will beat it.
     * @author Zoe Aspenns aspennza@mail.uc.edu
     */
    class MostUsed implements Strategy
    {
        /**
         * This method determines which move the player has used most and returns the move that beats it.
         * @param playerMove the player's selected move
         * @return a String representing rock, paper, or scissors
         */
        @Override
        public String getMove(String playerMove)
        {
            int max = Math.max(playerRockCount, Math.max(playerPaperCount, playerScissorsCount));

            //An algorithm that returns the computer's move based on the player's most used move
            if(playerRockCount == max)
                return "P";
            else if (playerPaperCount == max)
                return "S";
            else
                return "R";
        }
    }

    //An instance of the MostUsed class
    MostUsed mostUsedStrategy = new MostUsed();

    /**
     * A class implementing the Strategy interface. Determines the player's last used move and returns the same move.
     * @author Zoe Aspenns aspennza@mail.uc.edu
     */
    class LastUsed implements Strategy
    {
        //A String storing the computer's chosen move
        String compMove = "";

        /**
         * A method that determines whether the player has moved, and, if they have, sets the computer's move equal to the player's last move.
         * @param playerMove the player's chosen move
         * @return A String representing rock, paper, or scissors
         */
        @Override
        public String getMove(String playerMove)
        {
            //A random number generator
            Random gen = new Random();

            //An int that will hold a randomly generated index representing rock, paper, or scissors
            int rpsIndex = 0;

            //An algorithm for determining whether the player has moved yet; if yes, the computer's move is set equal to the player's last; if no, the computer's move is randomized
            if(!lastPlayerMove.isEmpty())
            {
                compMove = lastPlayerMove;
            } else
            {
                rpsIndex = gen.nextInt(3);

                //This algorithm determines the computer's move based on the random index generated
                switch (rpsIndex)
                {
                    case 0:
                        compMove = "R";
                        break;
                    case 1:
                        compMove = "P";
                        break;
                    case 2:
                        compMove = "S";
                        break;
                }
            }

            return compMove;
        }
    }

    //An instance of the LastUsed class
    LastUsed lastUsedStrategy = new LastUsed();

    /**
     * This method randomizes the strategy that the computer will use based on a randomly-generated probability.
     * @param playerMove the player's selected move
     * @return The computer's selected move
     */
    public String getComputerMove(String playerMove)
    {
        //A random number generator
        Random gen = new Random();

        //An integer representing a random probability
        int prob = gen.nextInt(100) + 1;

        //A String containing the computer's move choice
        String compMove = "";

        //An algorithm that determines the computer's strategy based on the probability generated
        if(prob <= 10)
        {
            compStrategy = "Cheat";
            compMove = cheatStrat.getMove(playerMove);
        } else if (prob <= 30)
        {
            compStrategy = "Least Used";
            compMove = leastUsedStrategy.getMove(playerMove);
        } else if (prob <= 50)
        {
            compStrategy = "Most Used";
            compMove = mostUsedStrategy.getMove(playerMove);
        } else if (prob <= 70)
        {
            compStrategy = "Last Used";
            compMove = lastUsedStrategy.getMove(playerMove);
        } else
        {
            compStrategy = "Random";
            compMove = randomStrat.getMove(playerMove);
        }

        //An algorithm that tracks the number of times the computer has used a specific move and the move it used last
        switch (compMove)
        {
            case "R":
                compRockCount++;
                lastCompMove = "R";
                break;
            case "P":
                compPaperCount++;
                lastCompMove = "P";
                break;
            case "S":
                compScissorsCount++;
                lastCompMove = "S";
                break;
        }

        return compMove;
    }

    /**
     * This method cross-references the player's move with the computer's move to determine which has won.
     * @param playerMove the player's chosen move
     */
    public void resolve(String playerMove)
    {
        //This String stores the computer's move
        String compMove = getComputerMove(playerMove);

        //This String stores the text that will be output depending on what win condition has occurred
        String result = "";

        resultsTA.append("Player: " + playerMove + "; Computer: " + compMove + "\n");

        //This algorithm identifies the player's move
        if(playerMove.equals("R"))
        {
            //This algorithm identifies the computer's move and determines what win condition has occurred
            if(compMove.equals("R"))
            {
                result = "Rock vs. Rock! It's a tie!";
                ties++;
                tiesTF.setText(ties + "");
            } else if (compMove.equals("P"))
            {
                result = "Paper covers Rock! Computer wins!";
                compWins++;
                computerWinsTF.setText(compWins + "");
            } else
            {
                result = "Rock breaks Scissors! Player wins!";
                playWins++;
                playerWinsTF.setText(playWins + "");
            }
        } else if (playerMove.equals("P"))
        {
            //This algorithm identifies the computer's move and determines what win condition has occurred
            if(compMove.equals("R"))
            {
                result = "Paper covers Rock! Player wins!";
                playWins++;
                playerWinsTF.setText(playWins + "");
            } else if (compMove.equals("P"))
            {
                result = "Paper vs. Paper! It's a tie!";
                ties++;
                tiesTF.setText(ties + "");
            } else
            {
                result = "Scissors cuts Paper! Computer wins!";
                compWins++;
                computerWinsTF.setText(compWins + "");
            }
        } else
        {
            //This algorithm identifies the computer's move and determines what win condition has occurred
            if(compMove.equals("R"))
            {
                result = "Rock breaks Scissors! Computer wins!";
                compWins++;
                computerWinsTF.setText(compWins + "");
            } else if (compMove.equals("P"))
            {
                result = "Scissors cuts Paper! Player wins!";
                playWins++;
                playerWinsTF.setText(playWins + "");
            } else
            {
                result = "Scissors vs. Scissors! It's a tie!";
                ties++;
                tiesTF.setText(ties + "");
            }
        }

        resultsTA.append(result + " (Computer Strategy: " + compStrategy + ")\n");
        totalGamesPlayed++;
        totalGamesPlayedTF.setText(totalGamesPlayed + "");
    }

    /**
     * This constructor determines the basic settings for the RockPaperScissorsFrame and also calls
     * all the methods that establish the individual panels in the frame.
     */
    public RockPaperScissorsFrame()
    {
        super("Rock Paper Scissors Game");

        //This Toolkit is used to find the screen size of the computer running the GUI
        Toolkit kit = Toolkit.getDefaultToolkit();

        //This Dimension stores the screen size
        Dimension screenSize = kit.getScreenSize();

        //This int stores the height of the screen
        int screenHeight = screenSize.height;

        //This int stores the width of the screen
        int screenWidth = screenSize.width;

        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4, 1));
        mainPnl.setBorder(new EmptyBorder(20, 20, 40, 20));
        add(mainPnl);

        createTitlePnl();
        mainPnl.add(titlePnl);

        createRPSPnl();
        mainPnl.add(rpsPnl);

        createResultsPnl();
        mainPnl.add(resultsPnl);

        createStatsPnl();
        mainPnl.add(statsPnl);

        setSize(screenWidth * 3/4, screenHeight * 3/4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rock Paper Scissors Game");
        setVisible(true);
    }

    /**
     * This method establishes the title panel and the JLabel that it contains
     */
    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Rock Paper Scissors Game");
        titlePnlFont = new Font("Serif", Font.BOLD, 48);
        titleLbl.setFont(titlePnlFont);

        titlePnl.add(titleLbl);
    }

    /**
     * This method establishes the rpsPnl, the buttons and images that it contains, and sets the logic for when each button is clicked.
     */
    private void createRPSPnl()
    {
        rpsPnl = new JPanel();
        rpsPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        rpsPnl.setLayout(new GridLayout(1, 4));

        rockIcon = new ImageIcon("src/rock.jpg");
        rockBtn = new JButton("Rock", rockIcon);
        rpsPnl.add(rockBtn);

        paperIcon = new ImageIcon("src/paper.jpg");
        paperBtn = new JButton("Paper", paperIcon);
        rpsPnl.add(paperBtn);

        scissorsIcon = new ImageIcon("src/scissors.jpg");
        scissorsBtn = new JButton("Scissors", scissorsIcon);
        rpsPnl.add(scissorsBtn);

        quitIcon = new ImageIcon("src/quit.jpg");
        quitBtn = new JButton("Quit", quitIcon);
        rpsPnl.add(quitBtn);

        playerMove = "";

        /**
         * This class creates an ActionListener that determines which button (Rock, Paper, or Scissors)
         * has been pressed and increments each counter accordingly. Also triggers the resolve method.
         * @author Zoe Aspenns aspennnza@mail.uc.edu
         */
        class RPSListener implements ActionListener
        {
            /**
             * This method determines which button has been pressed and increments the proper counter.
             * Also triggers the resolve method.
             * @param ae the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() instanceof JButton selectedButton)
                {
                    //This String stores the text of the selected button
                    String buttonText = selectedButton.getText();

                    //This algorithm identifies which button has been pressed and triggers the correct logic
                    switch (buttonText)
                    {
                        case "Rock":
                            playerRockCount++;
                            resolve("R");
                            lastPlayerMove = "R";
                            break;
                        case "Paper":
                            playerPaperCount++;
                            resolve("P");
                            lastPlayerMove = "P";
                            break;
                        case "Scissors":
                            playerScissorsCount++;
                            resolve("S");
                            lastPlayerMove = "S";
                            break;
                    }
                }
            }
        }

        //This RPSListener creates an instance of the RPSListener class
        RPSListener listener = new RPSListener();

        rockBtn.addActionListener(listener);
        paperBtn.addActionListener(listener);
        scissorsBtn.addActionListener(listener);

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
    }

    /**
     * This method establishes the resultsPnl and the JTextArea and JScrollPane that it contains
     */
    private void createResultsPnl()
    {
        resultsPnl = new JPanel();
        resultsPnl.setBorder(new EmptyBorder(10, 10, 10, 10));

        resultsTA = new JTextArea(10, 50);
        resultsTA.setEditable(false);
        scroller = new JScrollPane(resultsTA);

        resultsPnl.add(scroller);
    }

    /**
     * This method establishes the statsPnl, as well as the JLabels and JTextFields it contains
     */
    private void createStatsPnl()
    {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(2, 4));

        playerWinsLbl = new JLabel("Player Wins:");
        playerWinsLbl.setVerticalAlignment(SwingConstants.BOTTOM);
        statsPnl.add(playerWinsLbl);

        computerWinsLbl = new JLabel("Computer Wins:");
        computerWinsLbl.setVerticalAlignment(SwingConstants.BOTTOM);
        statsPnl.add(computerWinsLbl);

        tiesLbl = new JLabel("Ties:");
        tiesLbl.setVerticalAlignment(SwingConstants.BOTTOM);
        statsPnl.add(tiesLbl);

        totalGamesPlayedLbl = new JLabel("Total Games Played:");
        totalGamesPlayedLbl.setVerticalAlignment(SwingConstants.BOTTOM);
        statsPnl.add(totalGamesPlayedLbl);

        playerWinsTF = new JTextField(15);
        playerWinsTF.setEditable(false);
        playerWinsTF.setText(0 + "");
        statsPnl.add(playerWinsTF);

        computerWinsTF = new JTextField(15);
        computerWinsTF.setEditable(false);
        computerWinsTF.setText(0 + "");
        statsPnl.add(computerWinsTF);

        tiesTF = new JTextField(15);
        tiesTF.setEditable(false);
        tiesTF.setText(0 + "");
        statsPnl.add(tiesTF);

        totalGamesPlayedTF = new JTextField(15);
        totalGamesPlayedTF.setEditable(false);
        totalGamesPlayedTF.setText(0 + "");
        statsPnl.add(totalGamesPlayedTF);
    }
}
