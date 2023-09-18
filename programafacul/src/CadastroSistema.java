import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;



public class CadastroSistema {
    private static void salvarEventosEmArquivo(List<Evento> eventos) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\walli\\IdeaProjects\\programafacul\\src\\events.data"))) {
            outputStream.writeObject(eventos);
            System.out.println("Eventos salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Pessoa> listaPessoas = new ArrayList<>();
        List<Evento> listaEventos = GerenciadorArquivo.carregarEventos("C:\\Users\\walli\\IdeaProjects\\programafacul\\src\\events.data");
        List<Evento> eventosConfirmados = new ArrayList<>();

        while (true) {
            System.out.println("===== Sistema de Cadastro de Eventos =====");
            System.out.println("1. Cadastrar Pessoa");
            System.out.println("2. Listar Pessoas Cadastradas");
            System.out.println("3. Cadastrar Evento");
            System.out.println("4. Listar Eventos Cadastrados");
            System.out.println("5. Participar de Evento");
            System.out.println("6. Listar Eventos Confirmados");
            System.out.println("7. Cancelar Participação em Evento");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da pessoa: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a idade da pessoa: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    Pessoa pessoa = new Pessoa(nome, idade);
                    listaPessoas.add(pessoa);
                    System.out.println("Pessoa cadastrada com sucesso!");
                    break;
                case 2:
                    System.out.println("Pessoas cadastradas:");
                    for (Pessoa p : listaPessoas) {
                        System.out.println("Nome: " + p.getNome() + ", Idade: " + p.getIdade());
                    }
                    break;
                case 3:
                    System.out.print("Digite o nome do evento: ");
                    String nomeEvento = scanner.nextLine();
                    System.out.print("Digite o endereço do evento: ");
                    String enderecoEvento = scanner.nextLine();
                    System.out.print("Digite a categoria do evento (festas, eventos esportivos, shows, etc.): ");
                    String categoriaEvento = scanner.nextLine();
                    System.out.print("Digite o horário do evento (formato dd/MM/yyyy HH:mm): ");
                    String horarioEventoStr = scanner.nextLine();
                    LocalDateTime horarioEvento = LocalDateTime.parse(horarioEventoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    System.out.print("Digite a descrição do evento: ");
                    String descricaoEvento = scanner.nextLine();

                    Evento evento = new Evento(nomeEvento, enderecoEvento, categoriaEvento, horarioEvento, descricaoEvento);
                    listaEventos.add(evento);
                    System.out.println("Evento cadastrado com sucesso!");

                    break;

                case 4:
                    // Ordenar os eventos por horário
                    listaEventos.sort(Comparator.comparing(Evento::getHorarioInicio));

                    // Listar eventos cadastrados e verificar se estão ocorrendo
                    LocalDateTime agora = LocalDateTime.now();
                    for (Evento ev : listaEventos) {
                        System.out.print("Nome: " + ev.getNome() + ", Horário: " + ev.formatarHorario());
                        if (ev.eventoOcorreu()) {
                            System.out.println(" (Evento já ocorreu)");
                        } else {
                            System.out.println();
                        }
                    }
                    break;
                case 5:
                    System.out.println("Eventos disponíveis para participação:");
                    for (int i = 0; i < listaEventos.size(); i++) {
                        Evento ev = listaEventos.get(i);
                        System.out.println(i + 1 + ". " + ev.getNome());
                    }
                    System.out.print("Escolha o número do evento que deseja participar (ou 0 para voltar): ");
                    int escolhaParticipar = scanner.nextInt();

                    if (escolhaParticipar >= 1 && escolhaParticipar <= listaEventos.size()) {
                        Evento eventoEscolhido = listaEventos.get(escolhaParticipar - 1);
                        eventosConfirmados.add(eventoEscolhido);
                        System.out.println("Você participará do evento: " + eventoEscolhido.getNome());
                    } else if (escolhaParticipar == 0) {
                        // Voltar ao menu principal
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;


                case 6:
                    if (eventosConfirmados.isEmpty()) {
                        System.out.println("Você não está confirmado em nenhum evento.");
                    } else {
                        System.out.println("Eventos em que você está confirmado:");
                        for (int i = 0; i < eventosConfirmados.size(); i++) {
                            Evento ev = eventosConfirmados.get(i);
                            System.out.println(i + 1 + ". " + ev.getNome());
                        }
                    }
                    break;

                case 7:
                    System.out.println("Eventos em que você está confirmado:");
                    for (int i = 0; i < eventosConfirmados.size(); i++) {
                        Evento ev = eventosConfirmados.get(i);
                        System.out.println(i + 1 + ". " + ev.getNome());
                    }
                    System.out.print("Escolha o número do evento que deseja cancelar (ou 0 para voltar): ");
                    int escolhaCancelar = scanner.nextInt();

                    if (escolhaCancelar >= 1 && escolhaCancelar <= eventosConfirmados.size()) {
                        Evento eventoCancelado = eventosConfirmados.remove(escolhaCancelar - 1);
                        System.out.println("Você cancelou a participação no evento: " + eventoCancelado.getNome());
                    } else if (escolhaCancelar == 0) {
                        // Voltar ao menu principal
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                    break;

                case 8:
                    System.out.println("Saindo do sistema...");
                    scanner.close();

                    GerenciadorArquivo.salvarEventos(listaEventos, "C:\\Users\\walli\\IdeaProjects\\programafacul\\src\\events.data");
                    System.exit(0);
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }
}
