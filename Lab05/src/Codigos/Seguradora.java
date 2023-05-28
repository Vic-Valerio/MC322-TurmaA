package Codigos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seguradora {

    //Atributos de instancia
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Seguro> listaSeguros;
    private List<Cliente> listaClientes;
    
    // Metodo construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
    this.cnpj = cnpj;
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.endereco = endereco;
    listaSeguros = new ArrayList<>();
    listaClientes = new ArrayList<>();
    }
    
    // Metodos de acesso (Getters and setters)
    public String getCnpj(){
        return cnpj;
    }

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

    public List<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    // Metodo para listar clientes
    public void listarClientes(String tipoCliente){
        List<Cliente> listaClientesPF = new ArrayList<>();
        List<Cliente> listaClientesPJ = new ArrayList<>();

        for(Cliente c: listaClientes){
            if(c instanceof ClientePF){
                listaClientesPF.add(c);
            }
            if(c instanceof ClientePJ){
                listaClientesPJ.add(c);
            }
        }
        
        if (tipoCliente.equals("PF")){
            System.out.println("Clientes pessoa fisica da seguradora "+nome+":\n"+listaClientesPF+"\n");
        }
        if (tipoCliente.equals("PJ")){
            System.out.println("Clientes pessoa juridica da seguradora "+nome+":\n"+listaClientesPJ+"\n");
        }
    }

    // Metodo para gerar um novo seguro
    public boolean gerarSeguro(String tipoClinte){
        // Como receber os parametros necessarios para instanciar objeto SeguroPF ou seguro PJ?
        // Criar dois metodos?
        return false;
    }

    public boolean gerarSeguroPF(ClientePF cliente, Veiculo veiculo, LocalDate fimContrato){
        boolean temCliente = false;

        for(Cliente c: listaClientes){
            if(c.getIdentificador().equals(cliente.getIdentificador())){
                temCliente = true;
                break;
            }
        }
        if(!temCliente){
            System.out.println("Cliente não encontrado, nao foi possível gerar seguro\n");
            return false;
        }
        // Como saber se o cliente possui esse veículo a ser segurado?;
        
        Seguro s = new SeguroPF(fimContrato, this, veiculo, cliente);
        listaSeguros.add(s);
        return true;
    }

    // Metodo para cancelar um seguro existente da seguradora

    

    /*         Metodo para cadastrar clientes e armazenar numa lista
    Se o cliente ja esta cadastrado retorna False, se nao, cadastra o cliente e retorna True */
    public boolean cadastrarCliente(Cliente cliente) {
        boolean temCliente = false;
        for(Cliente c: listaClientes){
            if(c.getIdentificador().equals(cliente.getIdentificador())){
                temCliente = true;
                break;
            }
        }
        if(temCliente){
            System.out.println("Cliente já cadastrado\n");
            return false;
        }
        else{
            System.out.println("Cliente cadastrado com sucesso\n");
            listaClientes.add(cliente);
            return true;
        }
    }

    /*         Metodo para remover cliente da lista clientes
    Retorna False se o cliente nao esta na lista e True se removeu o cliente com sucesso */
    public boolean removerCliente(Cliente cliente){
        boolean temCliente = false;
        for(Cliente c: listaClientes){
            if(c.getIdentificador().equals(cliente.getIdentificador())){
                temCliente = true;
                break;
            }
        }
        if(temCliente){
            listaClientes.remove(cliente);
            System.out.println("Cliente removido com sucesso\n");
            return true;
        }
        else{
            System.out.println("Cliente não encontrado\n");
            return false;
        } 
    }

    // Metodo para visualizar seguros por cliente;
    public List<Seguro> getSegurosPorCliente(String clienteID){

        List<Seguro> listaSeguros = new ArrayList<>();
        // Percorre os seguros de uma seguradora buscando pelo cliente alvo;
        for(Seguro s:listaSeguros){
            // Se o cliente for encontrado (PF ou PJ);
            if(s.getClientePF().getIdentificador().equals(clienteID) || s.getClientePJ().getIdentificador().equals(clienteID)){
                // Adiciona os Seguros do cliente na lista de Seguros;
                listaSeguros.add(s);
            }
        }
        return listaSeguros;
    }

    // Metodo para visualizar sinistros por cliente
    public List<Sinistro> getSinistrosPorCliente(String clienteID){

        List<Sinistro> listaSinistros = new ArrayList<>();
        // Percorre os seguros de uma seguradora buscando pelo cliente alvo;
        for(Seguro s:listaSeguros){
            // Se o cliente for encontrado (PF ou PJ);
            if(s.getClientePF().getIdentificador().equals(clienteID) || s.getClientePJ().getIdentificador().equals(clienteID)){
                // Adiciona os sinistros do cliente na lista de sinistros;
                for (Sinistro sinis:s.getListaSinistros()){
                    listaSinistros.add(sinis);
                }
                // Adiciona os sinistros dos condutores na lista de sinistros;
                for (Condutor c: s.getListaCondutores()){
                    for (Sinistro sinis:c.getListaSinistros()){
                        listaSinistros.add(sinis);
                    }   
                }
            }
        }
        return listaSinistros;
    }

    // Metodo para calcular o balanço de seguros de todos os clientes da seguradora;
    public double calcularReceita(Seguradora seguradora){
        double receita = 0;
        for(Cliente c:listaClientes){
            calcularPrecoSeguroCliente(c);
            receita += c.getValorSeguro();
        }
        return receita;
    }
}