package service;

import model.Aviso;
import model.AvisoDestinatario;
import model.Morador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gerencia o envio de avisos direcionados a moradores específicos.
 * Utiliza ArrayList em memória — sem banco de dados.
 */
public class NotificacaoService {

    private List<AvisoDestinatario> notificacoes = new ArrayList<>();
    private int contador = 1;

    /**
     * Envia um aviso para um único destinatário.
     */
    public AvisoDestinatario enviarParaMorador(Aviso aviso, Morador destinatario) {
        AvisoDestinatario notificacao = new AvisoDestinatario(contador++, aviso, destinatario);
        notificacoes.add(notificacao);
        return notificacao;
    }

    /**
     * Envia o mesmo aviso para uma lista de destinatários.
     */
    public List<AvisoDestinatario> enviarParaVarios(Aviso aviso, List<Morador> destinatarios) {
        List<AvisoDestinatario> enviadas = new ArrayList<>();
        for (Morador dest : destinatarios) {
            AvisoDestinatario notificacao = new AvisoDestinatario(contador++, aviso, dest);
            notificacoes.add(notificacao);
            enviadas.add(notificacao);
        }
        return enviadas;
    }

    /**
     * Retorna todas as notificações recebidas por um morador (lidas e não lidas).
     */
    public List<AvisoDestinatario> buscarPorDestinatario(int moradorId) {
        return notificacoes.stream()
                .filter(n -> n.getDestinatario().getId() == moradorId)
                .collect(Collectors.toList());
    }

    /**
     * Retorna apenas as notificações não lidas de um morador.
     */
    public List<AvisoDestinatario> buscarNaoLidas(int moradorId) {
        return notificacoes.stream()
                .filter(n -> n.getDestinatario().getId() == moradorId && !n.isLido())
                .collect(Collectors.toList());
    }

    /**
     * Marca uma notificação como lida pelo seu ID.
     */
    public boolean marcarComoLida(int notificacaoId) {
        for (AvisoDestinatario n : notificacoes) {
            if (n.getId() == notificacaoId) {
                n.marcarComoLido();
                return true;
            }
        }
        return false;
    }

    /**
     * Marca todas as notificações de um morador como lidas.
     */
    public void marcarTodasComoLidas(int moradorId) {
        notificacoes.stream()
                .filter(n -> n.getDestinatario().getId() == moradorId && !n.isLido())
                .forEach(AvisoDestinatario::marcarComoLido);
    }

    /**
     * Conta quantas notificações não lidas um morador possui.
     */
    public long contarNaoLidas(int moradorId) {
        return notificacoes.stream()
                .filter(n -> n.getDestinatario().getId() == moradorId && !n.isLido())
                .count();
    }
}
