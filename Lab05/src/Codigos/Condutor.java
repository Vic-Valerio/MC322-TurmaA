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
    private boolean habilitado = false;

    // Metodo construtor;
    public Condutor(String cpf, String nome, String endereco, String telefone, String email, LocalDate dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataNasc = dataNasc;
        listaSinistros = new ArrayList<>(null);
    }

    // Metodos de acesso;
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
        
    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString(){
        String str = "Condutor " + nome +" CPF: "+ cpf+
                    "\nEndereço "+endereco+
                    "\nEmail "+ email+
                    "Data de nascimento "+dataNasc+"\n";
        return str;
    }

    // Metodo para gerar um novo sinistro
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro, Condutor condutorSinistro,
                                  Seguro seguroSinistro, Seguradora seguradoraSinistro){
        boolean temCondutor = false, temSeguro = false;
        Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, condutorSinistro, seguroSinistro);

        // Verificar se o cliente eh valido, em caso afirmativo gera sinistro caso contrario nao;
        for (Condutor cond: seguroSinistro.getListaCondutores()){
            if(cond.getCpf().equals(condutorSinistro.getCpf())){
                temCondutor = true;
                // Verifica tambem se o condutor esta habilitado ou nao;
                if (!cond.isHabilitado()){
                    System.out.println("Condutor não habilitado, sinistro não pode ser gerado\n");
                    return false;
                }
                break;
            }
        }
        if(!temCondutor){
            System.out.println("Condutor não cadastrado, sinistro não pode ser gerado\n");
            return false;
        }

        // Verifica s o seguro esta contido na seguradora
        for(Seguro seguro: seguradoraSinistro.getListaSeguros()){
            if(seguro.getId() == seguroSinistro.getId()){
                temSeguro = true;
                break;
            }
        }
        if(!temSeguro){
            System.out.println("Seguro não existente, sinistro não pode ser gerado\n");
            return false;
        }
        return listaSinistros.add(sinistro);
    }

    // Metodo para adicionar um sinistro na lista de sinistros de cada condutor;
    public void adicionarSinistro(Sinistro sinis){
        listaSinistros.add(sinis);
    }
}
