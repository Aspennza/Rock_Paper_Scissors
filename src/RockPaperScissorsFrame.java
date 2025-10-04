import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


//Return to implementing the resolve method for calculating who wins
//Return to Prof. Wulf's tuesday lecture at 20 mins in
//may need to create fonts?
//write javadoc
//create UML diagrams

public class RockPaperScissorsFrame extends javax.swing.JFrame
{
    JPanel mainPnl;
    JPanel rpsPnl;
    JPanel statsPnl;
    JPanel resultsPnl;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;

    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;
    ImageIcon quitIcon;

    String playerMove;
    String computerMove;

    int playerRockCount;
    int playerPaperCount;
    int playerScissorsCount;

    int compRockCount;
    int compPaperCount;
    int compScissorsCount;

    int compWins;
    int playWins;
    int ties;

    String lastCompMove = "";
    String lastPlayerMove = "";
    String compStrategy = "";

    JLabel playerWinsLbl;
    JLabel computerWinsLbl;
    JLabel tiesLbl;
    JLabel totalGamesPlayedLbl;

    JTextField playerWinsTF;
    JTextField computerWinsTF;
    JTextField tiesTF;
    JTextField totalGamesPlayedTF;

    JTextArea resultsTA;
    JScrollPane scroller;

    CheatStrategy cheatStrat = new CheatStrategy();
    RandomStrategy randomStrat = new RandomStrategy();

    public void resolve(String playerMove)
    {
        String compMove = getComputerMove(playerMove);
        String result = "";

        resultsTA.append("Player: " + playerMove + " Computer: " + compMove + "\n");

        if(playerMove.equals("R"))
        {
            if(computerMove.equals("R"))
            {
                result = "Rock vs. Rock! It's a tie!";
                ties++;
                tiesTF.setText(ties + "");
            } else if (computerMove.equals("P"))
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
            if(computerMove.equals("R"))
            {
                result = "Paper covers Rock! Player wins!";
                playWins++;
                playerWinsTF.setText(playWins + "");
            } else if (computerMove.equals("P"))
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
            if(computerMove.equals("R"))
            {
                result = "Rock breaks Scissors! Computer wins!";
                compWins++;
                computerWinsTF.setText(compWins + "");
            } else if (computerMove.equals("P"))
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

        resultsTA.append(result + " (" + compStrategy + ")\n");
    }

    class LeastUsed implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            int min = Math.min(playerRockCount, Math.min(playerPaperCount, playerScissorsCount));

            if(playerRockCount == min)
                return "P";
            else if (playerPaperCount == min)
                return "S";
            else
                return "R";
        }
    }
    LeastUsed leastUsedStrategy = new LeastUsed();

    class MostUsed implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            int max = Math.max(playerRockCount, Math.max(playerPaperCount, playerScissorsCount));

            if(playerRockCount == max)
                return "P";
            else if (playerPaperCount == max)
                return "S";
            else
                return "R";
        }
    }
    MostUsed mostUsedStrategy = new MostUsed();

    class LastUsed implements Strategy
    {
        String compMove = "";

        @Override
        public String getMove(String playerMove)
        {
            Random gen = new Random();
            int rpsIndex = 0;

            if(!lastPlayerMove.isEmpty())
            {
                compMove = lastPlayerMove;
            } else
            {
                rpsIndex = gen.nextInt(3);

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
    LastUsed lastUsedStrategy = new LastUsed();

    public RockPaperScissorsFrame()
    {
        super("Rock Paper Scissors Game");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(3, 1));
        mainPnl.setBorder(new EmptyBorder(20, 20, 40, 20));
        add(mainPnl);

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

        class RPSListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() instanceof JButton selectedButton)
                {
                    String buttonText = selectedButton.getText();

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

        RPSListener listener = new RPSListener();

        rockBtn.addActionListener(listener);
        paperBtn.addActionListener(listener);
        scissorsBtn.addActionListener(listener);

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
    }

    public String getComputerMove(String playerMove)
    {
        Random gen = new Random();
        int prob = gen.nextInt(100) + 1;

        String compMove = "";

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

    private void createResultsPnl()
    {
        resultsPnl = new JPanel();
        resultsPnl.setBorder(new EmptyBorder(10, 10, 10, 10));

        resultsTA = new JTextArea(13, 50);
        resultsTA.setEditable(false);
        scroller = new JScrollPane(resultsTA);

        resultsPnl.add(scroller);
    }

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
