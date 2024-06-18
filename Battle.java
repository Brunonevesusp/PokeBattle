import java.util.Random;

public class Battle {
    private Pokemon[] pokemonJogador1;
    private Pokemon[] pokemonJogador2;
    private Pokemon[] pokemonsIniciais;
    private Random random;

    public Battle(Pokemon[] pokemonJogador1, Pokemon[] pokemonJogador2, Pokemon[] pokemonsIniciais) {
        this.pokemonJogador1 = pokemonJogador1;
        this.pokemonJogador2 = pokemonJogador2;
        this.pokemonsIniciais = pokemonsIniciais;
        this.random = new Random();
    }

    public void iniciar() {
        System.out.println("A batalha começou!");

        Pokemon p1 = pokemonsIniciais[0];
        Pokemon p2 = pokemonsIniciais[1];
        System.out.println("\nBatalha: " + p1.getNome() + " vs " + p2.getNome());

        while (p1.getHp() > 0 && p2.getHp() > 0) {
            turno(p1, p2);
            if (p2.getHp() > 0) {
                turno(p2, p1);
            }
        }

        if (p1.getHp() <= 0) {
            System.out.println(p1.getNome() + " foi derrotado!");
        }
        if (p2.getHp() <= 0) {
            System.out.println(p2.getNome() + " foi derrotado!");
        }
    }

    private void turno(Pokemon atacante, Pokemon defensor) {
        Ataque ataque = atacante.getAtaques().get(random.nextInt(atacante.getAtaques().size()));
        System.out.println(atacante.getNome() + " usa " + ataque.getNome() + "!");

        if (random.nextInt(100) < ataque.getAcuracia()) {
            atacante.atacar(defensor, ataque);
            System.out.println(defensor.getNome() + " agora tem " + defensor.getHp() + " de HP.");
        } else {
            System.out.println(atacante.getNome() + " errou o ataque!");
        }
    }
}
