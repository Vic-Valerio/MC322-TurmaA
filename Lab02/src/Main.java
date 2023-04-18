public class Main {
    public static void main(String[] args){
        // Testes para a classe Cliente

        // c1 eh um cliente valido com CPF verificado
        Cliente c1 = new Cliente("Victor", "437.355.398-07", "12/03/2001", 22,"Rua do RS e RA");
        // c2 eh um cliente invalido pelo DV1 do CPF estar errado (DV2 nao importa pois ja eh invalidado)
        Cliente c2 = new Cliente("Yudi", "021.400.289-22", "04/09/1992", 31,"Rua Japones do Playstation 3");
        // c3 eh um cliente invalido pelo DV2 do CPF estar errado, nesse caso DV1 esta correto
        Cliente c3 = new Cliente("Maisinha", "123.487.478-42", "22/05/2002", 20,"Rua Rooooosaa Joao?");
        // c4 eh um cliente invalido pelo CPF ser invalido
        Cliente c4 = new Cliente("Priscila", "111.111.111-11", "19/06/1996", 26,"Rua Roda a Roleta");
        // c5 eh um cliente invalido pelo CPF ser ter mais de 11 digitos
        Cliente c5 = new Cliente("Juca", "123.654.983-241", "11/04/1999", 23,"Rua dor na nuca");

        if(c1.validarCpf(c1.getCpf())){ // Se o CPF for valido
            System.out.println("CPF " + c1.getCpf() + " eh valido!\n");
        } 
        else // Se for invalido
            System.out.println("CPF " + c1.getCpf() + " invalido, verificar e tentar novamente\n");

        if(c2.validarCpf(c2.getCpf())){ // Se o CPF for valido
            System.out.println("CPF " + c2.getCpf() + " eh valido!\n");
        } 
        else // Se for invalido
            System.out.println("CPF " + c2.getCpf() + " invalido, verificar e tentar novamente\n");
        
            if(c3.validarCpf(c3.getCpf())){ // Se o CPF for valido
            System.out.println("CPF " + c3.getCpf() + " eh valido!\n");
        } 
        else // Se for invalido
            System.out.println("CPF " + c3.getCpf() + " invalido, verificar e tentar novamente\n");

        if(c4.validarCpf(c4.getCpf())){ // Se o CPF for valido
            System.out.println("CPF " + c4.getCpf() + " eh valido!\n");
        } 
        else // Se for invalido
            System.out.println("CPF " + c4.getCpf() + " invalido, verificar e tentar novamente\n");
        
        if(c5.validarCpf(c5.getCpf())){ // Se o CPF for valido
            System.out.println("CPF " + c5.getCpf() + " eh valido!\n");
        } 
        else // Se for invalido
            System.out.println("CPF " + c5.getCpf() + " invalido, verificar e tentar novamente\n");
        
        // Testes para a classe Sinistro: IDs distintos e unicos para cada objeto
        Sinistro sinis1 = new Sinistro("28/02/2023", "Rua Branca de neve e seus anoes, numero 7");
        System.out.println("sinis1 ID: " + sinis1.getId() + "\n");

        Sinistro sinis2 = new Sinistro("01/01/1968", "Rua Bela zzzzzz");
        System.out.println("sinis2 ID: " + sinis2.getId() + "\n");

        //Testes para a classe Seguradora
        Seguradora seguradora = new Seguradora("Bom dia & cia", "4002-8922", "Infancia@bomdiaecia.sbt", "Rua ganhador do jogo da vida");
        System.out.println("seguradora cadastrada com sucesso: \n" + "Seguradora "+seguradora.getNome()+ " Telefone " + seguradora.getTelefone()+"\n"+ seguradora.getEndereco()+"\n");

        //Testes para a classe Veiculo
        Veiculo carro1 = new Veiculo("AB01C23", "Honda", "Civic");
        System.out.println("veiculo cadastrado com sucesso: \n" + carro1.getMarca()+ " " + carro1.getModelo()+"\n"+"Placa " + carro1.getPlaca()+"\n");
        
        Veiculo carro2 = new Veiculo("DE45F67", "Ford", "Ka");
        System.out.println("veiculo cadastrado com sucesso: \n" + carro2.getMarca()+ " " + carro2.getModelo()+"\n"+"Placa " + carro2.getPlaca());
    }    
}