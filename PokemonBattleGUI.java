import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class PokemonBattleGUI extends JFrame {
    private JComboBox<Pokemon> jogador1ComboBox1;
    private JComboBox<Pokemon> jogador1ComboBox2;
    private JComboBox<Pokemon> jogador2ComboBox1;
    private JComboBox<Pokemon> jogador2ComboBox2;
    private JComboBox<Pokemon> jogador1InicialComboBox;
    private JComboBox<Pokemon> jogador2InicialComboBox;
    private JLabel descricaoLabel;
    private JLabel vantagensLabel;
    private JLabel desvantagensLabel;
    private JComboBox<Item> jogador1ItemsComboBox;
    private JComboBox<Item> jogador2ItemsComboBox;

    
    private List<Pokemon> todosPokemons = Arrays.asList(
            new Fire("Charmander", 100, 52, 43, Arrays.asList(new Ataque("Chama", 40, 100), new Ataque("Lança-Chamas", 90, 85))),
            new Fire("Vulpix", 90, 41, 40, Arrays.asList(new Ataque("Chama", 40, 100), new Ataque("Lança-Chamas", 90, 85))),
            new Fire("Flareon", 110, 65, 60, Arrays.asList(new Ataque("Chama", 40, 100), new Ataque("Lança-Chamas", 90, 85))),
            new Water("Squirtle", 100, 48, 65, Arrays.asList(new Ataque("Jato de Água", 40, 100), new Ataque("Hidro Bomba", 90, 85))),
            new Water("Psyduck", 90, 52, 48, Arrays.asList(new Ataque("Jato de Água", 40, 100), new Ataque("Hidro Bomba", 90, 85))),
            new Water("Vaporeon", 120, 55, 65, Arrays.asList(new Ataque("Jato de Água", 40, 100), new Ataque("Hidro Bomba", 90, 85))),
            new Grass("Bulbasaur", 100, 49, 49, Arrays.asList(new Ataque("Folha Navalha", 45, 100), new Ataque("Chicote de Vinha", 45, 100))),
            new Grass("Oddish", 90, 50, 55, Arrays.asList(new Ataque("Folha Navalha", 45, 100), new Ataque("Chicote de Vinha", 45, 100))),
            new Grass("Leafeon", 110, 60, 65, Arrays.asList(new Ataque("Folha Navalha", 45, 100), new Ataque("Chicote de Vinha", 45, 100)))
    );

    public PokemonBattleGUI() {
        setTitle("Batalha Pokémon");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        jogador1ComboBox1 = new JComboBox<>(todosPokemons.toArray(new Pokemon[0]));
        jogador1ComboBox2 = new JComboBox<>(todosPokemons.toArray(new Pokemon[0]));
        jogador2ComboBox1 = new JComboBox<>(todosPokemons.toArray(new Pokemon[0]));
        jogador2ComboBox2 = new JComboBox<>(todosPokemons.toArray(new Pokemon[0]));
        jogador1InicialComboBox = new JComboBox<>();
        jogador2InicialComboBox = new JComboBox<>();
        descricaoLabel = new JLabel();
        vantagensLabel = new JLabel();
        desvantagensLabel = new JLabel();

        jogador1ComboBox1.addActionListener(new PokemonSelectionListener());
        jogador1ComboBox2.addActionListener(new PokemonSelectionListener());
        jogador2ComboBox1.addActionListener(new PokemonSelectionListener());
        jogador2ComboBox2.addActionListener(new PokemonSelectionListener());



// Inside your constructor or initialization method
jogador1ItemsComboBox = new JComboBox<>(Item.TODOS_ITENS.toArray(new Item[0]));
jogador2ItemsComboBox = new JComboBox<>(Item.TODOS_ITENS.toArray(new Item[0]));


        panel.add(new JLabel("Jogador 1 - Pokémon 1:"));
        panel.add(jogador1ComboBox1);
        panel.add(new JLabel("Jogador 1 - Pokémon 2:"));
        panel.add(jogador1ComboBox2);
        panel.add(new JLabel("Jogador 1 - Pokémon Inicial:"));
        panel.add(jogador1InicialComboBox);

        panel.add(new JLabel("Jogador 2 - Pokémon 1:"));
        panel.add(jogador2ComboBox1);
        panel.add(new JLabel("Jogador 2 - Pokémon 2:"));
        panel.add(jogador2ComboBox2);
        panel.add(new JLabel("Jogador 2 - Pokémon Inicial:"));
        panel.add(jogador2InicialComboBox);

        panel.add(new JLabel("Descrição:"));
        panel.add(descricaoLabel);
        panel.add(new JLabel("Vantagens:"));
        panel.add(vantagensLabel);
        panel.add(new JLabel("Desvantagens:"));
        panel.add(desvantagensLabel);

        JButton iniciarBatalhaButton = new JButton("Iniciar Batalha");
        iniciarBatalhaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarBatalha();
            }
        });
        panel.add(iniciarBatalhaButton);

        add(panel);
        setVisible(true);
    }

    private class PokemonSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<Pokemon> comboBox = (JComboBox<Pokemon>) e.getSource();
            Pokemon selecionado = (Pokemon) comboBox.getSelectedItem();

            if (comboBox == jogador1ComboBox1 || comboBox == jogador1ComboBox2) {
                jogador1InicialComboBox.removeAllItems();
                jogador1InicialComboBox.addItem((Pokemon) jogador1ComboBox1.getSelectedItem());
                jogador1InicialComboBox.addItem((Pokemon) jogador1ComboBox2.getSelectedItem());
            }

            if (comboBox == jogador2ComboBox1 || comboBox == jogador2ComboBox2) {
                jogador2InicialComboBox.removeAllItems();
                jogador2InicialComboBox.addItem((Pokemon) jogador2ComboBox1.getSelectedItem());
                jogador2InicialComboBox.addItem((Pokemon) jogador2ComboBox2.getSelectedItem());
            }

            if (selecionado != null) {
                descricaoLabel.setText(selecionado.getDescricao());
                vantagensLabel.setText("Vantagens: " + selecionado.getVantagens());
                desvantagensLabel.setText("Desvantagens: " + selecionado.getDesvantagens());
            }
        }
    }

    private void iniciarBatalha() {
        Pokemon[] pokemonJogador1 = {(Pokemon) jogador1ComboBox1.getSelectedItem(), (Pokemon) jogador1ComboBox2.getSelectedItem()};
        Pokemon[] pokemonJogador2 = {(Pokemon) jogador2ComboBox1.getSelectedItem(), (Pokemon) jogador2ComboBox2.getSelectedItem()};
        Pokemon[] pokemonsIniciais = {(Pokemon) jogador1InicialComboBox.getSelectedItem(), (Pokemon) jogador2InicialComboBox.getSelectedItem()};

        Battle batalha = new Battle(pokemonJogador1, pokemonJogador2, pokemonsIniciais);
        batalha.iniciar();
    }
}
