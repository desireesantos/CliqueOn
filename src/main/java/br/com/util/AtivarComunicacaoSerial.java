package br.com.util;


public class AtivarComunicacaoSerial {
    public static void main(String args[]) {
               SerialComm serial = new SerialComm();
               System.out.println(" Ativar Comunicao serial ");
               serial.execute();
    }
}