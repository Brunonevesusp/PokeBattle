import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PokeBattleGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel selectionPanel;
    private JButton[] player1Buttons = new JButton[9];
    private JButton[] player2Buttons = new JButton[9];
    private JButton readyButton1;
    private JButton readyButton2;
    private boolean player1Ready = false;
    private boolean player2Ready = false;
    private boolean player1Selected = false;
    private boolean player2Selected = false;

    private JPanel battlePanel;
    private JPanel centralPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton attackButton;
    private JButton itemsButton;
    private JButton pokemonButton;
    private JButton quitButton;

    public PokeBattleGUI() {
        setTitle("PokeBattle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createSelectionPanel();
        createBattlePanel();

        mainPanel.add(selectionPanel, "Selection");
        mainPanel.add(battlePanel, "Battle");

        add(mainPanel);

        cardLayout.show(mainPanel, "Selection");
    }

    private void createSelectionPanel() {
        selectionPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Select Your Character"));
        selectionPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel charactersPanel = new JPanel(new GridLayout(1, 2));

        JPanel player1Panel = new JPanel(new BorderLayout());
        player1Panel.add(new JLabel("Player 1"), BorderLayout.NORTH);
        JPanel player1ButtonsPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            player1Buttons[i] = new JButton("Character " + (i + 1));
            int finalI = i;
            player1Buttons[i].addActionListener(e -> {
                player1Selected = true;
                highlightSelectedButton(player1Buttons, finalI);
            });
            player1ButtonsPanel.add(player1Buttons[i]);
        }
        player1Panel.add(player1ButtonsPanel, BorderLayout.CENTER);
        readyButton1 = new JButton("Ready");
        player1Panel.add(readyButton1, BorderLayout.SOUTH);

        JPanel player2Panel = new JPanel(new BorderLayout());
        player2Panel.add(new JLabel("Player 2"), BorderLayout.NORTH);
        JPanel player2ButtonsPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            player2Buttons[i] = new JButton("Character " + (i + 1));
            int finalI = i;
            player2Buttons[i].addActionListener(e -> {
                player2Selected = true;
                highlightSelectedButton(player2Buttons, finalI);
            });
            player2ButtonsPanel.add(player2Buttons[i]);
        }
        player2Panel.add(player2ButtonsPanel, BorderLayout.CENTER);
        readyButton2 = new JButton("Ready");
        player2Panel.add(readyButton2, BorderLayout.SOUTH);

        charactersPanel.add(player1Panel);
        charactersPanel.add(player2Panel);

        selectionPanel.add(charactersPanel, BorderLayout.CENTER);

        readyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1Selected) {
                    player1Ready = true;
                    readyButton1.setEnabled(false);
                    checkReady();
                } else {
                    JOptionPane.showMessageDialog(PokeBattleGUI.this, "Player 1 must select a character.");
                }
            }
        });

        readyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2Selected) {
                    player2Ready = true;
                    readyButton2.setEnabled(false);
                    checkReady();
                } else {
                    JOptionPane.showMessageDialog(PokeBattleGUI.this, "Player 2 must select a character.");
                }
            }
        });
    }

    private void highlightSelectedButton(JButton[] buttons, int index) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == index) {
                buttons[i].setBackground(Color.GREEN);
            } else {
                buttons[i].setBackground(null);
            }
        }
    }

    private void createBattlePanel() {
        battlePanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("PokeBattle"));
        battlePanel.add(titlePanel, BorderLayout.NORTH);

        centralPanel = new JPanel();
        battlePanel.add(centralPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel(new GridLayout(3, 1));
        rightPanel = new JPanel(new GridLayout(4, 1));
        attackButton = new JButton("Attack");
        itemsButton = new JButton("Items");
        pokemonButton = new JButton("Pokemon");
        quitButton = new JButton("Quit");
        rightPanel.add(attackButton);
        rightPanel.add(itemsButton);
        rightPanel.add(pokemonButton);
        rightPanel.add(quitButton);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);
        battlePanel.add(bottomPanel, BorderLayout.SOUTH);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLeftPanel("Attack");
            }
        });

        itemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLeftPanel("Items");
            }
        });

        pokemonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLeftPanel("Pokemon");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(PokeBattleGUI.this, "Player X wins! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    player1Ready = false;
                    player2Ready = false;
                    player1Selected = false;
                    player2Selected = false;
                    readyButton1.setEnabled(true);
                    readyButton2.setEnabled(true);
                    highlightSelectedButton(player1Buttons, -1);
                    highlightSelectedButton(player2Buttons, -1);
                    cardLayout.show(mainPanel, "Selection");
                } else {
                    System.exit(0);
                }
            }
        });
    }

    private void checkReady() {
        if (player1Ready && player2Ready) {
            cardLayout.show(mainPanel, "Battle");
        }
    }

    private void updateLeftPanel(String type) {
        leftPanel.removeAll();
        if (type.equals("Attack")) {
            for (int i = 0; i < 3; i++) {
                leftPanel.add(new JButton("Attack " + (i + 1)));
            }
        } else if (type.equals("Items")) {
            for (int i = 0; i < 3; i++) {
                leftPanel.add(new JButton("Item " + (i + 1)));
            }
        } else if (type.equals("Pokemon")) {
            for (int i = 0; i < 3; i++) {
                leftPanel.add(new JButton("Pokemon " + (i + 1)));
            }
        }
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    //testee
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PokeBattleGUI().setVisible(true);
            }
        });
    }
}
