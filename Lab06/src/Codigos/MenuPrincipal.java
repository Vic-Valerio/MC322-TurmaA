package Codigos;
public enum MenuPrincipal{
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    CALCULAR_RECEITA_SEGURADORA(5),
    SAIR(0);

    public final int operacao;

    MenuPrincipal(int operacao){
        this.operacao = operacao;
    }
    public int getOperacao(){
        return operacao;
    }
    public static MenuPrincipal obterOperacao(int ope){
        for(MenuPrincipal item: MenuPrincipal.values()){
            if (item.operacao == ope)
                return item;
        }
        return null;
    }
}