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

    @Override
    public String toString(){
        String str = "Seguradora "+nome+" CNPJ " +cnpj+
        "\nTelefone "+telefone+
        "\nEndereço "+endereco+
        "\nEmail "+email+"\n";
        
        return str;
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

    // Metodo para gerar um novo seguro PJ
    //(Sobrecarregado para distinguir SeguroPF e SguroPJ);
    
    public boolean gerarSeguro(ClientePJ cliente, Frota frota, LocalDate fimContrato){
        boolean temCliente = false, temFrota = false;

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

        for(Frota f: cliente.getListaFrota()){
            if(f.getCode().equals(frota.getCode())){
                temFrota = true;
                break;
            }
        }
        if(!temFrota){
            System.out.println("Frota não encontrada, nao foi possível gerar seguro\n");
            return false;
        }

        Seguro s = new SeguroPJ(fimContrato, this, frota, cliente);
        listaSeguros.add(s);

        return true;
    }
    // Metodo para gerar um novo seguro PF
    //(Sobrecarregado para distinguir SeguroPF e SguroPJ);
    public boolean gerarSeguro(ClientePF cliente, Veiculo veiculo, LocalDate fimContrato){
        boolean temCliente = false, temVeiculo = false;

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
        
        for(Veiculo v:cliente.getListaVeiculos()){
            if(v.getPlaca().equals(veiculo.getPlaca())){
                temVeiculo = true;
                break;
            }
        }
        if(!temVeiculo){
            System.out.println("Veículo não cadastrado para o cliente informado, nao foi possível gerar seguro\n");
            return false;
        }

        Seguro s = new SeguroPF(fimContrato, this, veiculo, cliente);
        listaSeguros.add(s);
        return true;
    }

    // Metodo para cancelar um seguro existente da seguradora
    public boolean cancelarSeguro(int seguroID){
        for(Seguro s:listaSeguros){
            if(s.getId()== seguroID){
                listaSeguros.remove(s);
                System.out.println("Seguro "+s.getId()+" cancelado com sucesso\n");
                return true;
            }
        }
        System.out.println("Seguro não encontrado\n");
        return false;
    }

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
            listaClientes.add(cliente);
            System.out.println("Cliente "+cliente.getIdentificador()+" cadastrado com sucesso\n");
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
    public List<Seguro> getSegurosPorCliente(Cliente cliente){

        List<Seguro> listaSegurosCliente = new ArrayList<>();
        // Percorre os seguros de uma seguradora buscando pelo cliente alvo;
        for(Seguro s:listaSeguros){
            // Se o cliente for do tipo PF;
            if(cliente instanceof ClientePF){
                if(s.getCliente().getIdentificador().equals(cliente.getIdentificador())){
                    listaSegurosCliente.add(s);
                }
            }
            // Se o cliente for do tipo PJ;
            else{
                if(s.getCliente().getIdentificador().equals(cliente.getIdentificador())){
                    listaSegurosCliente.add(s);
                }
            }
        }
        return listaSegurosCliente;
    }

    // Metodo para visualizar sinistros por cliente
    public List<Sinistro> getSinistrosPorCliente(String clienteID){

        List<Sinistro> listaSinistrosCliente = new ArrayList<>();
        // Percorre os seguros de uma seguradora buscando pelo cliente alvo;
        for(Seguro s:listaSeguros){
            // Se o cliente for encontrado (PF ou PJ);
            if(s.getCliente().getIdentificador().equals(clienteID)){
                // Adiciona os sinistros do cliente na lista de sinistros;
                for (Sinistro sinis:s.getListaSinistros()){
                    listaSinistrosCliente.add(sinis);
                }
                // Adiciona os sinistros dos condutores na lista de sinistros;
                for (Condutor c: s.getListaCondutores()){
                    for (Sinistro sinis:c.getListaSinistros()){
                        listaSinistrosCliente.add(sinis);
                    }   
                }
            }
        }
        return listaSinistrosCliente;
    }

    // Metodo para calcular o balanço de seguros de todos os clientes da seguradora;
    public double calcularReceita(){
        double receita = 0;
        for(Seguro s:listaSeguros){
            s.calcularValor();
            receita += s.getValorMensal();
        }
        return receita;
    }
}