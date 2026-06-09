package view;

import controller.SistemaController;
import model.Aviso;
import model.Morador;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private SistemaController controller = new SistemaController();
    private Scanner sc = new Scanner(System.in);
    private Morador moradorLogado = null;

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\n=== Vizinhança Conectada ===");

            if (moradorLogado != null) {
                System.out.println("👤 Logado como: " + moradorLogado.getNome() + " (ID " + moradorLogado.getId() + ")");
                controller.exibirContadorNaoLidas(moradorLogado.getId());
            }

            System.out.println("1 - Ver avisos recentes");
            System.out.println("2 - Enviar aviso geral");
            System.out.println("3 - Cadastrar Morador");
            System.out.println("4 - Meus avisos enviados");
            System.out.println("5 - Estatísticas");
            System.out.println("6 - Selecionar meu perfil (login simples)");
            System.out.println("7 - Listar moradores cadastrados");
            System.out.println("8 - Filtrar avisos por bairro");
            System.out.println("9 - Excluir um aviso meu");
            System.out.println("10 - Editar um aviso meu");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> listarAvisos();
                case 2 -> enviarAviso();
                case 3 -> cadastrarMorador();
                case 4 -> meusAvisos();
                case 5 -> estatisticas();
                case 6 -> selecionarPerfil();
                case 7 -> listarMoradores();
                case 8 -> filtrarPorBairro();
                case 9 -> excluirMeuAviso();
                case 10 -> editarMeuAviso();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // =========================
    // FUNCIONALIDADES
    // =========================

    private void cadastrarMorador() {
        System.out.println("\n=== CADASTRAR MORADOR ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        System.out.print("Bairro: ");
        String bairro = sc.nextLine();

        System.out.print("Rua: ");
        String rua = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Morador m = controller.cadastrarMorador(nome, bairro, rua, telefone, email, senha);
        System.out.println("✅ Morador cadastrado! ID: " + m.getId());
        System.out.println("   Endereço: " + m.getEndereco());
    }

    private void enviarAviso() {
        System.out.println("\n=== ENVIAR AVISO GERAL ===");

        Morador morador = obterMoradorAtual();
        if (morador == null) return;

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        String titulo = lerTitulo();
        String descricao = lerDescricao();
        int tipo = lerTipo();
        String urgencia = lerUrgencia();

        System.out.print("Bairro afetado: ");
        String bairro = sc.nextLine();

        System.out.print("Rua (ou área): ");
        String rua = sc.nextLine();

        System.out.print("Número (opcional, Enter para pular): ");
        String numero = sc.nextLine().trim();
        String ruaFinal = numero.isEmpty() ? rua : rua + ", " + numero;

        controller.enviarAviso(morador, senha, titulo, descricao, urgencia, bairro, ruaFinal, tipo);
    }

    private void listarAvisos() {
        System.out.println("\n=== AVISOS RECENTES ===");
        controller.listarAvisos();
    }

    private void meusAvisos() {
        Morador m = obterMoradorAtual();
        if (m == null) return;

        System.out.println("=== MEUS AVISOS ENVIADOS ===");
        controller.listarAvisosPorMorador(m.getId());
    }

    private void estatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        controller.gerarEstatisticas();
    }

    private void selecionarPerfil() {
        System.out.println("\n=== SELECIONAR PERFIL ===");

        System.out.print("Seu ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Morador m = controller.buscarMorador(id);
        if (m == null) { System.out.println("❌ Morador não encontrado."); return; }

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (!m.getSenha().equals(senha)) { System.out.println("❌ Senha incorreta."); return; }

        moradorLogado = m;
        System.out.println("✅ Bem-vindo(a), " + m.getNome() + "!");
    }

    private void listarMoradores() {
        System.out.println("\n=== MORADORES CADASTRADOS ===");
        var lista = controller.listarMoradores();

        if (lista.isEmpty()) { System.out.println("Nenhum morador cadastrado ainda."); return; }

        lista.forEach(m ->
                System.out.println("ID " + m.getId() + " - " + m.getNome()
                        + " | " + m.getEndereco()
                        + " | " + m.getEmail())
        );
    }

    private void filtrarPorBairro() {
        System.out.println("\n=== FILTRAR AVISOS POR BAIRRO ===");

        List<String> bairros = controller.listarBairrosComAvisos();

        if (bairros.isEmpty()) {
            System.out.println("Nenhum aviso cadastrado ainda.");
            return;
        }

        System.out.println("Bairros com avisos:");
        for (int i = 0; i < bairros.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + bairros.get(i));
        }

        System.out.print("Digite o nome do bairro: ");
        String bairro = sc.nextLine();

        controller.filtrarPorBairro(bairro);
    }

    private void excluirMeuAviso() {
        System.out.println("\n=== EXCLUIR MEU AVISO ===");

        Morador m = obterMoradorAtual();
        if (m == null) return;

        List<Aviso> meus = controller.listarAvisosDeMorador(m.getId());
        if (meus.isEmpty()) { System.out.println("Você não possui avisos enviados."); return; }

        System.out.println("\nSeus avisos:");
        for (int i = 0; i < meus.size(); i++) {
            Aviso a = meus.get(i);
            System.out.printf("  %d - [ID %d] %s | %s | Bairro: %s%n",
                    i + 1, a.getId(), a.getTitulo(), a.getUrgencia(), a.getBairro());
        }

        System.out.print("\nDigite o número do aviso que deseja excluir (0 para cancelar): ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 0) { System.out.println("Operação cancelada."); return; }
        if (escolha < 1 || escolha > meus.size()) { System.out.println("❌ Número inválido."); return; }

        Aviso aviso = meus.get(escolha - 1);
        System.out.println("\nAviso selecionado: \"" + aviso.getTitulo() + "\"");
        System.out.print("Digite sua senha para confirmar a exclusão: ");
        String senha = sc.nextLine();

        controller.excluirAviso(m, senha, aviso.getId());
    }

    private void editarMeuAviso() {
        System.out.println("\n=== EDITAR MEU AVISO ===");

        Morador m = obterMoradorAtual();
        if (m == null) return;

        List<Aviso> meus = controller.listarAvisosDeMorador(m.getId());
        if (meus.isEmpty()) { System.out.println("Você não possui avisos enviados."); return; }

        System.out.println("\nSeus avisos:");
        for (int i = 0; i < meus.size(); i++) {
            Aviso a = meus.get(i);
            System.out.printf("  %d - [ID %d] %s | %s | Bairro: %s%n",
                    i + 1, a.getId(), a.getTitulo(), a.getUrgencia(), a.getBairro());
        }

        System.out.print("\nDigite o número do aviso que deseja editar (0 para cancelar): ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 0) { System.out.println("Operação cancelada."); return; }
        if (escolha < 1 || escolha > meus.size()) { System.out.println("❌ Número inválido."); return; }

        Aviso aviso = meus.get(escolha - 1);

        System.out.println("\nAviso: \"" + aviso.getTitulo() + "\"");
        System.out.println("O que deseja editar?");
        System.out.println("  1 - Título        (atual: " + aviso.getTitulo() + ")");
        System.out.println("  2 - Descrição     (atual: " + aviso.getDescricao() + ")");
        System.out.println("  3 - Urgência      (atual: " + aviso.getUrgencia() + ")");
        System.out.println("  4 - Bairro        (atual: " + aviso.getBairro() + ")");
        System.out.println("  5 - Rua           (atual: " + aviso.getRua() + ")");
        System.out.println("  0 - Cancelar");
        System.out.print("Escolha: ");
        int campo = sc.nextInt();
        sc.nextLine();

        if (campo == 0) { System.out.println("Operação cancelada."); return; }
        if (campo < 1 || campo > 5) { System.out.println("❌ Opção inválida."); return; }

        String novoTitulo    = null;
        String novaDescricao = null;
        String novaUrgencia  = null;
        String novoBairro    = null;
        String novaRua       = null;

        switch (campo) {
            case 1 -> { System.out.print("Novo título: ");    novoTitulo    = sc.nextLine(); }
            case 2 -> { System.out.print("Nova descrição: "); novaDescricao = sc.nextLine(); }
            case 3 -> { novaUrgencia = lerUrgencia(); }
            case 4 -> { System.out.print("Novo bairro: ");    novoBairro    = sc.nextLine(); }
            case 5 -> { System.out.print("Nova rua: ");       novaRua       = sc.nextLine(); }
        }

        System.out.print("\nDigite sua senha para confirmar: ");
        String senha = sc.nextLine();

        controller.editarAviso(m, senha, aviso.getId(),
                novoTitulo, novaDescricao, novaUrgencia, novoBairro, novaRua);
    }

    // =========================
    // AUXILIARES
    // =========================

    private Morador obterMoradorAtual() {
        if (moradorLogado != null) return moradorLogado;

        System.out.print("Seu ID (ou use opção 6 para selecionar perfil): ");
        int id = sc.nextInt();
        sc.nextLine();

        Morador m = controller.buscarMorador(id);
        if (m == null) System.out.println("❌ Morador não encontrado.");
        return m;
    }

    private String lerTitulo() {
        System.out.print("Título: ");
        return sc.nextLine();
    }

    private String lerDescricao() {
        System.out.print("Descrição: ");
        return sc.nextLine();
    }

    private int lerTipo() {
        System.out.println("Tipo de aviso:");
        System.out.println("  1 - Segurança");
        System.out.println("  2 - Infraestrutura");
        System.out.println("  3 - Saúde");
        System.out.print("Escolha: ");
        int tipo = sc.nextInt();
        sc.nextLine();
        return tipo;
    }

    private String lerUrgencia() {
        System.out.println("Urgência:");
        System.out.println("  1 - VERDE (informativo)");
        System.out.println("  2 - AMARELO (atenção)");
        System.out.println("  3 - VERMELHO (urgente)");
        System.out.print("Escolha: ");
        int u = sc.nextInt();
        sc.nextLine();

        return switch (u) {
            case 2 -> "AMARELO";
            case 3 -> "VERMELHO";
            default -> "VERDE";
        };
    }
}
