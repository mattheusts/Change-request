package desconto;

import desconto.Pedido;

public interface Desconto {
    double calcular(Pedido pedido);
}