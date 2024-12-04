package desconto;

import entity.Pedido;

public interface Desconto {
    double calcular(entity.Pedido pedido);
}