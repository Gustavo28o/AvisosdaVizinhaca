package service;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MoradorService {

    private List<Morador> moradores = new ArrayList<>();

    public Morador cadastrar(String nome, String bairro, String rua, String telefone, String email, String senha) {
        Morador m = new MoradorComum(nome, bairro, rua, telefone, email, senha);
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

    public List<Morador> listarPorBairro(String bairro) {
        return moradores.stream()
                .filter(m -> m.getBairro() != null &&
                             m.getBairro().equalsIgnoreCase(bairro))
                .collect(Collectors.toList());
    }
}
