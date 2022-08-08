package com.yuzhi.home.utils;

/**
 * 判断两句话是否一致
 */
public class Test1Util {
    public static void main(String[] args) {
        String str1 = "罪犯本人且联号成员无违规扣分的，每月加 5 分，联号成员已履行包夹义务或无法履行包夹义务的不影响加分";
        String str2 = "罪犯本人且联号成员无违规扣分的，联号成员已履行包夹义务或无法履行包夹义务的视同无违规扣分";
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(score(str1, str2));
//        System.out.println(score("hello", "hello"));
    }

    static double score(String word, String word2) {
        int w1 = word.length(); //横
        int w2 = word2.length(); //纵
        int[][] arr = new int[w1 + 1][w2 + 1];
        if (w1 == 0 || w2 == 0)
            return (1 - 0);
        for (int i = 0; i < w1; i++) {
            arr[i][0] = i;
        }
        for (int i = 0; i < w2; i++) {
            arr[0][i] = i;
        }
        for (int i = 1; i <= w1; i++) {
            int cost = 1;
            char ch1 = word.charAt(i - 1);
            for (int j = 1; j <= w2; j++) {
                char ch2 = word2.charAt(j - 1);
                if (ch1 == ch2)
                    cost = 0;
                arr[i][j] = Math.min(Math.min(arr[i - 1][j] + 1, arr[i][j - 1] + 1), arr[i - 1][j - 1] + cost);
            }
        }
        return 1 - (double) arr[w1][w2] / Math.max(w1, w2);
    }

}
