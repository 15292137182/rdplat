package io.rdplat.common;

public class Test {
    public static void main(String[] args) {

        String str = "I LOVE SH";

        System.out.println(reverse(str));

        System.out.println(CharAtreverse(str));

        System.out.println(reverseCharArray(str));

        System.out.println(reverseStringBuilder(str));

        System.out.println(reverse1(str));
    }

    /**
     * 将字符按照空格反转
     *
     * @param rev
     * @return
     */
    public static String reverse(String rev) {
        String[] split = rev.split(" ");
        StringBuilder string = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            string.append(split[i]).append(" ");
        }
        return string.toString();
    }


    /**
     * 该方法是通过charAt()方法获得每一个char的字符，i=0时获得第一个字符a然后赋值给reverse
     * 此时reverse="a";i=1时获得第二个字符b然后加上reverse再赋值给reverse，此时reverse="ba";
     * 一次类推
     */
    public static String CharAtreverse(String s) {
        String reverse = "";
        for (int i = 0; i < s.length(); i++)
            reverse = s.charAt(i) + reverse;
        return reverse;
    }

    /*
     * 第二种   通过String的toCharArray()方法可以获得字符串中的每一个字符串并转换为字符数组，然后用一个空的字符串从后向前一个个的拼接成新的字符串。
     */

    public static String reverseCharArray(String s) {
        char[] array = s.toCharArray();
        StringBuilder reverse = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            reverse.append(array[i]);
        }
        return reverse.toString();
    }

    /*
     *第三种  通过StringBuiler的reverse()的方法，最快的方式。
     */
    public static String reverseStringBuilder(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    /*
     *第四种 通过递归的方式，其实有以上的就可以了，但是还是说一下递归，实在是应为这个感觉高大上一点而已。
     */
    public static String reverse1(String s) {
        int length = s.length();
        if (length <= 1)
            return s;
        String left = s.substring(0, length / 2);
        String right = s.substring(length / 2, length);
        return reverse1(right) + reverse1(left);
    }
}
