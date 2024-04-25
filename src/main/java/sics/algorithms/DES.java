package sics.algorithms;

/**
 *
 * @author João Lima
 */
public class DES {

    private final int MSG_SZ = 64;
    private final int KEY_SZ = 64;
    private final int BLOCK_SZ = 56;
    private final int SUB_K_SZ = 48;
    private final int N_ROT = 16;
    private final int N_SBOX = 8;
    private final int[] L_PC_1 = {
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36
    };
    private final int[] R_PC_1 = {
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4
    };
    private final int[] PC_2 = {
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };
    private final int[] BIT_ROT_TAB = {
        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };
    private final int[] IP_L = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8
    };
    private final int[] IP_R = {
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };
    private final int[] E = {
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };
    private final int[][][] S_BOX = {
        {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        },
        {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        },
        {
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        },
        {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        },
        {
            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        },
        {
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        },
        {
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        },
        {
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        }
    };
    private final int[] P = {
        16, 7, 20, 21, 29, 12, 28, 17,
        1, 15, 23, 26, 5, 18, 31, 10,
        2, 8, 24, 14, 32, 27, 3, 9,
        19, 13, 30, 6, 22, 11, 4, 25
    };
    private final int[] IP_REV = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };
    private char[] msg;
    private char[] key;
    private char[][] c;
    private char[][] d;
    private char[][] k;
    private char[][] l;
    private char[][] r;
    private char[][] f;
    private char[][] expR;
    private char[][] xorExpR_K;
    private char[][] sub;
    private char[] cipher;

    public DES(String msg, String key) {
        this.msg = hexToBin(msg);
        this.key = hexToBin(key);
        this.c = new char[N_ROT + 1][BLOCK_SZ / 2];
        this.d = new char[N_ROT + 1][BLOCK_SZ / 2];
        this.k = new char[N_ROT][SUB_K_SZ];
        this.l = new char[N_ROT + 1][MSG_SZ / 2];
        this.r = new char[N_ROT + 1][MSG_SZ / 2];
        this.f = new char[N_ROT][MSG_SZ / 2];
        this.expR = new char[N_ROT][SUB_K_SZ];
        this.xorExpR_K = new char[N_ROT][SUB_K_SZ];
        this.sub = new char[N_ROT][MSG_SZ / 2];
        this.cipher = new char[MSG_SZ];
    }

    public String getHexCipher() {
        int i = 0;
        StringBuilder hex = new StringBuilder();
        StringBuilder block = new StringBuilder();
        while(i < MSG_SZ){
            block.append(cipher[i]);
            i++;
            block.append(cipher[i]);
            i++;
            block.append(cipher[i]);
            i++;
            block.append(cipher[i]);
            i++;
            hex.append(Integer.toHexString(Integer.parseInt(block.toString(),2)));
            block.setLength(0);
        }
        return "0x" + hex.toString().toUpperCase();
    }

    public void encrypt() {
        defC_D();
        defK();
        defL_R();
        defC();
    }

    private void defC() {
        char[] concat = (String.valueOf(r[N_ROT]) + String.valueOf(l[N_ROT])).toCharArray();
        for (int i = 0; i < MSG_SZ; i++) {
            cipher[i] = concat[IP_REV[i] - 1];
        }
    }

    private void defL_R() {
        defL_R0();
        defC_D0();
        for (int rot = 1; rot <= N_ROT; rot++) {
            l[rot] = copy(r[rot - 1]);
            defF(rot - 1);
            r[rot] = xor(l[rot - 1], f[rot - 1]);
        }

    }

    private void defF(int rot) {
        defExpR(rot);
        xorExpR_K[rot] = xor(expR[rot], k[rot]);
        defSub(rot);
        for (int i = 0; i < MSG_SZ / 2; i++) {
            f[rot][i] = sub[rot][P[i] - 1];
        }
    }

    private void defSub(int rot) {
        int j;
        int row;
        int column;
        int bSize = SUB_K_SZ / N_SBOX;
        int subBlockSize = (MSG_SZ / 2) / N_SBOX;
        for (int i = 0; i < N_SBOX; i++) {
            row = Integer.parseInt(""
                    + xorExpR_K[rot][i * bSize]
                    + xorExpR_K[rot][((i + 1) * bSize) - 1], 2);
            column = Integer.parseInt(""
                    + xorExpR_K[rot][(i * bSize) + 1]
                    + xorExpR_K[rot][(i * bSize) + 2]
                    + xorExpR_K[rot][(i * bSize) + 3]
                    + xorExpR_K[rot][(i * bSize) + 4], 2);
            j = 0;
            for (char e : intToBin(S_BOX[i][row][column], 4)) {
                sub[rot][(i * subBlockSize) + j] = e;
                j++;
            }
        }
    }

    private void defExpR(int rot) {
        for (int i = 0; i < SUB_K_SZ; i++) {
            expR[rot][i] = r[rot][E[i] - 1];
        }
    }

    private void defL_R0() {
        for (int i = 0; i < MSG_SZ / 2; i++) {
            l[0][i] = msg[IP_L[i] - 1];
            r[0][i] = msg[IP_R[i] - 1];
        }
    }

    private void defK() {
        for (int rot = 0; rot < N_ROT; rot++) {
            for (int i = 0; i < SUB_K_SZ; i++) {
                if ((PC_2[i] - 1) / (BLOCK_SZ / 2) == 0) {
                    k[rot][i] = c[rot + 1][PC_2[i] - 1];
                } else {
                    k[rot][i] = d[rot + 1][(PC_2[i] - 1) % (BLOCK_SZ / 2)];
                }
            }
        }

    }

    private void defC_D() {
        defC_D0();
        for (int rot = 1; rot <= N_ROT; rot++) {
            c[rot] = shiftLeft(c[rot - 1], BIT_ROT_TAB[rot - 1]);
            d[rot] = shiftLeft(d[rot - 1], BIT_ROT_TAB[rot - 1]);
        }

    }

    private void defC_D0() {
        for (int i = 0; i < BLOCK_SZ / 2; i++) {
            c[0][i] = key[L_PC_1[i] - 1];
            d[0][i] = key[R_PC_1[i] - 1];
        }
    }

    private char[] shiftLeft(char[] bits, int shifts) {
        char[] shiftedBits = copy(bits);
        char firstBit;

        for (int shift = 0; shift < shifts; shift++) {
            firstBit = shiftedBits[0];
            for (int i = 0; i < shiftedBits.length - 1; i++) {
                shiftedBits[i] = shiftedBits[i + 1];
            }
            shiftedBits[shiftedBits.length - 1] = firstBit;
        }
        return shiftedBits;
    }

    private char[] hexToBin(String hex) {
        StringBuilder bin = new StringBuilder();
        String malforBin;

        if (hex.startsWith("0x")) {
            hex = hex.substring(2);
        }

        for (char c : hex.toLowerCase().toCharArray()) {
            malforBin = Integer.toBinaryString(Integer.parseInt(Character.toString(c), 16));
            for (int i = 0; i < 4 - malforBin.length(); i++) {
                bin.append("0");
            }
            bin.append(malforBin);
        }

        return bin.toString().toCharArray();
    }

    private char[] intToBin(int value, int minSize) {
        String bin = Integer.toBinaryString(value);
        while (bin.length() < minSize) {
            bin = "0" + bin;
        }
        return bin.toString().toCharArray();
    }

    private char[] copy(char[] array) {
        char[] copy = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        return copy;
    }

    private char[] xor(char[] x, char[] y) {
        char[] xor = new char[x.length];
        for (int i = 0; i < x.length; i++) {
            if ((x[i] == '1' && y[i] == '0') || (x[i] == '0' && y[i] == '1')) {
                xor[i] = '1';
            } else {
                xor[i] = '0';
            }
        }
        return xor;
    }

}
