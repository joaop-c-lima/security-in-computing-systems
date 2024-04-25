package sics;

import sics.algorithms.DES;

/**
 *
 * @author João Lima
 */
public class Main {

    public static void main(String[] args) {
        DES des = new DES("0x0123456789ABCDEF", "0x133457799BBCDFF1");
        des.encrypt();
        System.out.println(des.getHexCipher());
    }

}
