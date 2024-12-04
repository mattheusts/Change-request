package entity;


import desconto.Desconto;

public class DescontoPorCategoria implements Desconto {
    @Override
    public double calcular(Pedido pedido) {
        String categoria = pedido.getCliente().getTipo();
        switch (categoria.toLowerCase()) {
            case "ouro":
                return pedido.getValorPedido() * 0.30;
            case "prata":
                return pedido.getValorPedido() * 0.20;
            case "bronze":
                return pedido.getValorPedido() * 0.10;
            default:
                return 0.0;
        }
    }
}