public enum MenuCadastrar {
    CADASTRAR_CLIENTE (1),
    CADASTRAR_VEICULO (2),
    CADASTRAR_SEGURADORA (3),
    VOLTAR (4);

    public final int operacao;
    Scanner teclado = new Scanner(System.in);
    Seguradora seguradora;
    Cliente cliente;

    MenuCadastrar(int operacao){
        this.operacao = operacao;
    }
    public int getOperacao(){
        return operacao;
    }

    void implementaMenuCadastrar(int operacao){
        switch(operacao){
            case 1: // Cadastrar cliente
                Seguradora s; // Qual seguradora?
                
                System.out.println("Opção escolhida: Cadastrar cliente\n
                                    Insira o nome do cliente\n");
                String nome = teclado.nextLine();
                while (!validarNome(nome)){
                    System.out.println("Insira o nome do cliente novamente apenas com caracteres alfabeticos:\n");
                    nome = teclado.nextLine();
                }
                System.out.println("Insira o endereço\n");
                String endereco = teclado.nextLine();

                System.out.println("Insira o tipo de cliente\n
                                    (PF) Pessoa fisica\n
                                    (PJ) Pessoa juridica\n");
                String tipoCliente = teclado.nextLine();

                if(tipoCliente == PF){
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
                    while(!validarCPF(CPF)){
                        System.out.println("CPF invalido, insira novamente\n");
                        CPF = teclado.nextLine();
                    }
                    System.out.println("Insira a data de nascimento\n");
                    String dataNascimento = teclado.nextLine(); // converter para LocalDate

                    ClientePF c1 = new ClientePF(nome, endereco, dataLicenca, educacao, genero,
                                                classeEconomica, CPF, dataNascimento);
                    
                    cadastrarCliente (s, c1);
                }

                if(tipoCliente == PJ){
                    System.out.println("Insira o CNPJ\n");
                    String CNPJ = teclado.nextLine();
                    while(!validarCNPJ(CNPJ)){
                        System.out.println("CNPJ invalido, insira novamente\n");
                        CNPJ = teclado.nextLine();
                    }
                    System.out.println("Insira a data de fundação da empresa\n");
                    String dataFundacao = teclado.nextLine(); // converter para LocalDate
                    System.out.println("Insira a quantidade de funcionarios da empresa\n");
                    int qtdFuncionarios = teclado.nextInt();

                    ClientePJ c2 = new Cliente (nome, endereco, CNPJ, dataFundacao, qtdFuncionarios);
                    cadastrarCliente (s, c2);

                }
                
                break;
            
            case 2: // Cadastrar veiculo
                Cliente c; // Qual cliente?
                System.out.println("Opção escolhida: Cadastrar veiculo\n
                                    Insira a placa do veiculo\n");
                String placa = teclado.nextLine();

                System.out.println("Insira o modelo do veiculo\n");
                String modelo = teclado.nextLine();

                System.out.println("Insira a marca do veiculo\n");
                String marca = teclado.nextLine();

                System.out.println("Insira o ano de fabricação\n");
                String anoFabricacao = teclado.nextLine();

                Veiculo v = new Veiculo(placa, modelo, marca, anoFabricacao);
                cadastrarVeiculo(c, v)
                break;

            case 3: // Cadastrar seguradora
                System.out.println("Opção escolhida: Cadastrar seguradora\n
                                    Insira o nome da seguradora:\n");

                String nome = teclado.nextLine();
                while (!validarNome(nome)){
                    System.out.println("Insira novamente o nome da seguradora somente com caracteres alfabeticos:\n");
                    nome = teclado.nextLine();
                }

                System.out.println("Insira o telefone da seguradora no padrao (DDD)XXXXX-XXXX:\n");
                String telefone = telcado.nextLine();

                System.out.println("Insira o email da seguradora:\n");
                String email = email.nextLine();

                System.out.println("Insira o endereco da seguradora:\n");
                String endereco = endereco.nextLine();

                Seguradora s1 = new Seguradora(nome, telefone, email, endereco);
                // Adicionar seguradora em um ArrayList;
                break;
            
            case 4: // Voltar
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }

    }

    boolean cadastrarCliente (Seguradora s, Cliente c){
        if (s.listaClientes.contains(c))
            return false;
        else
            return s.listaClientes.add(c);
    }

    boolean cadastrarVeiculo(Cliente c, Veiculo v){
        return c.registerVeiculo(v);
    }
}
