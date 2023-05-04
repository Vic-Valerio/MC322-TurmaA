public class ImplementarMenu {
    //Definir se vai ser tudo private;
    Scanner teclado = new Scanner(System.in);
    final int operacao;

    // inicializar a operação no construtor? 
    public ImplementarMenu(int operacao){
        this.operacao = operacao;
    }

    // Cadastrar uma seguradora para iniciar
    String nome;
    String telefone;
    String email;
    String endereco;
    System.out.println("Cadastre uma seguradora para começar\n
                        Insira o nome da seguradora:\n");

    nome = teclado.nextLine();
    while (!validarNome(nome)){
        System.out.println("Insira novamente o nome da seguradora somente com caracteres alfabeticos:\n");
        nome = teclado.nextLine();
    }

    System.out.println("Insira o telefone da seguradora no padrao (DDD)XXXXX-XXXX:\n");
    telefone = telcado.nextLine();
    System.out.println("Insira o email da seguradora:\n");
    email = email.nextLine();
    System.out.println("Insira o endereco da seguradora:\n");
    endereco = endereco.nextLine();

    // Instanciando um objeto Seguradora;
    Seguradora s1 = new Seguradora(nome, telefone, email, endereco);
    
    // Preciso criar uma lista para armazenar as seguradoras?

    System.out.println("O que voce quer realizar?\n
                        (1) CADASTRAR\n
                        (2) LISTAR\n
                        (3) EXCLUIR\n
                        (4) GERAR_SINISTRO\n
                        (5) TRANSFERIR_SEGURO\n
                        (6) CALCULAR_RECEITA_SEGURADORA\n
                        (0) SAIR\n");

    MenuPrincipal op;

    if(operacao == 1){
        op.operacao(1);
        // MenuCadastrar
        System.out.println("O que voce quer cadastrar?\n
                        (1) Cliente\n
                        (2) Veiculo\n
                        (3) Seguradora\n
                        (4) Voltar\n");
        operacao = teclado.nextLine();
        // Como saltar para a proxima classe do menu?
    }

    if (operacao == 2){

    }

    if(operacao == 3){

    }
    
    if (operacao == 4){

    }

    if(operacao == 5){

    }
    
    if (operacao == 6){

    }

    if (operacao == 0){

    }

    public boolean validarNome(String nome){
        if (Validacao.validaNome(nome)){
            System.out.println("Nome" + nome + "valido\n");
        }
        else
            System.out.println("Nome" + nome + "invalido\n");
        
        return Validacao.validaNome(nome);
    }


// Criar varios metodos a serem chamados para encapsular o codigo


// Escolha uma opçao abaixo....

// if (MenuPrincipal.CADASTRAR == opçao)

//    if (MenuCadastrar.CADASTRAR_CLIENTE == novaOpçao)
//     (chamar metodos para implementar ações);

}
