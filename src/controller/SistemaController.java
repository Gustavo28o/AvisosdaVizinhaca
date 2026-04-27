package controller;

import model.*;
import service.*;

import java.util.List;

public class SistemaController {

    private MoradorService moradorService = new MoradorService();
    private AvisoService avisoService = new AvisoService();
    private EstatisticaService estatisticaService = new EstatisticaService();

    // =========================
    // MORADOR
    // =========================

    public Morador cadastrarMorador(String nome, String end, String tel, String email) {
        return moradorService.cadastrar(nome, end, tel, email);
    }

    public Morador buscarMorador(int id) {
        return moradorService.buscarPorId(id);
    }

    public List<Morador> listarMoradores() {
        return moradorService.listar();
    }

    // =========================
    // AVISO
    // =========================

    public void enviarAviso(Morador m, String titulo, String desc, String urgencia, String local, int tipo) {
    if (m == null) {
        System.out.println("Erro: morador inválido.");
        return;
    }

    if (!m.podeEnviarAviso()) {
        System.out.println("Este morador não tem permissão para enviar avisos.");
        return;
    }

    int novoId = avisoService.gerarProximoId();
    Aviso aviso;

    switch (tipo) {
        case 1:
            aviso = new AvisoSeguranca(novoId, titulo, desc, urgencia, local, m);
            break;
        case 2:
            aviso = new AvisoInfraestrutura(novoId, titulo, desc, urgencia, local, m);
            break;
        case 3:
            aviso = new AvisoSaude(novoId, titulo, desc, urgencia, local, m);
            break;
        default:
            System.out.println("Tipo inválido. Usando Segurança.");
            aviso = new AvisoSeguranca(novoId, titulo, desc, urgencia, local, m);
    }

    avisoService.enviarAviso(aviso);
    m.adicionarAviso(aviso);

    System.out.println("✅ Aviso enviado com sucesso! ID: " + novoId);
}
    public void listarAvisos() {
        List<Aviso> avisos = avisoService.listar();

        if (avisos.isEmpty()) {
            System.out.println("Nenhum aviso encontrado.");
            return;
        }

        for (Aviso a : avisos) {
            a.exibirAlerta();
            System.out.println("Descrição: " + a.getDescricao());
            System.out.println("Urgência: " + a.getUrgencia());
            System.out.println("---------");
        }
    }

   
    public void listarAvisosPorMorador(int id) {
        List<Aviso> avisos = avisoService.listar();
        boolean encontrou = false;

        for (Aviso a : avisos) {
            if (a.getMorador() != null && a.getMorador().getId() == id) {
                a.exibirAlerta();
                System.out.println("Descrição: " + a.getDescricao());
                System.out.println("---------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aviso encontrado para este morador.");
        }
    }


    public void filtrarPorUrgencia(String urgencia) {
        List<Aviso> lista = avisoService.filtrarUrgencia(urgencia);

        if (lista.isEmpty()) {
            System.out.println("Nenhum aviso com essa urgência.");
            return;
        }

        for (Aviso a : lista) {
            a.exibirAlerta();
            System.out.println(a.getDescricao());
            System.out.println("---------");
        }
    }

    // =========================
    // ESTATÍSTICAS
    // =========================

    public void gerarEstatisticas() {
        estatisticaService.gerar(avisoService.listar());
    }
}
