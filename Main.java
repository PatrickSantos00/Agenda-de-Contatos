import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contato> contatos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o modo do programa:");
        System.out.println("1 - Interface gráfica");
        System.out.println("2 - Terminal");
        System.out.print("Opção: ");
        
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            usarInterfaceGrafica();
        } else if (escolha == 2) {
            usarTerminal(scanner);
        } else {
            System.out.println("Opção inválida.");
        }
        
        scanner.close();
    }

    private static void usarInterfaceGrafica() {
        int opcao;
        do {
            String menu = """
                    === Agenda de Contatos ===
                    1 - Adicionar contato
                    2 - Visualizar contatos
                    3 - Editar contato
                    4 - Excluir contato
                    5 - Sair
                    Escolha uma opção:
                    """;
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break;
            opcao = Integer.parseInt(input);

            switch (opcao) {
                case 1 -> adicionarContatoGUI();
                case 2 -> visualizarContatosGUI();
                case 3 -> editarContatoGUI();
                case 4 -> excluirContatoGUI();
                case 5 -> JOptionPane.showMessageDialog(null, "Encerrando a agenda...");
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void adicionarContatoGUI() {
        String nome = JOptionPane.showInputDialog("Nome do contato:");
        String telefone = JOptionPane.showInputDialog("Número de telefone:");

        if (nome != null && telefone != null) {
            Contato novoContato = new Contato(nome, telefone);
            contatos.add(novoContato);
            JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso!");
        }
    }

    private static void visualizarContatosGUI() {
        if (contatos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A agenda está vazia.");
        } else {
            StringBuilder lista = new StringBuilder("--- Lista de Contatos ---\n");
            for (int i = 0; i < contatos.size(); i++) {
                lista.append(i + 1).append(". ").append(contatos.get(i)).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }

    private static void editarContatoGUI() {
        visualizarContatosGUI();
        if (contatos.isEmpty()) return;

        String indiceStr = JOptionPane.showInputDialog("Digite o número do contato que deseja editar:");
        int indice = Integer.parseInt(indiceStr) - 1;

        if (indice >= 0 && indice < contatos.size()) {
            Contato contato = contatos.get(indice);

            String novoNome = JOptionPane.showInputDialog("Novo nome (deixe em branco para manter o atual):", contato.getNome());
            String novoTelefone = JOptionPane.showInputDialog("Novo telefone (deixe em branco para manter o atual):", contato.getTelefone());

            if (novoNome != null && !novoNome.isEmpty()) {
                contato.setNome(novoNome);
            }
            if (novoTelefone != null && !novoTelefone.isEmpty()) {
                contato.setTelefone(novoTelefone);
            }

            JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Contato não encontrado.");
        }
    }

    private static void excluirContatoGUI() {
        visualizarContatosGUI();
        if (contatos.isEmpty()) return;

        String indiceStr = JOptionPane.showInputDialog("Digite o número do contato que deseja excluir:");
        int indice = Integer.parseInt(indiceStr) - 1;

        if (indice >= 0 && indice < contatos.size()) {
            contatos.remove(indice);
            JOptionPane.showMessageDialog(null, "Contato excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Contato não encontrado.");
        }
    }

    private static void usarTerminal(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Agenda de Contatos ===");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Visualizar contatos");
            System.out.println("3 - Editar contato");
            System.out.println("4 - Excluir contato");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarContato(scanner);
                case 2 -> visualizarContatos();
                case 3 -> editarContato(scanner);
                case 4 -> excluirContato(scanner);
                case 5 -> System.out.println("Encerrando a agenda...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void adicionarContato(Scanner scanner) {
        System.out.print("Nome do contato: ");
        String nome = scanner.nextLine();
        System.out.print("Número de telefone: ");
        String telefone = scanner.nextLine();

        Contato novoContato = new Contato(nome, telefone);
        contatos.add(novoContato);
        System.out.println("Contato adicionado com sucesso!");
    }

    private static void visualizarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("A agenda está vazia.");
        } else {
            System.out.println("\n--- Lista de Contatos ---");
            for (int i = 0; i < contatos.size(); i++) {
                System.out.println((i + 1) + ". " + contatos.get(i));
            }
        }
    }

    private static void editarContato(Scanner scanner) {
        visualizarContatos();
        if (contatos.isEmpty()) return;

        System.out.print("Digite o número do contato que deseja editar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= contatos.size()) {
            Contato contato = contatos.get(indice - 1);

            System.out.print("Novo nome (deixe em branco para manter o atual): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                contato.setNome(novoNome);
            }

            System.out.print("Novo telefone (deixe em branco para manter o atual): ");
            String novoTelefone = scanner.nextLine();
            if (!novoTelefone.isEmpty()) {
                contato.setTelefone(novoTelefone);
            }

            System.out.println("Contato atualizado com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void excluirContato(Scanner scanner) {
        visualizarContatos();
        if (contatos.isEmpty()) return;

        System.out.print("Digite o número do contato que deseja excluir: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= contatos.size()) {
            contatos.remove(indice - 1);
            System.out.println("Contato excluído com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
}

class Contato {
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone;
    }
}
