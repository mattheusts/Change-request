package services;

import desconto.Desconto;
import desconto.Pedido;

import java.util.List;

public class GerenciadorDeDescontos {
    private final List<Desconto> descontos;

    public GerenciadorDeDescontos(List<Desconto> descontos) {
        this.descontos = descontos;
    }

    public double calcularMelhorDesconto(Pedido pedido) {
        double maiorDesconto = 0.0;
        for (Desconto desconto : descontos) {
            maiorDesconto = Math.max(maiorDesconto, desconto.calcular(pedido));
        }
        return maiorDesconto;
    }
}