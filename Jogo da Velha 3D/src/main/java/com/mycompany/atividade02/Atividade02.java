/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.atividade02;

/**
 *
 * @author NOTE
 */
import java.util.*;
public class Atividade02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String modo;
        System.out.println("Digite o modo de jogo(JxJ - para dois jogadores, ou JxB - para jogar\n contra o bot):");
        modo = sc.nextLine();
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.jogar(modo);
    }
}
