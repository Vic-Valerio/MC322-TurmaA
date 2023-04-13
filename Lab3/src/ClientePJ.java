import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {
    private String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca, List<Veiculo> listaVeiculos,
                     String CNPJ, Date dataFundacao) {

        super(nome, endereco, dataLicenca, listaVeiculos);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
    }
    
    // Metodos de acesso
    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    //Metodo de conversao para string
    public String toString() {
        String str = "";
        str += "informacoes de pessoa juridica:\n" 
                    + "CNPJ: " + CNPJ +"\n" 
                    + "Fundação: " + dataFundacao +"\n";
        return str;
    }
    
    // Metodo para validar o CNPJ informado
    public boolean validarCNPJ(String CNPJ) {
        char DV1, DV2; // Digito Validador;
        int multiplicador, resto, soma, i; // Variaveis para calculo DV1 DV2

        // Converte o CNPJ para apenas numeros, retirando qualquer caractere nao numerico
        String newCNPJ = CNPJ.replaceAll("[^0-9]", "");
    
        // Se o CNPJ não tiver 14 digitos retorna falso (invalido)
        if(newCNPJ.length() != 14) {
            return false;
        }

        // Se o CNPJ for uma sequencia de numeros repetidos retorna falso (invalido)
        if(newCNPJ.equals("00000000000000") || newCNPJ.equals("11111111111111") 
        || newCNPJ.equals("22222222222222") || newCNPJ.equals("33333333333333")
        || newCNPJ.equals("44444444444444") || newCNPJ.equals("55555555555555")
        || newCNPJ.equals("66666666666666") || newCNPJ.equals("77777777777777")
        || newCNPJ.equals("88888888888888") || newCNPJ.equals("99999999999999"))
        {   
            return false;
        }
    
        // Calculo do primeiro digito verificador
        multiplicador = 2;
        soma = 0;
        for(i=11; i >= 0; i--){
            soma = soma + (int)(newCNPJ.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do CNPJ para numero inteiro 
            multiplicador += 1;
            if (multiplicador == 10){
                multiplicador = 2;
            }
        }
        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV1 = '0';
        }
        else{
            DV1 = (char)(resto + 48); // retorna o 13o digito do CNPJ para um char
        }
        // Verifica se o digito verificador corresponde ao digito informado 
        if(DV1 != newCNPJ.charAt(12)){
            return false;
        }

        // Calculo do segundo digito verificador somente caso o DV1 esteja correto
        multiplicador = 2;
        soma = 0;
        for(i=12; i >= 0; i--){
            soma = soma + (int)(newCNPJ.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do CNPJ para numero inteiro 
            multiplicador += 1;
            if (multiplicador == 10){
                multiplicador = 2;
            }
        }
        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV2 = '0';
        }
        else{
            DV2 = (char)(resto + 48); // retorna o 13o digito do CNPJ para um char
        }
        // Checando o 2o digito verificador do CNPJ informado
        if(DV2 != newCNPJ.charAt(13)){
            return false;
        }
        return true;
    }
}

