import java.util.Scanner;

public enum MenuPrincipal{
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    SAIR(0);

    public final int operacao;

    MenuPrincipal(int operacao){
        this.operacao = operacao;
    }
    public int getOperacao(){
        return operacao;
    }
//Passar esse metodo para a classe ImplementaMenu
    void implementaMenu(int operacao){
        Scanner teclado = new Scanner(System.in);

        switch (operacao) {
            case 1:
                MenuCadastrar.implementaMenuCadastrar(ope);
                
                break;
            
        
            case 2:
                System.out.println("""
                O que voce quer listar?\n
                (1) Cliente por seguradora\n
                (2) Sinistros por seguradora\n
                (3) Sinistro por cliente\n
                (4) Veiculo por cliente\n
                (5) Veiculo por seguradora\n
                (6) Voltar\n""");

                ope = teclado.nextInt();
                teclado.nextLine();
                MenuListar.implementaMenuListar(ope);
                break;
            
            case 3:
                System.out.println("""
                O que voce quer excluir?\n
                (1) Cliente\n
                (2) Veiculo\n
                (3) Sinistro\n
                (4) Voltar\n""");
                ope = teclado.nextInt();
                teclado.nextLine();
                MenuExcluir.implementaMenuExcluir(ope);
                break;

            case 4:

                break;

            case 5:

                break;
            
            case 6: 

                break;

            case 0:

                break;
        }
    }
}