public class ImplementarMenu {
    //Definir se vai ser tudo private;
    Scanner teclado = new Scanner(System.in);
    String nome;
    String telefone;
    String email;
    String endereco;

    public ImplementarMenu() {
    }

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
