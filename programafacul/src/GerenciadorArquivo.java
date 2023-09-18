import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivo {
    public static void salvarEventos(List<Evento> eventos, String events) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(events))) {
            outputStream.writeObject(eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Evento> carregarEventos(String events) {

        List<Evento> eventos = new ArrayList<>();
        String nomeArquivo = "C:\\Users\\walli\\IdeaProjects\\programafacul\\src\\events.data";

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            eventos = (List<Evento>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eventos;

    }
}
