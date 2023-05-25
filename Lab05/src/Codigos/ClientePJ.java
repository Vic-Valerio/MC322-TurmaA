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

    /* public boolean atualizarFrota(Veiculo veiculo, boolean acao){
        // acao = 0: remove veiculo da frota; acao = 1: adiciona veiculo na frota;
        // se veiculos = 0 remove a frota
    }
    */
}