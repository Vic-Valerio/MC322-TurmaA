package Codigos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private List<Frota> listaFrota;

        public ClientePJ(String nome, String endereco, String telefone, String email,
                     String CNPJ, LocalDate dataFundacao) {

        super(nome, endereco, telefone, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        listaFrota = new ArrayList<>();
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

    @Override
    public String getIdentificador(){
        return CNPJ;
    }

    public boolean getVeiculosPorFrota(Frota frota){
        System.out.println("Veiculos da frota "+frota.getCode()+":\n");
        if (frota.getListaVeiculos() == null){
            System.out.println("Frota não possui veículos registrados\n");
            return false;
        }
        for(Veiculo v: frota.getListaVeiculos()){
            System.out.println(v);
        }
        return true;
    }

    /* public boolean atualizarFrota(){
        O que faz esse metodo?
    }
    */


    /*
    @Override
    public double calculaScore(){
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        int qtdCarros = listaVeiculos.size();

        return valorBase* (1+(qtdFuncionarios/100.0))*qtdCarros;
    } */
}