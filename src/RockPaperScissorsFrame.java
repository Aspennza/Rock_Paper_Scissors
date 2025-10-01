import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

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
        add(mainPnl);

        createRPSPnl();
        mainPnl.add(rpsPnl, BorderLayout.NORTH);

        //need to call the panel creation functions here
    }

    private void createRPSPnl()
    {
        rpsPnl = new JPanel();
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
    }

}
