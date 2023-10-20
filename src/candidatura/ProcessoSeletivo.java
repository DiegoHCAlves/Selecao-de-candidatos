package candidatura;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    public static void main(String[] args) {
        selecaoCandidatos();
    }
    public static void selecaoCandidatos() {
        // Lista de candidatos concorrentes
        String [] candidatos = {"FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JOAQUIM"};
        // Lista de candidatos que atendem os critérios para entrevista
        ArrayList<String> listaSelecionados = new ArrayList<>();
        // Contador de candidatos selecionados
        int candidatosSelecionados = 0;
        // Contador para percorrer array
        int candidatoAtual = 0;
        // Salario limite escolhido pela "empresa"
        double salarioBase = 2000.0;

        // Loop para verificar se o limite de 5 candidatos já foi preenchido e se todos os nomes da lista de concorrentes já foram verificados
        while(candidatosSelecionados < 5 && candidatoAtual < candidatos.length) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            System.out.printf("O(A) candidato(a) %s solicitou este valor de salário: %.2f\n",candidato,salarioPretendido);
            if(salarioBase >= salarioPretendido) {
                listaSelecionados.add(candidato);
                candidatosSelecionados++;
            }
            candidatoAtual++;
        }
        // Exibe os candidatos selecionados
        listaSelecionados.forEach(nome -> System.out.println(" --> " + nome + " foi selecionado(a) para a entrevista"));
    }
    static double valorPretendido() {
        // Gera valor aleatório de salário para os concorrentes
        return ThreadLocalRandom.current().nextDouble(1800,2200);
    }
}
