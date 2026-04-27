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

        System.out.println("Estatísticas:");
        System.out.println("Verde: " + verde);
        System.out.println("Amarelo: " + amarelo);
        System.out.println("Vermelho: " + vermelho);
    }
}