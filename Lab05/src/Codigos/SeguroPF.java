package Codigos;
import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    // Metodo construtor;
    public SeguroPF(LocalDate dataFim, Seguradora seguradora,
                    Veiculo veiculo, ClientePF cliente) {
        super(dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorMensal = calcularValor();
    }
    
    // Metodos de acesso;
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
                    "\n Seguradora "+super.getSeguradora().getNome()+
                    "\nCliente CPF "+cliente.getIdentificador()+
                    "\nVeiculo placa "+veiculo.getPlaca()+"\n";
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
            System.out.println("Condutor "+condutor.getCpf()+" já esta desabilitado\n");
            return false;
        }        
    }

    // Metodo para gerar um sinistro de condutores;
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro,
                                  Condutor condutorSinistro){
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
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro){
    Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, null, this);
    if(this.getListaSinistros().add(sinistro)){
        System.out.println("Sinistro gerado com sucesso\n");
        return true;
    }    
    return false;
    }

    // Metodo para calcular o valor mensal do seguro;
    public double calcularValor(){
        int idade = cliente.getIdade();
        int qtdVeiculos = 1; // quando o seguro esta sendo gerado pela 1a vez, tem um veiculo associado mesmo que a lista de seguros seja vazia;
        int qtdSinistrosCliente = 0; // Sinistros do cliente estao armazenados na classe Seguro
        int qtdSinistrosCondutores = 0; // Sinistros dos condutores estao armazenados na classe Condutor
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        double valorMensal = 0, fatorIdade = 0;
         
        boolean seguroExiste = false;
        for(Seguro seguro: this.getSeguradora().getListaSeguros()){
            if(seguro.getId()==this.getId()){
                seguroExiste = true;
                break;
            }
        }
        // Se o seguro ja existe significa que eu quero apenas atualizar o valor (condutor habilitado)
        if(!seguroExiste){
            for(Seguro seguro: this.getSeguradora().getListaSeguros()){
                if(seguro.getCliente().getIdentificador().equals(this.cliente.getIdentificador())){
                    // 1 seguro PF esta relacionado a apenas 1 veiculo do cliente PF;
                    qtdVeiculos++;                
                }
            }
        }
        // Atribui o fator idade;
        if (idade < 30){
            fatorIdade = CalcSeguro.FATOR_MENOR_30.getFator();
        }
        else if (idade >= 30 && idade <= 60){
            fatorIdade = CalcSeguro.FATOR_30_60.getFator();
        }
        else if (idade > 60){
            fatorIdade = CalcSeguro.FATOR_MAIOR_60.getFator();
        }
        
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

        /*
        System.out.println("Sinistros condutores : "+qtdSinistrosCondutores+"\n");
        System.out.println("Sinistros cliente : "+qtdSinistrosCliente+"\n");
        System.out.println("veiculos segurados: "+qtdVeiculos+"\n");
        */
        
        valorMensal = valorBase*fatorIdade*(1+ 1.0/(qtdVeiculos+2))*(2+qtdSinistrosCliente/10.0)*(5+qtdSinistrosCondutores/10.0);
                      
        System.out.println("Valor mensal: "+valorMensal+"\n");
        return valorMensal;
    }
}
