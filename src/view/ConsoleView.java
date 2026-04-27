package view;

import controller.SistemaController;
import model.Morador;

import java.util.Scanner;

public class ConsoleView {

    private SistemaController controller = new SistemaController();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\n=== Vizinhança Conectada ===");
            System.out.println("1 - Ver avisos recentes");
            System.out.println("2 - Enviar novo aviso");
            System.out.println("3 - Cadastrar Morador");
            System.out.println("4 - Meus avisos");
            System.out.println("5 - Estatísticas");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    listarAvisos();
                    break;

                case 2:
                    enviarAviso();
                    break;

                case 3:
                    cadastrarMorador();
                    break;

                case 4:
                    meusAvisos();
                    break;

                case 5:
                    estatisticas();
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 6);
    }

    // =========================
    // FUNCIONALIDADES
    // =========================

    private void cadastrarMorador() {
        System.out.println("\n=== CADASTRAR MORADOR ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Morador m = controller.cadastrarMorador(nome, endereco, telefone, email);

        System.out.println("Morador cadastrado! ID: " + m.getId());
    }

    private void enviarAviso() {
        System.out.println("\n=== ENVIAR AVISO ===");

        System.out.print("Seu ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Morador morador = controller.buscarMorador(id);

        if (morador == null) {
            System.out.println("Morador não encontrado!");
            return;
        }

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.println("Urgência:");
        System.out.println("1 - VERDE");
        System.out.println("2 - AMARELO");
        System.out.println("3 - VERMELHO");

        int u = sc.nextInt();
        sc.nextLine();

        String urgencia = switch (u) {
            case 1 -> "VERDE";
            case 2 -> "AMARELO";
            case 3 -> "VERMELHO";
            default -> "VERDE";
        };

        System.out.print("Local: ");
        String local = sc.nextLine();

        controller.enviarAviso(morador, titulo, descricao, urgencia, local);

        System.out.println("Aviso enviado com sucesso!");
    }

    private void listarAvisos() {
        System.out.println("\n=== AVISOS RECENTES ===");
        controller.listarAvisos();
    }

    private void meusAvisos() {
        System.out.print("\nSeu ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("=== MEUS AVISOS ===");
        controller.listarAvisosPorMorador(id);
    }

    private void estatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        controller.gerarEstatisticas();
    }
}