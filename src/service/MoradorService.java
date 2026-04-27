package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class MoradorService {

    private List<Morador> moradores = new ArrayList<>();

    public Morador cadastrar(String nome, String endereco, String telefone, String email) {
        Morador m = new MoradorComum(nome, endereco, telefone, email);
        moradores.add(m);
        return m;
    }

    public Morador buscarPorId(int id) {
        return moradores.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Morador> listar() {
        return moradores;
    }
}