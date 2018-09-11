package io.rdplat.common;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
//        new Demo().test();
        char [] chars = {'A','B','C'};
        char [] c = {'B','A','D'};
        int[] check = check(chars, c);
        System.out.println(Arrays.toString(check));
    }

    public void test() throws InterruptedException {
        synchronized (this){
            this.wait();
            System.out.println("123123");
        }
    }

    /**
     * 随机生成需要猜测的字母序列
     *
     * @return 存储随机字符的数组
     */
    public static char[] generate() {

        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z' };
        boolean[] flags = new boolean[letters.length];
        char[] chs = new char[5];
        for (int i = 0; i < chs.length; i++) {
            int index;
            do {
                index = (int) (Math.random() * (letters.length));
            } while (flags[index]);// 判断生成的字符是否重复
            chs[i] = letters[index];
            flags[index] = true;
        }
        return chs;
    }


    /**
     * 比较玩家输入的字母序列和程序所生成的字母序列，逐一比较字符及其位置，并记载比较结果
     *
     * @param chs
     *            程序生成的字符序列
     * @param input
     *            玩家输入的字符序列
     * @return 存储比较的结果。返回值int数组 的长度为2，其中，索引为0的位置
     *         用于存放完全猜对的字母个数(字符和位置均正确)，索引为1的位置用于存放猜对的字母个数(字符正确，但是位置不正确)。
     */
    public static int[] check(char[] chs, char[] input) {
        int[] result = new int[2];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < chs.length; j++) {
                if (input[i] == chs[j]) {// 判断字符是否正确
                    result[1]++;
                    if (i == j) {// 判断位置是否正确
                        result[0]++;
                    }
                    break;
                }
            }
        }
        return result;
    }


}
