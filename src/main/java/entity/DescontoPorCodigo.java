package entity;

import desconto.Desconto;
import desconto.Pedido;

import java.util.Map;

public class DescontoPorCodigo implements Desconto {
    @Override
    public double calcular(Pedido pedido) {
        String codigo = pedido.getCodigoDeCupom();
        Map<String, Double> codigos = pedido.getCodigosDeDesconto();
        if (codigo != null && codigos.containsKey(codigo)) {
            return pedido.getValorPedido() * codigos.get(codigo);
        }
        return 0.0;
    }
}