package entity;

import entity.*;
import log.ILog;
import services.GerenciadorDeDescontos;
import services.UsuarioLogadoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
    private double taxaEntrega;
    private LocalDate dataPedido;
    private Cliente cliente;
    private String codigoDeCupom;
    private final List<Item> itens = new ArrayList<>();
    private final Map<String, Double> codigosDeDesconto;

    public Pedido(double taxaEntrega, LocalDate dataPedido, Cliente cliente) {
        if (taxaEntrega < 0 || dataPedido == null || cliente == null) {
            throw new IllegalArgumentException("Valores inválidos para criar o pedido.");
        }

        this.taxaEntrega = taxaEntrega;
        this.dataPedido = dataPedido;
        this.cliente = cliente;

        this.codigosDeDesconto = new HashMap<>();
        this.codigosDeDesconto.put("DESC10", 0.10); // 10%
        this.codigosDeDesconto.put("DESC20", 0.20); // 20%
        this.codigosDeDesconto.put("DESC30", 0.30); // 30%
    }

    public void adicionarItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        itens.add(item);
    }

    public double getValorPedido() {
        double valorPedido = 0.0;
        for (Item item : itens) {
            valorPedido += item.getValorTotal();
        }
        return valorPedido + taxaEntrega;
    }

    public double calcularDesconto() {
        GerenciadorDeDescontos gerenciador = new GerenciadorDeDescontos(
                List.of(
                        new DescontoPorCodigo(),
                        new DescontoPorCategoria(),
                        new DescontoPorTipoDeItem()
                )
        );
        return gerenciador.calcularMelhorDesconto(this);
    }

    public double getValorFinal() {
        return getValorPedido() - calcularDesconto();
    }

    public void setCodigoDeCupom(String codigoDeCupom) {
        this.codigoDeCupom = codigoDeCupom;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public String getCodigoDeCupom() {
        return codigoDeCupom;
    }

    public Map<String, Double> getCodigosDeDesconto() {
        return Collections.unmodifiableMap(codigosDeDesconto);
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void registrarLog(ILog ilog) {
        String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
        String data = dataPedido.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String codigoPedido = hashCode() + "";
        String nomeCliente = cliente.getNome();

        String rawLog = String.format("NOME_USUARIO: %s | Data: %s | Hora: %s | codigo_pedido: %s | Nome de Operação: Calculo do valor total do pedido (%s) | Nome_Cliente: %s",
                nomeUsuario, data, hora, codigoPedido, this.getValorFinal(), nomeCliente);

        ilog.escrever(rawLog);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "taxaEntrega=" + taxaEntrega +
                ", dataPedido=" + dataPedido +
                ", cliente=" + cliente +
                ", itens=" + itens +
                '}';
    }
}
