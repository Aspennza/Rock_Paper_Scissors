import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.tools.Tool;
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
    JPanel titlePnl;
    JPanel rpsPnl;
    JPanel statsPnl;
    JPanel resultsPnl;

    JLabel titleLbl;
    Font titlePnlFont;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;

    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;
    ImageIcon quitIcon;

    String playerMove;
    String prevPlayerMove;
    int playerRockCount;
    int playerPaperCount;
    int playerScissorsCount;

    String computerMove;

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

    public RockPaperScissorsFrame()
    {
        super("Rock Paper Scissors Game");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
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

    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Rock Paper Scissors Game");
        titlePnlFont = new Font("Serif", Font.BOLD, 36);
        titleLbl.setFont(titlePnlFont);
        titlePnl.add(titleLbl);
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
                            playerMove = "R";
                            playerRockCount++;
                            break;
                        case "Paper":
                            playerMove = "P";
                            playerPaperCount++;
                            break;
                        case "Scissors":
                            playerMove = "S";
                            playerScissorsCount++;
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

    private void createResultsPnl()
    {
        resultsPnl = new JPanel();
        resultsPnl.setBorder(new EmptyBorder(10, 10, 10, 10));

        resultsTA = new JTextArea(10, 50);
        resultsTA.setEditable(false);
        scroller = new JScrollPane(resultsTA);

        resultsPnl.add(scroller);
    }

    private void createStatsPnl()
    {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(2, 4));

        playerWinsLbl = new JLabel("Player Wins:");
        statsPnl.add(playerWinsLbl);

        computerWinsLbl = new JLabel("Computer Wins:");
        statsPnl.add(computerWinsLbl);

        tiesLbl = new JLabel("Ties:");
        statsPnl.add(tiesLbl);

        totalGamesPlayedLbl = new JLabel("Total Games Played:");
        statsPnl.add(totalGamesPlayedLbl);

        playerWinsTF = new JTextField(15);
        playerWinsTF.setEditable(false);
        statsPnl.add(playerWinsTF);

        computerWinsTF = new JTextField(15);
        computerWinsTF.setEditable(false);
        statsPnl.add(computerWinsTF);

        tiesTF = new JTextField(15);
        tiesTF.setEditable(false);
        statsPnl.add(tiesTF);

        totalGamesPlayedTF = new JTextField(15);
        totalGamesPlayedTF.setEditable(false);
        statsPnl.add(totalGamesPlayedTF);
    }

    public String resolve(String playerMove, String computerMove)
    {
        String result = "";

        if(playerMove.equals("R"))
        {
            if(computerMove.equals("R"))
            {

            }
        }
    }

    public class LeastUsed implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            String leastUsed = "";

            if (playerRockCount < playerPaperCount && playerRockCount < playerScissorsCount)
            {
                leastUsed = "R";
            } else if (playerPaperCount < playerRockCount && playerPaperCount < playerScissorsCount)
            {
                leastUsed = "P";
            } else
            {
                leastUsed = "S";
            }

            switch (leastUsed)
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
            }

            return computerMove;
        }
    }

    public class MostUsed implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            String mostUsed = "";

            if (playerRockCount > playerPaperCount && playerRockCount > playerScissorsCount)
            {
                mostUsed = "R";
            } else if (playerPaperCount > playerRockCount && playerPaperCount > playerScissorsCount)
            {
                mostUsed = "P";
            } else
            {
                mostUsed = "S";
            }

            switch (mostUsed)
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
            }

            return computerMove;
        }
    }

    public class LastUsed implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            Random gen = new Random();
            int rpsIndex = 0;

            if(playerMove != null)
            {
                computerMove = playerMove;
            } else
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
            }

            return computerMove;
        }
    }

}
