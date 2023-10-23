package candidatura;

// Melhoria: Percorrer todas listas, mesmo que ja selecionados os 5. Escolher os 5 melhores e criar uma lista de reservas, caso mais de 5 atendam aos requisitos.
// Classificar reservas por ordem de pedida salarial.
// Introduzir as ligações aos selecionados. 3 tentativas de contato usando booleano. Caso não atenda algum, chamar reserva.

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

public class ProcessoSeletivo {
    public static void main(String[] args) {
        ProcessoSeletivo ps = new ProcessoSeletivo();
        ps.selecaoCandidatos();
    }
    public void selecaoCandidatos() {
        // Lista de candidatos concorrentes
        String [] candidatos = {"FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JOAQUIM"};
        // Lista para armazenar os candidatos que atendem os critérios para entrevista
        Map<String, Double> map = new TreeMap<>();
        Set<SalarioEmpregado> salarioEmpregado = new TreeSet<>(Comparator.comparing(SalarioEmpregado::getSalario));
        // Contador de candidatos selecionados
        int candidatosSelecionados = 0;
        // Contador para percorrer array
        int candidatoAtual = 0;
        // Salario limite escolhido pela "empresa"
        double salarioBase = 2000.0;

        // Loop para verificar se o limite de 5 candidatos já foi preenchido e se todos os nomes da lista de concorrentes já foram verificados
        while(candidatoAtual < candidatos.length) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            salarioEmpregado.add(new SalarioEmpregado(candidato, salarioPretendido));

            System.out.printf("O(A) candidato(a) %s solicitou este valor de salário: R$ %.2f\n",candidato,salarioPretendido);
            if(salarioBase >= salarioPretendido) {
                map.put(candidato,salarioPretendido);
                candidatosSelecionados++;
            }
            candidatoAtual++;
        }

        // Exibe os candidatos selecionados
        int i=1;
        System.out.println();
        System.out.println("----------Candidatos selecionados----------");
        for (Map.Entry<String, Double> entry : map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).toList()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            if(i==6){
                System.out.println();
                System.out.println("------------Lista de espera------------");
            }

            System.out.printf("%d° candidato --> %s - R$ %.2f\n",i,key,value);
            i++;
        }

        System.out.println();
        System.out.println("_____________________________________________________________________________________________");
        System.out.println();
        System.out.println("----------Candidatos selecionados----------");

        salarioEmpregado.removeIf(x -> x.getSalario() > salarioBase );

        i=1;
        for (SalarioEmpregado sal : salarioEmpregado) {
            String key = sal.getNome();
            Double value = sal.getSalario();

            if(i==6){
                System.out.println();
                System.out.println("------------Lista de espera------------");
            }

            System.out.printf("%d° candidato --> %s - R$ %.2f\n",i,key,value);
            i++;
        }
    }
    double valorPretendido() {
        // Gera valor aleatório de salário para os concorrentes
        return RandomGenerator.getDefault().nextDouble(1800.00,2200.00); //  ThreadLocalRandom.current().nextDouble(1800,2200);
    }

    class SalarioEmpregado{

        private Double salario;
        private String nome;
        SalarioEmpregado(String nome, Double salario){
            this.salario = salario;
            this.nome = nome;
        }

        public Double getSalario() {
            return salario;
        }

        public void setSalario(Double salario) {
            this.salario = salario;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SalarioEmpregado that = (SalarioEmpregado) o;
            return Objects.equals(nome, that.nome);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nome);
        }
    }
}

