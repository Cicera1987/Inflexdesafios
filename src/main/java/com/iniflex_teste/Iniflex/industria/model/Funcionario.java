package com.iniflex_teste.Iniflex.industria.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Setter
@Getter
public class Funcionario  extends Pessoa{
  private BigDecimal salario;
  private String funcao;

  public Funcionario() {
    super();
  }
  public Funcionario(String nome, LocalDate dataNascimento,BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.funcao = funcao;
    this.salario = salario;
  }

  public int getIdade() {
    return Period.between(getDataNascimento(), LocalDate.now()).getYears();
  }
  @Override
  public String toString() {
      DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      NumberFormat moedaFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

      return String.format("%-15s %-12s %-15s %-15s",
            getNome(),
            getDataNascimento().format(dataFormatter),
            moedaFormatter.format(salario),
            funcao
      );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Funcionario)) return false;
    Funcionario that = (Funcionario) o;
    return Objects.equals(getNome(), that.getNome()) &&
          Objects.equals(getDataNascimento(), that.getDataNascimento()) &&
          Objects.equals(salario, that.salario) &&
          Objects.equals(funcao, that.funcao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNome(), getDataNascimento(), salario, funcao);
  }
}
