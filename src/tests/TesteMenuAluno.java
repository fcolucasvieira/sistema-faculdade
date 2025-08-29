package tests;
import views.MenuAluno;

public class TesteMenuAluno {
    public static void main(String[] args) {
        try {
            MenuAluno.exibir();
        } catch (Exception e) {
            System.err.println("Erro ao executar MenuAluno: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
