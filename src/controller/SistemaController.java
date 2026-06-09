package controller;

import model.*;
import service.*;
import model.AvisoDestinatario;

import java.util.List;

public class SistemaController {

    private MoradorService moradorService = new MoradorService();
    private AvisoService avisoService = new AvisoService();
    private EstatisticaService estatisticaService = new EstatisticaService();
    private NotificacaoService notificacaoService = new NotificacaoService();

    // =========================
    // MORADOR
    // =========================

    public Morador cadastrarMorador(String nome, String bairro, String rua,
                                    String telefone, String email, String senha) {
        return moradorService.cadastrar(nome, bairro, rua, telefone, email, senha);
    }

    public Morador buscarMorador(int id) {
        return moradorService.buscarPorId(id);
    }

    public List<Morador> listarMoradores() {
        return moradorService.listar();
    }

    // =========================
    // AVISO GERAL
    // =========================

    public void enviarAviso(Morador m, String senha, String titulo, String desc,
                            String urgencia, String bairro, String rua, int tipo) {
        if (m == null) { System.out.println("Erro: morador inválido."); return; }

        if (!m.getSenha().equals(senha)) { System.out.println("❌ Senha incorreta!"); return; }

        if (!m.podeEnviarAviso()) { System.out.println("Sem permissão."); return; }

        int novoId = avisoService.gerarProximoId();
        Aviso aviso = criarAviso(novoId, titulo, desc, urgencia, bairro, rua, m, tipo);

        avisoService.enviarAviso(aviso);
        m.adicionarAviso(aviso);

        System.out.println("✅ Aviso enviado! ID: " + novoId);
    }

    // =========================
    // LISTAGEM
    // =========================

    public void listarAvisos() {
        List<Aviso> avisos = avisoService.listar();

        if (avisos.isEmpty()) { System.out.println("Nenhum aviso encontrado."); return; }

        for (Aviso a : avisos) {
            System.out.println("---------");
            a.exibirAlerta();
        }
        System.out.println("---------");
    }

    public void listarAvisosPorMorador(int id) {
        List<Aviso> avisos = avisoService.listar();
        boolean encontrou = false;

        for (Aviso a : avisos) {
            if (a.getMorador() != null && a.getMorador().getId() == id) {
                System.out.println("---------");
                a.exibirAlerta();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aviso encontrado para este morador.");
        } else {
            System.out.println("---------");
        }
    }

    public void filtrarPorBairro(String bairro) {
        List<Aviso> lista = avisoService.filtrarPorBairro(bairro);

        if (lista.isEmpty()) {
            System.out.println("Nenhum aviso encontrado para o bairro \"" + bairro + "\".");
            return;
        }

        System.out.println(lista.size() + " aviso(s) encontrado(s):");
        for (Aviso a : lista) {
            System.out.println("---------");
            a.exibirAlerta();
        }
        System.out.println("---------");
    }

    public List<String> listarBairrosComAvisos() {
        return avisoService.listarBairros();
    }

    // =========================
    // ESTATÍSTICAS
    // =========================

    public void gerarEstatisticas() {
        estatisticaService.gerar(avisoService.listar());
    }

    // =========================
    // EXCLUIR / EDITAR AVISOS
    // =========================

    public List<Aviso> listarAvisosDeMorador(int moradorId) {
        return avisoService.listarPorMorador(moradorId);
    }

    public boolean excluirAviso(Morador m, String senha, int avisoId) {
        if (m == null) { System.out.println("❌ Morador inválido."); return false; }
        if (!m.getSenha().equals(senha)) { System.out.println("❌ Senha incorreta!"); return false; }
        boolean removido = avisoService.excluirAviso(avisoId, m.getId());
        if (removido) {
            m.getAvisos().removeIf(a -> a.getId() == avisoId);
            System.out.println("✅ Aviso excluído com sucesso!");
        } else {
            System.out.println("❌ Aviso não encontrado ou não pertence a você.");
        }
        return removido;
    }

    public boolean editarAviso(Morador m, String senha, int avisoId,
                               String novoTitulo, String novaDescricao,
                               String novaUrgencia, String novoBairro, String novaRua) {
        if (m == null) { System.out.println("❌ Morador inválido."); return false; }
        if (!m.getSenha().equals(senha)) { System.out.println("❌ Senha incorreta!"); return false; }
        Aviso aviso = avisoService.buscarPorId(avisoId);
        if (aviso == null || aviso.getMorador() == null || aviso.getMorador().getId() != m.getId()) {
            System.out.println("❌ Aviso não encontrado ou não pertence a você.");
            return false;
        }
        if (novoTitulo    != null) aviso.setTitulo(novoTitulo);
        if (novaDescricao != null) aviso.setDescricao(novaDescricao);
        if (novaUrgencia  != null) aviso.setUrgencia(novaUrgencia);
        if (novoBairro    != null) aviso.setBairro(novoBairro);
        if (novaRua       != null) aviso.setRua(novaRua);
        System.out.println("✅ Aviso editado com sucesso!");
        return true;
    }

    // =========================
    // NOTIFICAÇÕES
    // =========================

    public void listarNotificacoes(int moradorId) {
        List<AvisoDestinatario> notificacoes = notificacaoService.buscarPorDestinatario(moradorId);

        if (notificacoes.isEmpty()) { System.out.println("Nenhuma notificação recebida."); return; }

        System.out.println("Total: " + notificacoes.size() + " | "
                + notificacaoService.contarNaoLidas(moradorId) + " não lida(s)");

        for (AvisoDestinatario n : notificacoes) {
            System.out.println("---------");
            System.out.println(n);
            System.out.println("  📝 " + n.getAviso().getDescricao());
            System.out.println("  📍 " + n.getAviso().getLocal());
        }
        System.out.println("---------");
        notificacaoService.marcarTodasComoLidas(moradorId);
    }

    public void exibirContadorNaoLidas(int moradorId) {
        long total = notificacaoService.contarNaoLidas(moradorId);
        if (total > 0) {
            System.out.println("🔔 Você tem " + total + " notificação(ões) não lida(s)!");
        }
    }

    // =========================
    // AUXILIAR PRIVADO
    // =========================

    private Aviso criarAviso(int id, String titulo, String desc, String urgencia,
                              String bairro, String rua, Morador m, int tipo) {
        return switch (tipo) {
            case 2 -> new AvisoInfraestrutura(id, titulo, desc, urgencia, bairro, rua, m);
            case 3 -> new AvisoSaude(id, titulo, desc, urgencia, bairro, rua, m);
            default -> new AvisoSeguranca(id, titulo, desc, urgencia, bairro, rua, m);
        };
    }
}
