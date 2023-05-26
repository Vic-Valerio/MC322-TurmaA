package Codigos;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private List<Frota> listaFrota;

    private int tempoFundacao;
    private LocalDate dataHoje = LocalDate.now();
    private Period p;

        public ClientePJ(String nome, String endereco, String telefone, String email,
                     String CNPJ, LocalDate dataFundacao) {

        super(nome, endereco, telefone, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        
        listaFrota = new ArrayList<>();

        if (dataFundacao != null && dataHoje != null) {
            p = Period.between(dataFundacao, dataHoje);
        }
        tempoFundacao = p.getYears();
    }

    // Metodos de acesso
    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public List<Frota> getListaFrota() {
        return listaFrota;
    }

    public int getTempoFundacao() {
        return tempoFundacao;
    }

    //Metodo de conversao para string;
    @Override
    public String toString() {
        String str = "Nome: " + super.getNome()+"\n"
                    + "Endereço: "+ super.getEndereco()+"\n"
                    + "Telefone: "+super.getTelefone()+"\n"
                    + "Email: "+super.getEmail()+"\n"
                    + "CNPJ: " + CNPJ +"\n" 
                    + "Fundação: " + dataFundacao +"\n";
        return str;
    }
    
    @Override
    public String getIdentificador(){
        return CNPJ;
    }
    
    // Metodo para cadastrar nova frota na lista de frotas do cliente PJ;
    public boolean cadastrarFrota(Frota frota) {
        for(Frota f: listaFrota){
            if (frota.getCode().equals(f.getCode())){
                System.out.println("Frota já cadastrada\n");
                return false;
            }
        }
        System.out.println("Frota cadastrada com sucesso\n");
        return listaFrota.add(frota);
    }

    // Metodo para obter os veiculos de uma frota do cliente;
    public boolean getVeiculosporFrota(Frota frota){
        boolean existeFrota = false;
        for (Frota f: listaFrota){
            if (f.getCode().equals(frota.getCode())){
                existeFrota = true;
                System.out.println("Veiculos da frota "+f.getCode()+":\n" + f.getListaVeiculos());
                break;
            }
        }
        return existeFrota;
    }

    // Metodo para atualizar uma frota do cliente;
    public boolean atualizarFrota(Frota frota, Veiculo veiculo, int acao){
        boolean existeFrota = false, temVeiculo = false;

        // Verificar se a frota existe e, se existir, verifica o veiculo
        for (Frota f:listaFrota){
            if (f.getCode().equals(frota.getCode())){
                existeFrota = true;
                for (Veiculo v: f.getListaVeiculos()){
                   if (v.getPlaca().equals(veiculo.getPlaca())){
                    temVeiculo = true;
                    break;
                   }
                }
                break;
            }
        }
        if(!existeFrota){
            System.out.println("Frota nao encontrada\n");
            return false;
        }

        if(acao == 0){ // acao remover = 0;
            // Se o veiculo estiver na lista ele é excluido;
            // caso contrario retorna false e nao exclui;
            if(temVeiculo){
                frota.removeVeiculo(veiculo);
                System.out.println("Veiculo de placa "+veiculo.getPlaca()+" removido com sucesso\n");
                if(frota.getListaVeiculos().size() == 0){
                    // Se a frota ficar sem veiculos ela é removida;
                    listaFrota.remove(frota);
                    System.out.println("Frota "+frota.getCode() +"excluida pela ausencia de veículos\n");
                    return true;
                }
                System.out.println("Frota atualizada\n");
                return true;
            }
            else{
                System.out.println("Veiculo não encontrado\nFrota não atualizada\n");
                return false;
            }
        }
        if (acao == 1){ //acao incluir = 1;
            // Se o veiculo esta cadastrado, nada é feito;
            // caso contrario, cadastra o veiculo na frota;
            if(!temVeiculo){
                frota.adicionarVeiculo(veiculo);
                System.out.println("Veiculo de placa "+veiculo.getPlaca()+" cadastrado com sucesso\nFrota atualizada\n");
                return true;
            }
            else{
                System.out.println("Veiculo de placa "+veiculo.getPlaca()+" já cadastrado\nFrota não atualizada\n");
                return false;
            }
        }
        else{
            System.out.println("Operação inválida\n");
            return false;
        }
    }
}