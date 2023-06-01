package Codigos;

import java.time.LocalDate;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente; // tem que ser ClientePF?

    // Metodo construtor;
    public SeguroPF(LocalDate dataFim, Seguradora seguradora,
                    Veiculo veiculo, ClientePF cliente) {
        super(dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    // Metodos de acesso;
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    @Override
    public ClientePF getClientePF() {
        return cliente;
    }

    @Override
    public String toString(){
        String str = "Seguro id "+super.getId()+
                    "\nData de inicio "+super.getDataInicio()+
                    "\nData de término "+super.getDataFim()+
                    "\n Seguradora "+super.getSeguradora().getNome()+
                    "Cliente CPF "+cliente.getCPF()+
                    "Veiculo placa "+veiculo.getPlaca()+"\n";
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

    // Metodo para gerar um sinistro de condutores;
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro,
                                  Condutor condutorSinistro, Seguradora seguradoraSinistro){
        boolean temCondutor = false;
        Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, condutorSinistro, this);

        // Verificar se o condutor eh valido, em caso afirmativo gera sinistro caso contrario nao;
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
        // Adciona o sinistro na lista de sinistros do condutor;
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
        int idade = cliente.getIdade();
        int qtdVeiculos = cliente.getListaVeiculos().size();
        int qtdSinistrosCliente = this.getListaSinistros().size(); // Sinistros do cliente estao armazenados na classe Seguro
        int qtdSinistrosCondutores = 0; // Sinistros dos condutores estao armazenados na classe Condutor
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        double valorMensal, fatorIdade = 0;

        if (idade < 30){
            fatorIdade = CalcSeguro.FATOR_MENOR_30.getFator();
        }
        else if (idade >= 30 && idade <= 60){
            fatorIdade = CalcSeguro.FATOR_30_60.getFator();
        }
        else if (idade > 60){
            fatorIdade = CalcSeguro.FATOR_MAIOR_60.getFator();
        }

        for(Condutor cond: this.getListaCondutores()){
            qtdSinistrosCondutores += cond.getListaSinistros().size();
        }

        valorMensal = valorBase*fatorIdade*(1+1/(qtdVeiculos+2))*
                      (2+qtdSinistrosCliente/10)*(5+qtdSinistrosCondutores/10);

        this.setValorMensal(valorMensal);
    }
}
