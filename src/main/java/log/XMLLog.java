package log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLLog implements ILog {


    private final String arquivo = "log.xml";


    @Override
    public void escrever(String mensagem) {
        try {
            File file = new File(arquivo);
            Document doc;

            if (file.exists()) {
                // Carregar o arquivo XML existente
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(file);
            } else {
                // Criar um novo documento XML
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.newDocument();
                Element root = doc.createElement("logs");
                doc.appendChild(root);
            }

            // Adicionar nova entrada de log
            Element root = doc.getDocumentElement();
            Element log = doc.createElement("log");

            Element mensagemTag = doc.createElement("mensagem");
            mensagemTag.appendChild(doc.createTextNode(mensagem));
            log.appendChild(mensagemTag);

            Element timestampTag = doc.createElement("data_hora");
            timestampTag.appendChild(doc.createTextNode(java.time.LocalDateTime.now().toString()));
            log.appendChild(timestampTag);

            root.appendChild(log);

            // Escrever o documento atualizado no arquivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Log registrado com sucesso no arquivo XML.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar log no arquivo XML: " + e.getMessage(), e);
        }
    }


}
