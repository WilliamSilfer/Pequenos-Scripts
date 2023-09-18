import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento implements Serializable{
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horarioInicio; // Data e hora de início do evento
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horarioInicio, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horarioInicio = horarioInicio;
        this.descricao = descricao;
    }

    // Métodos getters
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para verificar se o evento já ocorreu
    public boolean eventoOcorreu() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(horarioInicio);
    }

    // Método para formatar a data e hora do evento como string
    public String formatarHorario() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return horarioInicio.format(formatter);
    }
}
