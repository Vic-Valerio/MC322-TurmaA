public class Cliente {
    // Atributos instanciados (caracterizacao do objeto)
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

     // Metodo de construcao
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;     
    }

    // Metodos de acesso (getters and setters)
    public String getNome() {
        return nome;
    }
    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getendereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //Metodo de conversao para string
    public String toString() {
        String str = "informacoes do cliente:\n" + "Nome: " + nome +"\n" + "CPF: " + cpf +"\n" + "Nascimento: " + dataNascimento +"\n" + "Idade: " + idade +"\n" + "Endereco: " + endereco +"\n";
        return str;
    } 

    //Metodo para validar um CPF (algoritmo utilizado pela Receita Federal)

    public boolean validarCpf(String cpf) {
        char DV1, DV2; // Digito Validador;
        int multiplicador, resto, soma, i; // Variaveis para calculo DV1 DV2

        // Converte o CPF para apenas numeros, retirando qualquer caractere nao numerico
        String newCpf = cpf.replaceAll("[^0-9]", ""); // nao ta funcionando
       
        // Se o CPF não tiver 11 digitos retorna falso (invalido)
        if(newCpf.length() != 11) {
            return false;
        }

        // Se o CPF for uma sequencia de numeros repetidos retorna falso (invalido)
        if(newCpf.equals("00000000000") || newCpf.equals("11111111111") 
        || newCpf.equals("22222222222") || newCpf.equals("33333333333")
        || newCpf.equals("44444444444") || newCpf.equals("55555555555")
        || newCpf.equals("66666666666") || newCpf.equals("77777777777")
        || newCpf.equals("88888888888") || newCpf.equals("99999999999"))
        {   
            return false;
        }
       
        // Calculo do primeiro digito verificador
        multiplicador = 10;
        soma = 0;
        for(i=0; i < 9; i++){
            soma = soma + (int)(newCpf.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do cpf para numero inteiro 
            multiplicador -= 1;
        }
        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV1 = '0';
        }
        else{
            DV1 = (char)(resto + 48); // retorna o decimo digito do cpf para um char
        }
        
        if(DV1 != newCpf.charAt(9)){
            return false;
        }

        // Calculo do segundo digito verificador
        multiplicador = 11;
        soma = 0;
        for (i=0; i < 10; i++){
            soma = soma + (int)(newCpf.charAt(i)-48 )*multiplicador; // converte o i-esimo digito do cpf para numero inteiro 
            multiplicador -= 1;
        }

        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11){
            DV2 = '0';
        }
        else
            DV2 = (char)(resto + 48); // retorna o decimo digito do cpf para um char

        // Checando o 2o digito verificador do CPF informado
        if(DV2 != newCpf.charAt(10)){
            return false;
        }
        return true;
    }
}