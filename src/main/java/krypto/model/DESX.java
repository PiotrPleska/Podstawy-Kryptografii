package krypto.model;

public class DESX {

    private static final int[][][] S_BOXES = {
            // S-box 1
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            // S-box 2
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            // S-box 3
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },// S-box 4
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            // S-box 5
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            },
            // S-box 6
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            // S-box 7
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            // S-box 8
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };
    byte[] initialPermutationPattern = new byte[]{
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };
    byte[] extendPattern = new byte[]{
            32, 1, 2, 3, 4, 5, 4, 5,
            6, 7, 8, 9, 8, 9, 10, 11,
            12, 13, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1
    };
    byte[] PBoxPattern = new byte[]{
            16, 7, 20, 21, 29, 12, 28, 17,
            1, 15, 23, 26, 5, 18, 31, 10,
            2, 8, 24, 14, 32, 27, 3, 9,
            19, 13, 30, 6, 22, 11, 4, 25
    };
    byte[] finalPermutationPattern = new byte[]{
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };

    public static void test(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int bits = 0;
        for (byte b : bytes) {
            int unsignedByte = b & 0xFF;
            String binaryString = Integer.toBinaryString(unsignedByte);
            // Pad the binary string with leading zeros if necessary
            while (binaryString.length() < 8) {
                binaryString = "0" + binaryString;
            }
            bits++;
            sb.append(binaryString + " ");
        }

        String binaryOutput = sb.toString();
        System.out.println("Byte size: " + bits);
        System.out.println(binaryOutput);
    }


    public byte[] permuteFunction(byte[] block, byte[] permutationPattern) {
        byte[] output = new byte[permutationPattern.length / 8];
        for (int i = 0; i < permutationPattern.length; i++) {
            int index = permutationPattern[i] - 1; // subtract 1 to adjust for 0-based indexing
            int byteIndex = index / 8; // calculate the index of the byte that contains the bit
            int bitIndex = 7 - (index % 8); // calculate the index of the bit within the byte
            byte bit = (byte) ((block[byteIndex] >> bitIndex) & 0x01); // extract the bit at the specified position
            output[i / 8] |= (bit << (7 - (i % 8))); // set the bit in the corresponding position in the permuted key
        }
        return output;
    }

    public static byte[] rotateBits(byte[] arr) {
        int carry = (arr[0] & 0x80) != 0 ? 1 : 0;
        for (int i = 0; i < arr.length; i++) {
            int nextCarry = (arr[i] & 0x80) != 0 ? 1 : 0;
            arr[i] = (byte) ((arr[i] << 1) | carry);
            carry = nextCarry;
        }
        return arr;
    }

    public byte[] feistelFunction(byte[] block, byte[] permutedKey) throws Exception {

        if (block.length != 4) {
            throw new Exception("Invalid block size");
        }
        if (permutedKey.length != 6) {
            throw new Exception("Invalid key size");
        }

        byte[] extendedBlock = permuteFunction(block, extendPattern);

        byte[] extendedBlockXorKey = xor(extendedBlock, permutedKey);


        byte[][] input = new byte[8][1];
        for (int i = 7; i >= 0; i--) {
            byte temp = (byte) (extendedBlockXorKey[0] & 0xfc);
            temp >>= 2;
            input[input.length - 1][0] = temp;
            input[i][0] = temp;

            for (int j = 0; j < 6; j++) {
                rotateBits(extendedBlockXorKey);
            }

        }
        byte[][] output = new byte[8][1];
        for (int i = 0; i < 8; i++) {
            int row = ((input[i][0] & 0b100000) >> 4) | (input[i][0] & 0b000001);
            int col = (input[i][0] & 0b011110) >> 1;
            output[i][0] = (byte) S_BOXES[i][row][col];
        }


        byte[] result = new byte[4]; // inicjalizacja wyjściowej tablicy 4 bajtów

        // iteracja po wejściowej tablicy
        for (int i = 0; i < 8; i++) {
            // odczytaj bajt z wejściowej tablicy
            byte b = output[i][0];

            // wybierz tylko 4 najmniej znaczących bitów
            int fourBits = b & 0x0F;

            // oblicz pozycję w wyjściowej tablicy
            int Index = i / 2;

            // oblicz przesunięcie bitowe w wyjściowym słowie
            int shift = (i % 2) * 4;

            // zapisz 4-bitową wartość w wyjściowym słowie
            result[Index] |= fourBits << shift;
        }

        result = permuteFunction(result, PBoxPattern);

        return result;
    }

    public byte[] xor(byte[] arr1, byte[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Byte arrays must be of equal length");
        }
        byte[] result = new byte[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = (byte) (arr1[i] ^ arr2[i]);
        }
        return result;
    }

    public byte[] round(byte[] left, byte[] right, int iteration, boolean reverse) throws Exception {
        byte[] result = new byte[8];
        byte[] key;
        if (iteration == 16) {
            System.arraycopy(left, 0, result, 0, left.length);
            System.arraycopy(right, 0, result, left.length, right.length);

            return result;
        }
        if (reverse) {
            key = Key.getPermuttedKey(15 - iteration);
            return round(right, xor(left, feistelFunction(right, key)), iteration + 1, true);
        } else {
            key = Key.getPermuttedKey(iteration);
            return round(right, xor(left, feistelFunction(right, key)), iteration + 1, false);
        }

    }

    public byte[] cipher(byte[] block, Key keys) throws Exception {
        if (block.length != 8) {
            throw new Exception("Invalid block size");
        }

        block = xor(block, keys.getKey(0));

        block = permuteFunction(block, initialPermutationPattern);

        byte[] leftBlock = new byte[4];
        byte[] rightBlock = new byte[4];
        System.arraycopy(block, 0, leftBlock, 0, 4);
        System.arraycopy(block, 4, rightBlock, 0, 4);

        byte[] result = round(leftBlock, rightBlock, 0, false);

        result = permuteFunction(result, finalPermutationPattern);

        result = xor(block, keys.getKey(2));
        return result;
    }

    public byte[] decipher(byte[] block, Key keys) throws Exception {
        if (block.length != 8) {
            throw new Exception("Invalid block size");
        }

        block = xor(block, keys.getKey(2));

        block = permuteFunction(block, finalPermutationPattern);

        byte[] leftBlock = new byte[4];
        byte[] rightBlock = new byte[4];
        System.arraycopy(block, 0, leftBlock, 0, 4);
        System.arraycopy(block, 4, rightBlock, 0, 4);



        byte[] result = round(leftBlock, rightBlock, 0, true);

        result = permuteFunction(result, initialPermutationPattern);

        result = xor(block, keys.getKey(0));

        return result;
    }

}
