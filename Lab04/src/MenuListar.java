public enum MenuListar {
    LISTAR_CLIENTE_POR_SEGURADORA (1),
    LISTAR_SINISTRO_POR_SEGURADORA (2),
    LISTAR_SINISTRO_POR_CLIENTE (3),
    LISTAR_VEICULO_POR_CLIENTE (4),
    LISTAR_VEICULO_POR_SEGURADORA (5),
    VOLTAR (6);

    public final int operacao;

    MenuListar(int operacao){
        this.operacao = operacao;
    }
    public int getOperacao(){
        return operacao;
    }
}