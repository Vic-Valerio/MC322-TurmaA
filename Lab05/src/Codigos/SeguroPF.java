package Codigos;

public class SeguroPF {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Veiculo veiculo, ClientePF cliente) {
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public ClientePF getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
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

    // Metodo para gerar um novo sinistro
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro, Condutor condutorSinistro,
                                  Seguro seguroSinistro, Seguradora seguradoraSinistro){
        boolean temCondutor = false, temSeguro = false;
        Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, condutorSinistro, seguroSinistro);

        // Verificar se o cliente eh valido, em caso afirmativo gera sinistro caso contrario nao;
        for (Condutor cond: seguroSinistro.getListaCondutores()){
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

        // Verifica s o seguro esta contido na seguradora
        for(Seguro seguro: seguradoraSinistro.getListaSeguros()){
            if(seguro.getId() == seguroSinistro.getId()){
                temSeguro = true;
                break;
            }
        }
        if(!temSeguro){
            System.out.println("Seguro não existente, sinistro não pode ser gerado\n");
            return false;
        }
        return seguroSinistro.getListaSinistros().add(sinistro);
    }

    // Metodo para calcular o valor mensal do seguro
    public void calcularValor(Seguro seguro){
        int idade = cliente.getIdade();
        int qtdVeiculos = cliente.getListaVeiculos().size();
        int qtdSinistrosCliente = seguro.getListaSinistros().size(); // Sinistros do cliente estao armazenados na classe Seguro
        int qtdSinistrosCondutores = 0; // Sinistros dos condutores estao armazenados na classe Condutor
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        double valorMensal, fatorIdade = 0;

        if (idade < 30){
            fatorIdade = CalcSeguro.FATOR_MENOR_30.getFator();
        }
        if (idade >= 30 && idade <= 60){
            fatorIdade = CalcSeguro.FATOR_30_60.getFator();
        }
        if (idade > 60){
            fatorIdade = CalcSeguro.FATOR_MAIOR_60.getFator();
        }

        for(Condutor cond: seguro.getListaCondutores()){
            qtdSinistrosCondutores += cond.getListaSinistros().size();
        }

        valorMensal = valorBase*fatorIdade*(1+1/(qtdVeiculos+2))*
                      (2+qtdSinistrosCliente/10)*(5+qtdSinistrosCondutores/10);

        seguro.setValorMensal(valorMensal);
    }
}
