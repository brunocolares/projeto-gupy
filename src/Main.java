import modelos.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// @author: Bruno Colares em 19/06/2024
public class Main {
    public static void main(String[] args) {

        List<Funcionario> funcionarioList = new ArrayList<>();
        funcionarioList.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarioList.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarioList.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarioList.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarioList.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarioList.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarioList.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarioList.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarioList.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarioList.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarioList.removeIf(list -> list.getNome().equals("João"));

        System.out.println(funcionarioList);

        for (Funcionario funcionario : funcionarioList) {
            funcionario.aumentarSalario(10);
        }

        System.out.println(funcionarioList);

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarioList.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("- Funcionários agrupos por função -");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função de " + funcao +": ");
            listaFuncionarios.forEach(System.out::println);
            System.out.println("-");
        });

        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        funcionarioList.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        System.out.println("Funcionário com a maior idade:");
        Funcionario funcionarioMaisVelho = funcionarioList.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        System.out.println("O funcionário mais velho é o " + funcionarioMaisVelho.getNome() + " com " + funcionarioMaisVelho.calcularIdade() + " anos");

        System.out.println("Lista de funcionários em ordem alfabética:");
        funcionarioList.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        System.out.println("Total dos salários dos funcionários:");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarioList) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.printf("O Total dos salários dos funcionários é: " + "R$ " + String.format("%,.2f", totalSalarios));

        System.out.println("Quantidade de salários mínimos por funcionário:");
        for (Funcionario funcionario : funcionarioList) {
            BigDecimal salarioMinimo = new BigDecimal("1212.00");
            BigDecimal salarioDoFuncionario = funcionario.getSalario();
            BigDecimal qntSalariosMinimos = salarioDoFuncionario.divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println(funcionario.getNome() + ": " + qntSalariosMinimos + " salários mínimos");
        }
    }
}