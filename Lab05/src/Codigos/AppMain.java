package Codigos;
public class AppMain {
    public static void main(String[] args) throws Exception {
        // Objetos instanciados considerando todos os dados validos
        // sem a necessidade de aplicar os metodos de validação;
        Seguradora seguradora = new Seguradora("44.668.939/0001-02", "Sol Seguros", "19 99547-8236",
                                "solseguros@gmail.com", "Rua do castor, 1028");

        // Instanciando 2 clientes PF com CPFs validos;
        Cliente c1 = new ClientePF("Victor Valerio", "Rua Luverci Pereira", "(19)99161-8109", 
                     "v245146@dac.unicamp.br", "437.355.398-07", "Masculino",
                     "Superior incompleto", ImplementarMenu.converteDataStrToLD("15/03/2001"));

        Cliente c2 = new ClientePF("Luiz Almeida", "Rua Amazonas", "(31)99258-7496",
                        "luizalmeida@gmail.com", "431.295.130-60", "Não-binario",
                        "Ensino superior completo", ImplementarMenu.converteDataStrToLD("10/05/1997"));

        // Instanciando 2 clientes PJ com CNPJs validos;
        Cliente c3 = new ClientePJ("Programacao Basica LTDA", "Rua do Python, n_o MC102", "(19)3620-1215",
                        "programacaomc102@hotmail.com","45.773.100/0001-99",
                        ImplementarMenu.converteDataStrToLD("05/02/2010"));

        Cliente c4 = new ClientePJ("Programacao avancada LTDA", "Rua do C, n_o MC202", "(11)3084-4565",
                            "programacaomc202@gmail.com", "31.778.022/0001-00",
                            ImplementarMenu.converteDataStrToLD("20/06/2005"));

        // Instanciando duas frotas;
        Frota f1 = new Frota("Caminhoes");
        Frota f2 = new Frota("Veículos de passeio");

        // Instanciando veículos;
        Veiculo v1 = new Veiculo("ABC0123", "Honda", "Fit", 2012);
        Veiculo v2 = new Veiculo("ABC4567", "VW", "Gol", 1998);
        Veiculo v3 = new Veiculo("ABC0001", "Chevrolet", "Omega", 1994);

        Veiculo v4 = new Veiculo("FEM0369", "Toyota", "Corolla", 2018);
        Veiculo v5 = new Veiculo("FEM0147", "VW", "e-Delivery", 2021);
        Veiculo v6 = new Veiculo("FEM0258", "IVECO", "Tector", 2015);

        // Instanciando condutores
        
    }
}
