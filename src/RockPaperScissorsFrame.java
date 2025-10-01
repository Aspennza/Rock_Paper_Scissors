import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.tools.Tool;
import java.awt.*;

//may need to create fonts?
//write javadoc
//create UML diagrams

public class RockPaperScissorsFrame extends javax.swing.JFrame
{
    JPanel mainPnl;
    JPanel rpsPnl;
    JPanel statsPnl;
    JPanel resultsPnl;

    JLabel titleLbl; //I may remove this after reviewing the lectures

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;

    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;
    ImageIcon quitIcon;

    JLabel playerWinsLbl;
    JLabel computerWinsLbl;
    JLabel tiesLbl;
    JTextField playerWinsTF;
    JTextField computerWinsTF;
    JTextField tiesTF;

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
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setBorder(new EmptyBorder(20, 20, 40, 20));
        add(mainPnl);

        createRPSPnl();
        mainPnl.add(rpsPnl, BorderLayout.NORTH);

        createResultsPnl();
        mainPnl.add(resultsPnl, BorderLayout.CENTER);

        createStatsPnl();
        mainPnl.add(statsPnl, BorderLayout.SOUTH);

        setSize(screenWidth /2, screenHeight /2);
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

        //need to add ActionListeners
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
        statsPnl.setLayout(new GridLayout(2, 3));

        playerWinsLbl = new JLabel("Player Wins:");
        statsPnl.add(playerWinsLbl);

        computerWinsLbl = new JLabel("Computer Wins:");
        statsPnl.add(computerWinsLbl);

        tiesLbl = new JLabel("Ties:");
        statsPnl.add(tiesLbl);

        playerWinsTF = new JTextField(15);
        playerWinsTF.setEditable(false);
        statsPnl.add(playerWinsTF);

        computerWinsTF = new JTextField(15);
        computerWinsTF.setEditable(false);
        statsPnl.add(computerWinsTF);

        tiesTF = new JTextField(15);
        tiesTF.setEditable(false);
        statsPnl.add(tiesTF);
    }

}
