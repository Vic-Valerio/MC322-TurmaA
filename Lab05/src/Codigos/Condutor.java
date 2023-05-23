package Codigos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Condutor {
    private final String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private LocalDate dataNasc;
    private List<Sinistro> listaSinistros;

    // Metodo construtor
    public Condutor(String cpf, String nome, String endereco, String telefone, String email, LocalDate dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataNasc = dataNasc;
        listaSinistros = new ArrayList<>(null);
    }

    // Metodos de acesso
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void adicionarSinistro(Sinistro sinis){
        
        listaSinistros.add(sinis);
    }
}
