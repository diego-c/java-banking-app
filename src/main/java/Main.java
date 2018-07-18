package src.main.java;

public class Main {
    public static void main(String[] args) {
        /*CLI cli = CLI.getInstance();
        cli.iniciar();*/
        ClienteDAO dao = new ClienteDAO();
        dao.findClientePorNome("diego");
    }
}
