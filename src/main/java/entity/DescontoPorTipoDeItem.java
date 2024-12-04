package entity;

import desconto.Desconto;
import desconto.Pedido;

public class DescontoPorTipoDeItem implements Desconto {
    @Override
    public double calcular(Pedido pedido) {
        double desconto = 0.0;
        for (Item item : pedido.getItens()) {
            switch (item.getTipo().toLowerCase()) {
                case "alimentação":
                    desconto += item.getValorTotal() * 0.05;
                    break;
                case "educação":
                    desconto += item.getValorTotal() * 0.20;
                    break;
                case "lazer":
                    desconto += item.getValorTotal() * 0.15;
                    break;
            }
        }
        return desconto;
    }
}
