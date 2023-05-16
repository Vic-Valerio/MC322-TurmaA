package Codigos;
import java.time.LocalDate;
import java.time.Period;

public class Validacao {

    // Metodo estatico para validar o CPF informado;
    public static boolean validarCPF(String CPF) {
        char DV1, DV2; // Digito Validador;
        int multiplicador, resto, soma, i; // Variaveis para calculo DV1 DV2;

        // Converte o CPF para apenas numeros, retirando qualquer caractere nao numerico;
        String newCPF = CPF.replaceAll("[^0-9]", "");
       
        // Se o CPF não tiver 11 digitos retorna falso (invalido);
        if(newCPF.length() != 11) {
            return false;
        }

        // Se o CPF for uma sequencia de numeros repetidos retorna falso (invalido);
        if(newCPF.equals("00000000000") || newCPF.equals("11111111111") 
        || newCPF.equals("22222222222") || newCPF.equals("33333333333")
        || newCPF.equals("44444444444") || newCPF.equals("55555555555")
        || newCPF.equals("66666666666") || newCPF.equals("77777777777")
        || newCPF.equals("88888888888") || newCPF.equals("99999999999")){   
            
            return false;
        }
       
        // Calculo do primeiro digito verificador;
        multiplicador = 10;
        soma = 0;
        for(i=0; i < 9; i++){
            soma = soma + (int)(newCPF.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do CPF para numero inteiro;
            multiplicador -= 1;
        }
        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV1 = '0';
        }
        else{
            DV1 = (char)(resto + 48); // retorna o decimo digito do CPF para um char;
        }
        
        if(DV1 != newCPF.charAt(9)){
            return false;
        }

        // Calculo do segundo digito verificador;
        multiplicador = 11;
        soma = 0;
        for (i=0; i < 10; i++){
            soma = soma + (int)(newCPF.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do CPF para numero inteiro;
            multiplicador -= 1;
        }

        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV2 = '0';
        }
        else
            DV2 = (char)(resto + 48); // retorna o decimo digito do CPF para um char;

        // Checando o 2o digito verificador do CPF informado;
        if(DV2 != newCPF.charAt(10)){
            return false;
        }
        return true;
    }

    // Metodo estatico para validar o CNPJ informado;
    public static boolean validarCNPJ(String CNPJ){
        char DV1, DV2; // Digito Validador;
        int multiplicador, resto, soma, i; // Variaveis para calculo DV1 DV2;

        // Converte o CNPJ para apenas numeros, retirando qualquer caractere nao numerico;
        String newCNPJ = CNPJ.replaceAll("[^0-9]", "");
    
        // Se o CNPJ não tiver 14 digitos retorna falso (invalido);
        if(newCNPJ.length() != 14) {
            return false;
        }

        // Se o CNPJ for uma sequencia de numeros repetidos retorna falso (invalido);
        if(newCNPJ.equals("00000000000000") || newCNPJ.equals("11111111111111") 
        || newCNPJ.equals("22222222222222") || newCNPJ.equals("33333333333333")
        || newCNPJ.equals("44444444444444") || newCNPJ.equals("55555555555555")
        || newCNPJ.equals("66666666666666") || newCNPJ.equals("77777777777777")
        || newCNPJ.equals("88888888888888") || newCNPJ.equals("99999999999999")){

            return false;
        }
    
        // Calculo do primeiro digito verificador;
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
            DV1 = (char)(resto + 48); // retorna o 13o digito do CNPJ para um char;
        }
        // Verifica se o digito verificador corresponde ao digito informado;
        if(DV1 != newCNPJ.charAt(12)){
            return false;
        }

        // Calculo do segundo digito verificador somente caso o DV1 esteja correto;
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
            DV2 = (char)(resto + 48); // retorna o 14o digito do CNPJ para um char;
        }
        // Checando o 2o digito verificador do CNPJ informado;
        if(DV2 != newCNPJ.charAt(13)){
            return false;
        }
        return true;
    }

    // Metodo estatico para validar o nome (nao pode conter numeros e caracteres especiais);
    public static boolean validaNome(String nome){
        String expRegular = "^[a-zA-Z\\s]+";
        // contem letras minusculas e maiusculas e 1 espaço entre as demais strings(+);
        if (nome == "")
            return false;

        return nome.matches(expRegular);
    }

    // Metodo estatico para validar a idade do cliente PF;
    public static boolean validaNascimento(LocalDate dataNascimento){
        LocalDate dataHoje = LocalDate.now();
        Period p = Period.between(dataNascimento, dataHoje);
        
        if (p.getYears() >= 18){   
            return true;
        }
        return false;
    }
}
