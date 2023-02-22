/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividade02;

/**
 *
 * @author NOTE
 */
public class Tabuleiro {
    private char matriz[][] =  new char[3][3];
    
    public Tabuleiro(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = '-';
            }
        }
    }
    
    public void imprimeMatriz(int linha){
        for (int i = 0; i < 3; i++)            
            System.out.print(matriz[linha][i] + " "); 
    }
    
    public void alteraValor(int i, int j, char valor){
        if(matriz[i][j] == '-')
            this.matriz[i][j] = valor;
        else
            System.out.println("posicao ja ocupada");
    }
    
    public char getValor(int i, int j){
        return this.matriz[i][j];
    }
    
}
