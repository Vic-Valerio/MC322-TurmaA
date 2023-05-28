package Codigos;

public  abstract class Cliente {
    // Atributos de instância;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;   

    // Metodo construtor;
    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    // Metodos de acesso (getters and setters)
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

    // Metodo de conversao para string 
    @Override
    public String toString() {
        String str = "informacoes do cliente:\n" 
                + "Nome: " + nome +"\n" 
                + "Endereco: " + endereco +"\n"
                + "Telefone: " + telefone+"\n"
                + "Email: "+ email+"\n";
        return str;
    }

    // Metodo para receber o identificador do cliente (CPF ou CNPJ) -> Sera sobrescrito
    public abstract String getIdentificador();
}