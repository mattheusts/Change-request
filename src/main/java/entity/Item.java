package entity;

public class Item {
    private String nome;
    private String tipo;
    private double precoUnitario;
    private int quantidade;

    public Item(String nome, String tipo, double precoUnitario, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidade=" + quantidade +
                ", valorTotal=" + getValorTotal() +
                '}';
    }
}
