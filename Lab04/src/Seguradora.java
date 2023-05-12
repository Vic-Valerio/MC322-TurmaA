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

    /*         Metodo para cadastrar clientes e armazenar numa lista
    Se o cliente ja esta cadastrado retorna False, se nao, cadastra o cliente e retorna True */
    public boolean registerCliente(Cliente cliente) {
        if (listaClientes.contains(cliente))
            return false;
        else
            return listaClientes.add(cliente);
    }

    /*         Metodo para remover cliente da lista clientes
    Retorna False se o cliente nao esta na lista e True se removeu o cliente com sucesso */
    public boolean removeCliente(Cliente cliente) {
        return listaClientes.remove(cliente);
    }

    // Metodo para listar clientes
    public void listarClientes(String tipoCliente){
        List<Cliente> listaClientesPF = new ArrayList<>();
        List<Cliente> listaClientesPJ = new ArrayList<>();

        if (tipoCliente == "PF")
            System.out.println("Clientes Pessoa Fisica:\n");
        
        if (tipoCliente == "PJ")
            System.out.println("Clientes Pessoa Juridica:\n");

        for(int i = 0; i < listaClientes.size(); i++){
            if (listaClientes.get(i) instanceof ClientePF){
                listaClientesPF.add(listaClientes.get(i));
            }
            if (listaClientes.get(i) instanceof ClientePJ){
                listaClientesPJ.add(listaClientes.get(i));
            }
        }

        if (tipoCliente == "PF"){
            //System.out.println("Clientes Pessoa Fisica:\n");
            for (int i = 0; i < listaClientesPF.size(); i++){
                System.out.println(listaClientesPF.get(i) + "\n");
            }
        }
        if (tipoCliente == "PJ"){
            //System.out.println("Clientes Pessoa Juridica:\n");
            for (int i = 0; i < listaClientesPJ.size(); i++){
                System.out.println(listaClientesPJ.get(i) + "\n");
            }
        }
    }

    // Metodo para gerar sinistros
    public boolean gerarSinistros(String data, String enderecoSinistro, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        Sinistro sinistro = new Sinistro(data, enderecoSinistro, seguradora, veiculo, cliente);
        // Verificar se o cliente eh valido, em caso afirmativo gera sinistro caso contrario nao
        for(Cliente c: listaClientes){
            if(c.getIdentificador() == cliente.getIdentificador()){
                System.out.println("Sinistro registrado\n");
                return listaSinistros.add(sinistro);
            }
        }
        System.out.println("Sinistro invalido\n");
        return false;
    }

    // Metodo para listar sinistros
    public void listarSinistros(){
        System.out.println("Sinistros:\n");
        for (Sinistro s: listaSinistros){
            System.out.println(s + "\n");
        }
    }

    // Metodo para visualizar sinistros
    public boolean visualizarSinistro(String cliente){
        // Percorre a lista de sinistros procurando pelo cliente informado;
        // Caso encontre, imprime na tela e retorna True;
        // Se não, retorna False;
        for (int i = 0; i < listaSinistros.size(); i++){
            if (listaSinistros.get(i).getCliente().getNome().equalsIgnoreCase(cliente)){
                System.out.println(listaSinistros.get(i));
                return true;
            }
        }
        return false;
    }

    // Metodo para calcular o preço do seguro para cada cliente
    public double calcularPrecoSeguroCliente(Cliente cliente){
        int qtdSinistros = listaSinistros.size();
        double score = cliente.calculaScore();

        /*if(cliente instanceof ClientePF){
            score = ClientePF.calculaScore();
        }
        if(cliente instanceof ClientePJ){
            score = ClientePJ.calculaScore();
        }*/

        return score *(1 + qtdSinistros);
    }

    // Metodo para calcular o balanço de seguros de todos os clientes da seguradora;
    public double calcularReceita(Seguradora seguradora){
        double receita = 0;
        for(Cliente c:listaClientes){
            receita += calcularPrecoSeguroCliente(c);
        }
        return receita;
    }
}