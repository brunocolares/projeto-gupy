package modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public String getSalarioFormatado() {
        Locale localeBR = new Locale("pt","BR");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeBR);
        return numberFormat.format(this.salario);
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void aumentarSalario(double porcentagem) {
        BigDecimal aumento = this.salario.multiply(BigDecimal.valueOf(porcentagem / 100));
        this.salario = this.salario.add(aumento);
        this.salario = this.salario.setScale(2, RoundingMode.HALF_UP);
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


    @Override
    public String toString() {
        return "Funcionario{" +
                "nome=" + getNome() +
                ", data de nascimento=" + getDataNascimentoFormatada() +
                ", salario=" + getSalarioFormatado() +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
