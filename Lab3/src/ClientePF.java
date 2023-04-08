import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {
    private String CPF;
    private Date dataNascimento;
    private String educacao;
    private String genero;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao,
                     String genero, String classeEconomica, List<Veiculo> listaVeiculos,
                     String CPF, Date dataNascimento) {

        super(nome, endereco, dataLicenca, listaVeiculos);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
    }

    // Metodos de acesso
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    //Metodo de conversao para string
    public String toString() {
        String str = "informacoes de pessoa fisica:\n" + "CPF: " + CPF +"\n" 
                    + "Nascimento: " + dataNascimento +"\n" 
                    + "Gênero: " + genero +"\n"
                    + "Escolaridade: " + educacao +"\n"
                    + "Classe econômica" + classeEconomica + "\n";
        return str;
    } 

    // Metodo para validar o CPF informado
    public boolean validarCPF(String CPF) {
        char DV1, DV2; // Digito Validador;
        int multiplicador, resto, soma, i; // Variaveis para calculo DV1 DV2

        // Converte o CPF para apenas numeros, retirando qualquer caractere nao numerico
        String newCPF = CPF.replaceAll("[^0-9]", ""); // nao ta funcionando
       
        // Se o CPF não tiver 11 digitos retorna falso (invalido)
        if(newCPF.length() != 11) {
            return false;
        }

        // Se o CPF for uma sequencia de numeros repetidos retorna falso (invalido)
        if(newCPF.equals("00000000000") || newCPF.equals("11111111111") 
        || newCPF.equals("22222222222") || newCPF.equals("33333333333")
        || newCPF.equals("44444444444") || newCPF.equals("55555555555")
        || newCPF.equals("66666666666") || newCPF.equals("77777777777")
        || newCPF.equals("88888888888") || newCPF.equals("99999999999"))
        {   
            return false;
        }
       
        // Calculo do primeiro digito verificador
        multiplicador = 10;
        soma = 0;
        for(i=0; i < 9; i++){
            soma = soma + (int)(newCPF.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do CPF para numero inteiro 
            multiplicador -= 1;
        }
        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV1 = '0';
        }
        else{
            DV1 = (char)(resto + 48); // retorna o decimo digito do CPF para um char
        }
        
        if(DV1 != newCPF.charAt(9)){
            return false;
        }

        // Calculo do segundo digito verificador
        multiplicador = 11;
        soma = 0;
        for (i=0; i < 10; i++){
            soma = soma + (int)(newCPF.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do CPF para numero inteiro 
            multiplicador -= 1;
        }

        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV2 = '0';
        }
        else
            DV2 = (char)(resto + 48); // retorna o decimo digito do CPF para um char

        // Checando o 2o digito verificador do CPF informado
        if(DV2 != newCPF.charAt(10)){
            return false;
        }
        return true;
    }
}