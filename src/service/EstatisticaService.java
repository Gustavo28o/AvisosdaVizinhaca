package service;

import model.Aviso;
import java.util.List;

public class EstatisticaService {

    public void gerar(List<Aviso> avisos) {
        int verde = 0, amarelo = 0, vermelho = 0;

        for (Aviso a : avisos) {
            switch (a.getUrgencia()) {
                case "VERDE": verde++; break;
                case "AMARELO": amarelo++; break;
                case "VERMELHO": vermelho++; break;
            }
        }

        int total = verde + amarelo + vermelho;

        int pctVermelho = total > 0 ? (int) Math.round((vermelho * 100.0) / total) : 0;
        int pctAmarelo  = total > 0 ? (int) Math.round((amarelo  * 100.0) / total) : 0;
        int pctVerde    = total > 0 ? (int) Math.round((verde    * 100.0) / total) : 0;

        System.out.println("• Vermelho (urgente): "     + vermelho + " avisos (" + pctVermelho + "%)");
        System.out.println("• Amarelo (atenção): "      + amarelo  + " avisos (" + pctAmarelo  + "%)");
        System.out.println("• Verde (informativo): "    + verde    + " avisos (" + pctVerde    + "%)");
    }
}