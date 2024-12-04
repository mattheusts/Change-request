package org.example;

import entity.Cliente;
import entity.Item;
import entity.Pedido;
import log.DBLog;
import log.ILog;
import log.JSONLog;
import log.XMLLog;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João Silva", "567.902.920-04", "prata");
        Pedido pedido = new Pedido(10.0, LocalDate.now(), cliente);

        Item item1 = new Item("Livro de Java", "Educação", 100, 1);
        Item item2 = new Item("Jogo de Tabuleiro", "Lazer", 150, 1);
        Item item3 = new Item("Sanduíche", "Alimentação", 15, 2);

        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);
        pedido.adicionarItem(item3);

        pedido.setCodigoDeCupom("DESC10");

        pedido.registrarLog(new XMLLog());
        pedido.registrarLog(new DBLog());
        pedido.registrarLog(new JSONLog());
    }
}