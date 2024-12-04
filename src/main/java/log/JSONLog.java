package log;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONLog implements ILog {
    private final String arquivo = "log.json";

    @Override
    public void escrever(String mensagem) {
        JSONObject logEntry = new JSONObject();
        logEntry.put("mensagem", mensagem);

        try (FileWriter writer = new FileWriter(arquivo, true)) {
            writer.write(logEntry.toJSONString() + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }
    }
}