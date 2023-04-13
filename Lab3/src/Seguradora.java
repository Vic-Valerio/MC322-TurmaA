import java.util.List;

public class Seguradora {

    //Atributos de instancia
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;
    
    // Metodo construtor
    public Seguradora(String nome, String telefone, String email, String endereco,
                        List<Sinistro> listaSinistros, List<Cliente> listaClientes) {
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.endereco = endereco;
    this.listaSinistros = listaSinistros;
    this.listaClientes = listaClientes;
    }
    
    // Metodos de acesso (Getters and setters)
    public String getNome() {
    return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    // Metodo para criar clientes e armazenar numa lista
    // public boolean  

    // Metodo para remover clientes

    // Metodo para listar sinistros

    // Metodo para listar clientes
    
}