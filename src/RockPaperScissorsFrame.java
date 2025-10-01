import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

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

        //need to call the panel creation functions here
    }

    private void createRPSPnl()
    {
        rpsPnl = new JPanel();

    }

}
