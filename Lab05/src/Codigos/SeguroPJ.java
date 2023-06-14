package Codigos;
import java.time.LocalDate;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;
    
    // Metodo construtor
    public SeguroPJ(LocalDate dataFim, Seguradora seguradora,
                    Frota frota, ClientePJ cliente) {
        super(dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
        this.valorMensal = calcularValor();
    }

    // Metodos de acesso
    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }  

    @Override
    public String toString(){
        String str = "Seguro id "+super.getId()+
                    "\nData de inicio "+super.getDataInicio()+
                    "\nData de término "+super.getDataFim()+
                    "\nSeguradora "+super.getSeguradora().getNome()+
                    "\nCliente CNPJ "+cliente.getIdentificador()+
                    "\nFrota "+frota.getCode()+"\n";
        return str;
    }

    // Metodos para habilitar ou desabilitar um condutor
    public boolean habilitarCondutor(Condutor condutor){
        boolean estaHabilitado = false;
        for(Condutor c: this.getListaCondutores()){
            // Se o condutor nao esta na lista, ele nao esta habilitado
            if(c.getCpf().equals(condutor.getCpf())){
                estaHabilitado = true;
                break;
            }
        }
        if(!estaHabilitado){
            // Habilita o condutor adicionando-o  na lista e recalcula o valor do seguro;
            condutor.setHabilitado(true);
            this.getListaCondutores().add(condutor);
            System.out.println("Condutor "+condutor.getCpf()+" agora está habilitado no seguro "+this.getId()+"\n");
            this.setValorMensal(calcularValor());
            return true;
        }
        else{
            System.out.println("Condutor "+condutor.getCpf()+" já habilitado no seguro "+this.getId()+"\n");
            return false;
        }        
    }

    public boolean desabilitarCondutor(Condutor condutor){
        boolean estaHabilitado = false;
        for(Condutor c: this.getListaCondutores()){
            // Se o condutor nao esta na lista, ele nao esta habilitado
            if(c.getCpf().equals(condutor.getCpf())){
                estaHabilitado = true;
                break;
            }
        }
        if(estaHabilitado){
            // Desabilita o condutor removendo-o  da lista e recalcula o valor do seguro;
            condutor.setHabilitado(true);
            this.getListaCondutores().remove(condutor);
            System.out.println("Condutor "+condutor.getCpf()+" foi desabilitado do seguro "+this.getId()+"\n");
            this.setValorMensal(calcularValor());
            return true;
        }
        else{
            System.out.println("Condutor "+condutor.getCpf()+" já esta desabilitado no seguro "+this.getId()+"\n");
            return false;
        }        
    }

    // Metodo para gerar um sinistro de condutores
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro,
                                  Condutor condutorSinistro){
        boolean temCondutor = false;
        Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, condutorSinistro, this);

        // Verificar se o cliente eh valido, em caso afirmativo gera sinistro caso contrario nao;
        for (Condutor cond: this.getListaCondutores()){
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
        if(condutorSinistro.getListaSinistros().add(sinistro)){
            System.out.println("Sinistro gerado com sucesso\n");
            return true;
        }
        return false;
    }

    // Metodo para gerar um sinistro do cliente;
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro){
    
    Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, null, this);
    if(this.getListaSinistros().add(sinistro)){
        System.out.println("Sinistro gerado com sucesso\n");
    }
    return false;
    }

    // Metodo para calcular o valor mensal do seguro;
    public double calcularValor(){
        int anosPosFundacao = cliente.getTempoFundacao();
        int qtdFuncionarios = this.getListaCondutores().size(); // Assume-se que os condutores sao os funcionarios da empresa inclusos no seguro
        int qtdVeiculos = frota.getListaVeiculos().size(); // quantidade de veiculos da frota;
        int qtdSinistrosCliente = this.getListaSinistros().size();
        int qtdSinistrosCondutores = 0;
        double valorMensal = 0, valorBase = CalcSeguro.VALOR_BASE.getFator();

        // Busca todos os sinistros ja gerados pelo cliente e seus condutores na seguradora;
        for (Seguro seguro: this.getSeguradora().getListaSeguros()){
            if(seguro.getCliente().getIdentificador().equals(cliente.getIdentificador())){
                // Soma todos os sinistros do cliente;
                qtdSinistrosCliente += seguro.getListaSinistros().size();

                for(Condutor cond: seguro.getListaCondutores()){
                    // Soma todos os sinistros de todos os condutores segurados do cliente
                    qtdSinistrosCondutores += cond.getListaSinistros().size();
                }
            }
        }
        
        valorMensal = valorBase*(10+((float)qtdFuncionarios/10))*(1+1.0/(qtdVeiculos+2))*(1+1.0/(anosPosFundacao+2))*(2+qtdSinistrosCliente/10.0)*(5+qtdSinistrosCondutores/10.0);
        System.out.println("valor mensal: "+valorMensal+"\n");
        return valorMensal;
    }
}
