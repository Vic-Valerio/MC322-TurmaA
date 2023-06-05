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
    }

    // Metodos de acesso
    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    @Override
    public ClientePJ getClientePJ() {
        return cliente;
    }

    @Override
    public String toString(){
        String str = "Seguro id "+super.getId()+
                    "\nData de inicio "+super.getDataInicio()+
                    "\nData de término "+super.getDataFim()+
                    "\n Seguradora "+super.getSeguradora().getNome()+
                    "\n Cliente CNPJ "+cliente.getCNPJ()+
                    "\n Frota "+frota.getCode()+"\n";
        return str;
    }

    // Metodos para habilitar ou desabilitar um condutor
    public boolean habilitarCondutor(Condutor condutor, Seguro seguro){
        for(Condutor c: seguro.getListaCondutores()){
            if(c.getCpf().equals(condutor.getCpf())){
                condutor.setHabilitado(true);
                return true;
            }
        }
        System.out.println("Condutor não encontrado\n");
        return false;
    }

    public boolean desabilitarCondutor(Condutor condutor, Seguro seguro){
        for(Condutor c: seguro.getListaCondutores()){
            if(c.getCpf().equals(condutor.getCpf())){
                condutor.setHabilitado(false);
                return true;
            }
        }
        System.out.println("Condutor não encontrado\n");
        return false;
    }

    // Metodo para gerar um sinistro de condutores
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro,
                                  Condutor condutorSinistro, Seguradora seguradoraSinistro){
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
        condutorSinistro.getListaSinistros().add(sinistro);
        System.out.println("Sinistro gerado com sucesso\n");
        return true;
    }

    // Metodo para gerar um sinistro do cliente;
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro, 
                                  Seguradora seguradoraSinistro){
    
    Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, null, this);
    
    return this.getListaSinistros().add(sinistro);
    }

    // Metodo para calcular o valor mensal do seguro;
    public void calcularValor(){
        int anosPosFundacao = cliente.getTempoFundacao();
        int qtdFuncionarios = this.getListaCondutores().size(); // Assume-se que os condutores sao os funcionarios da empresa inclusos no seguro
        int qtdVeiculos = frota.getListaVeiculos().size(); // quantidade de veiculos da frota;
        int qtdSinistrosCliente = this.getListaSinistros().size();
        int qtdSinistrosCondutores = 0;
        double valorMensal = 0, valorBase = CalcSeguro.VALOR_BASE.getFator();

        for (Condutor c: this.getListaCondutores()){
            qtdSinistrosCondutores += c.getListaSinistros().size();
        }

        valorMensal = (valorBase*(10+(qtdFuncionarios/10))* (1+1/(qtdVeiculos+2))*
                    (1+1/(anosPosFundacao+2))* (2+qtdSinistrosCliente/10)* (5+qtdSinistrosCondutores/10));
        
        this.setValorMensal(valorMensal);
    }
}
