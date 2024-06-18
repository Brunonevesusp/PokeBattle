import java.util.List;

public class Grass implements Pokemon {
    private String nome;
    private int hp;
    private int ataque;
    private int defesa;
    private List<Ataque> ataques;

    public Grass(String nome, int hp, int ataque, int defesa, List<Ataque> ataques) {
        this.nome = nome;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.ataques = ataques;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getAtaque() {
        return ataque;
    }

    @Override
    public int getDefesa() {
        return defesa;
    }

    @Override
    public List<Ataque> getAtaques() {
        return ataques;
    }

    @Override
    public void atacar(Pokemon alvo, Ataque ataque) {
        int dano = calcularDano(this, alvo, ataque);
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(this.getNome() + " usa " + ataque.getNome() + " em " + alvo.getNome() + " causando " + dano + " de dano.");
    }

    private int calcularDano(Pokemon atacante, Pokemon alvo, Ataque ataque) {
        double fator = 1.0;
        if (alvo instanceof Water) {
            fator = 2.0;
        } else if (alvo instanceof Fire) {
            fator = 0.5;
        }
        return (int) ((ataque.getPoder() * atacante.getAtaque() / alvo.getDefesa()) / 2 * fator);
    }

    @Override
    public String toString() {
        return nome + " (Tipo: Grama)";
    }

    @Override
    public String getDescricao() {
        return "Pokémon do tipo Grama têm fortes laços com a natureza e utilizam ataques baseados em plantas.";
    }

    @Override
    public String getVantagens() {
        return "Water";
    }

    @Override
    public String getDesvantagens() {
        return "Fire";
    }
}
