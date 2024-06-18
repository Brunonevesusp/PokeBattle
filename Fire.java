import java.util.List;

public class Fire implements Pokemon {
    private String nome;
    private int hp;
    private int ataque;
    private int defesa;
    private List<Ataque> ataques;

    public Fire(String nome, int hp, int ataque, int defesa, List<Ataque> ataques) {
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
        if (alvo instanceof Grass) {
            fator = 2.0;
        } else if (alvo instanceof Water) {
            fator = 0.5;
        }
        return (int) ((ataque.getPoder() * atacante.getAtaque() / alvo.getDefesa()) / 2 * fator);
    }

    @Override
    public String toString() {
        return nome + " (Tipo: Fogo)";
    }

    @Override
    public String getDescricao() {
        return "Pokémon do tipo Fogo são conhecidos por sua força e habilidade em usar ataques de fogo.";
    }

    @Override
    public String getVantagens() {
        return "Grass";
    }

    @Override
    public String getDesvantagens() {
        return "Water";
    }
}
