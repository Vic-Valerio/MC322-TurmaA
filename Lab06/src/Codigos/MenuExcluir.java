package Codigos;
public enum MenuExcluir {
    EXCLUIR_CLIENTE (1),
    EXCLUIR_VEICULO (2),
    EXCLUIR_SINISTRO (3),
    VOLTAR (4);

    public final int operacao;

    MenuExcluir(int operacao){
        this.operacao = operacao;
    }
    public int getOperacao(){
        return operacao;
    }

    public static MenuExcluir obterOperacao(int ope){
        for(MenuExcluir item: MenuExcluir.values()){
            if (item.operacao == ope)
                return item;
        }
        return null;
    }
}