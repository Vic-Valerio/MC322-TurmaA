import java.time.LocalDate;
import java.util.Scanner;

public enum MenuCadastrar {
    CADASTRAR_CLIENTE (1),
    CADASTRAR_VEICULO (2),
    CADASTRAR_SEGURADORA (3),
    VOLTAR (4);

    public final int operacao;
    private static Scanner teclado = new Scanner(System.in);

    MenuCadastrar(int operacao){
        this.operacao = operacao;
    }
    public int getOperacao(){
        return operacao;
    }
    // Passar essa função para a classe ImplementaMenu
    public static void implementaMenuCadastrar(){
        int ope;
        System.out.println("O que voce quer cadastrar?\n" +
                        "(1) Cliente\n" +
                        "(2) Veiculo\n" +
                        "(3) Seguradora\n" +
                        "(4) Voltar\n");
        ope = teclado.nextInt();
        teclado.nextLine();
        switch(ope){
            case 1: // Cadastrar cliente
                Seguradora s; // buscar na listaSeguradoras da classe ImplementarMenu
                
                System.out.println("Opção escolhida: Cadastrar cliente\n" +
                                    "Insira o nome do cliente\n");
                String nome = teclado.nextLine();
                while (!Validacao.validaNome(nome)){
                    System.out.println("Insira o nome do cliente novamente apenas com caracteres alfabeticos:\n");
                    nome = teclado.nextLine();
                }
                System.out.println("Insira o endereço\n");
                String endereco = teclado.nextLine();

                System.out.println("Insira o tipo de cliente\n"+
                                    "(PF) Pessoa fisica\n" +
                                    "(PJ) Pessoa juridica\n");
                String tipoCliente = teclado.nextLine();

                if(tipoCliente == "PF"){
                    System.out.println("Insira a data da licença no formato DD/MM/AAA\n");
                    String dataLicenca = teclado.nextLine(); // converter para LocalDate

                    System.out.println("Insira o nivel de educação\n");
                    String educacao = teclado.nextLine();

                    System.out.println("Insira o gênero\n");
                    String genero = teclado.nextLine();

                    System.out.println("Insira a classe econômica\n");
                    String classeEconomica = teclado.nextLine();

                    System.out.println("Insira o CPF\n");
                    String CPF = teclado.nextLine();
                    while(!Validacao.validarCPF(CPF)){
                        System.out.println("CPF invalido, insira novamente\n");
                        CPF = teclado.nextLine();
                    }
                    System.out.println("Insira a data de nascimento\n");
                    String dataNascimento = teclado.nextLine(); // converter para LocalDate

                    ClientePF c1 = new ClientePF(nome, endereco,MenuCadastrar.converteDataStrToLD(dataLicenca), educacao, genero,
                                                classeEconomica, CPF,MenuCadastrar.converteDataStrToLD(dataNascimento));
                    // corrigir os objetos do tipo Date;
                    s.registerCliente(c1);
                }

                if(tipoCliente == "PJ"){
                    System.out.println("Insira o CNPJ\n");
                    String CNPJ = teclado.nextLine();
                    while(!Validacao.validarCNPJ(CNPJ)){
                        System.out.println("CNPJ invalido, insira novamente\n");
                        CNPJ = teclado.nextLine();
                    }
                    System.out.println("Insira a data de fundação da empresa\n");
                    String dataFundacao = teclado.nextLine(); // converter para LocalDate
                    System.out.println("Insira a quantidade de funcionarios da empresa\n");
                    int qtdFuncionarios = teclado.nextInt();

                    ClientePJ c2 = new ClientePJ (nome, endereco, CNPJ, MenuCadastrar.converteDataStrToLD(dataFundacao), qtdFuncionarios);
                    
                    s.registerCliente(c2);
                }
                break;
            
            case 2: // Cadastrar veiculo
                Cliente c; // Buscar cliente na listaClientes da seguradora X

                System.out.println("""
                    Opção escolhida: Cadastrar veiculo\n
                    Insira a placa do veiculo\n""");
                String placa = teclado.nextLine();

                System.out.println("Insira o modelo do veiculo\n");
                String modelo = teclado.nextLine();

                System.out.println("Insira a marca do veiculo\n");
                String marca = teclado.nextLine();

                System.out.println("Insira o ano de fabricação\n");
                int anoFabricacao = teclado.nextInt();
                teclado.nextLine();

                Veiculo v = new Veiculo(placa, modelo, marca, anoFabricacao);
                c.registerVeiculo(v);
                break;

            case 3: // Cadastrar seguradora
                System.out.println("""
                                    Opção escolhida: Cadastrar seguradora\n
                                    Insira o nome da seguradora:\n""");
                nome = teclado.nextLine();

                System.out.println("Insira o telefone da seguradora no padrao (DDD)XXXXX-XXXX:\n");
                String telefone = teclado.nextLine();

                System.out.println("Insira o email da seguradora:\n");
                String email = teclado.nextLine();

                System.out.println("Insira o endereco da seguradora:\n");
                endereco = teclado.nextLine();

                Seguradora s1 = new Seguradora(nome, telefone, email, endereco);
                // Adicionar seguradora em um ArrayList
                // ListaSeguradoras.add(s1);
                break;
            
            case 4: // Voltar
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }

    }
    // Metodo para converter uma string para LocalDate separando a String
    public static LocalDate converteDataStrToLD(String data){
        String dataSplitted[] = data.split("/");
        return LocalDate.of(Integer.valueOf(dataSplitted[2]), Integer.valueOf(dataSplitted[1]),
                            Integer.valueOf(dataSplitted[0]));
    }
}
