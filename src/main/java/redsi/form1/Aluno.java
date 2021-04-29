/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redsi.form1;

public class Aluno {

    public Aluno(String nome, String email, int numero) {
        this.nome = nome;
        this.email = email;
        this.numero = numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    String nome, email;
    int numero;

// necessário ser public para enviar como JSON
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getNumero() {
        return numero;
    }

}
