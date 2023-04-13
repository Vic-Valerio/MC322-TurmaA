import java.util.ArrayList;
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
    public Seguradora(String nome, String telefone, String email, String endereco) {
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.endereco = endereco;
    listaSinistros = new ArrayList<>();
    listaClientes = new ArrayList<>();
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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    // Metodo para criar clientes e armazenar numa lista
    // Se o cliente ja esta cadastrado retorna False se nao, cadastra o cliente 
    public boolean registerCliente(Cliente cliente) {
        if (listaClientes.contains(cliente))
            return false;
        else
            return listaClientes.add(cliente);
    }

    // Metodo para remover cliente da lista clientes
    // Retorna False se o cliente nao esta na lista e True se removeu o cliente com sucesso;
    public boolean removeCliente(Cliente cliente) {
        return listaClientes.remove(cliente);
    }

    // Metodo para listar clientes
    public List<Cliente> listarClientes(String tipoCliente){
        List<Cliente> clientesPF = new ArrayList<>();
        List<Cliente> clientesPJ = new ArrayList<>();
        // percorrer a liosta separando clientes PF e PJ;
        Cliente c;
        if (c instanceof ClientePF);
    }

    // Metodo para gerar sinistros
    public boolean gerarSinistros(String data, String enderecoSinistro, Veiculo veiculo, Cliente cliente){
        Sinistro sinistro = new Sinistro(data, enderecoSinistro, veiculo, cliente);
        // Verificar se o cliente eh valido, se o veiculo instanciado consta e se a data faz sentido
        if (listaClientes.contains(cliente)){
            return listaSinistros.add(sinistro);
        }
        return false;
    }

    // Metodo para listar sinistros 
}