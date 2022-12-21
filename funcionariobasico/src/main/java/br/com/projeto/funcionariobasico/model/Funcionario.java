/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.funcionariobasico.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author kevinperera
 */
public class Funcionario extends Pessoa{
    
    private BigDecimal salario;
    
    private String funcao;

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    //Construtor vazio para instancia de objetos não preenchidos
    public Funcionario(){
        
    }
    
    //Construtor para gerar o objeto do funcionário inline
    public Funcionario(String nome,LocalDate dataNascimento,BigDecimal salario, String funcao) {
        super(nome,dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    
    
    
}
