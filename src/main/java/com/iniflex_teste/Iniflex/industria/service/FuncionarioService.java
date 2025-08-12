package com.iniflex_teste.Iniflex.industria.service;

import com.iniflex_teste.Iniflex.industria.model.Funcionario;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {
  @Getter
  private final List<Funcionario> funcionarios = new ArrayList<>();
  private final DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private final DecimalFormat formatterMoeda = new DecimalFormat("#,##0.00");

  public void adicionar(Funcionario funcionario) {
    funcionarios.add(funcionario);
  }

  public void removerPorNome(String nome) {
    funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
  }

  public void imprimirTodos() {
    System.out.printf("%-12s %-12s %-15s %-15s%n",
          "Nome", "Data Nascimento", "Salário", "Função");
    System.out.println("-------------------------------------------------------------");
    funcionarios.forEach(f -> System.out.println(f.toString()));
  }

  public void aplicarAumento(BigDecimal percentual) {
    funcionarios.forEach(f -> {
      BigDecimal aumento = f.getSalario().multiply(percentual).setScale(2, RoundingMode.HALF_UP);
      f.setSalario(f.getSalario().add(aumento));
    });
  }

  public Map<String, List<Funcionario>> agruparPorFuncao() {
    return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
  }

  public void imprimirAgrupadoPorFuncao() {
    agruparPorFuncao().forEach((funcao, lista) -> {
      System.out.println("\nFunção: " + funcao);
      System.out.printf("%-15s %-15s%n",
            "Nome", "Função");
      lista.forEach(f ->
            System.out.printf("%-15s %-15s%n", f.getNome(), f.getFuncao())
      );
    });
  }

  public void imprimirAniversariantes(int... meses) {
    Set<Integer> mesesSet = Arrays.stream(meses).boxed().collect(Collectors.toSet());
    funcionarios.stream()
          .filter(f -> mesesSet.contains(f.getDataNascimento().getMonthValue()))
          .forEach(f -> System.out.printf("%s (%s)%n", f.getNome(), f.getDataNascimento().format(formatterData)));
  }

  public Funcionario getFuncionarioMaisVelho() {
    return funcionarios.stream()
          .min(Comparator.comparing(Funcionario::getDataNascimento))
          .orElse(null);
  }

  public void imprimirOrdemAlfabetica() {
    funcionarios.stream()
          .sorted(Comparator.comparing(Funcionario::getNome))
          .forEach(f -> System.out.println(f.getNome()));
  }

  public BigDecimal getTotalSalarios() {
    return funcionarios.stream()
          .map(Funcionario::getSalario)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void imprimirSalariosMinimos(BigDecimal salarioMinimo) {
    funcionarios.forEach(f -> {
      BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
      System.out.printf("%s ganha %.2f salários mínimos%n", f.getNome(), qtd);
    });
  }

}
