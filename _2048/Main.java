package _2048;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random r = new Random();
    public static int[][] a = new int[4][4];
    public static int[][] b = new int[4][4];
    public static int score = 0;
    public static boolean flag = true;
    public static boolean flag1 = true;

    public static void main(String[] args) throws Exception {
        System.out.println("2048小游戏，wasd控制方法，回车运行，o 退出游戏");
        a[r.nextInt(4)][r.nextInt(4)] = (r.nextInt(2) + 1) * 2;
        // 打印数组
        System.out.println("-----------------");
        pp();
        System.out.println("-----------------");
        Scanner s = new Scanner(System.in);
        end: while (flag) {
            fuzhi(a, b);
            char[] c = s.nextLine().toCharArray();

            if (c.length != 1) {
                System.out.println("输入错误，游戏结束。最终得分:" + score);
                break end;
            }
            switch (c[0]) {
                case 'w':
                    up();
                    break;
                case 's':
                    down();
                    break;
                case 'a':
                    left();
                    break;
                case 'd':
                    right();
                    break;
                case'o':
                    System.out.println("游戏结束  最终得分:" + score);
                    break end;
                default:
                    System.out.println("输入错误，请重新输入:");
            }
            p();
            System.out.println("-----------------");
        }
    }

    public static void l1() {
        for (int i = 0; i < 4; i++) {
            int t = 0;
            for (int j = 0; j < 4; j++) {
                if (a[i][j] != 0) {
                    int tmp = a[i][j];
                    a[i][j] = 0;
                    a[i][t] = tmp;
                    t++;
                }
            }
        }
    }

    public static void u1() {
        for (int i = 0; i < 4; i++) {
            int t = 0;
            for (int j = 0; j < 4; j++) {
                if (a[j][i] != 0) {
                    int tmp = a[j][i];
                    a[j][i] = 0;
                    a[t][i] = tmp;
                    t++;
                }
            }

        }
    }

    public static void r1() {
        for (int i = 3; i >= 0; i--) {
            int t = 3;
            for (int j = 3; j >= 0; j--) {
                if (a[i][j] != 0) {
                    int tmp = a[i][j];
                    a[i][j] = 0;
                    a[i][t] = tmp;
                    t--;
                }
            }
        }
    }

    public static void d1() {
        for (int i = 3; i >= 0; i--) {
            int t = 3;
            for (int j = 3; j >= 0; j--) {
                if (a[j][i] != 0) {
                    int tmp = a[j][i];
                    a[j][i] = 0;
                    a[t][i] = tmp;
                    t--;
                }
            }

        }
    }

    public static void up() {
        u1();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (a[j][i] == a[j - 1][i]) {
                    a[j - 1][i] = a[j][i] + a[j - 1][i];
                    score += a[j - 1][i];
                    a[j][i] = 0;
                }
            }
        }
        u1();
    }

    public static void down() {
        d1();
        for (int i = 3; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                if (a[j][i] == a[j + 1][i]) {
                    a[j + 1][i] = a[j][i] + a[j + 1][i];
                    score += a[j + 1][i];
                    a[j][i] = 0;
                }
            }
        }
        d1();
    }

    public static void right() {
        r1();
        for (int i = 3; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                if (a[i][j] == a[i][j + 1]) {
                    a[i][j + 1] = a[i][j] + a[i][j + 1];
                    score += a[i][j + 1];
                    a[i][j] = 0;
                }
            }
        }
        r1();
    }

    public static void left() {
        l1();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (a[i][j] == a[i][j - 1]) {
                    a[i][j - 1] = a[i][j] + a[i][j - 1];
                    score += a[i][j - 1];
                    a[i][j] = 0;
                }
            }
        }
        l1();
    }

    // 确定是否有空位，，在空位生成2，4，并打印
    public static void p() {
        // 计算数组有几个0
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (a[i][j] == 0) {
                    count++;
                }
            }
        }
        // 确定那几个位置是0
        int[][][] arr = new int[count][4][4];
        int tmp = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (a[i][j] == 0) {
                    arr[tmp][i][j] = 1;
                    tmp++;
                }
            }
        }
        // 剩余空位置，随机位置2或4
        if (arr.length != 0) {
            int index = r.nextInt(arr.length);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (arr[index][i][j] == 1) {
                        if (myequals(b)) {
                            a[i][j] = (r.nextInt(2) + 1) * 2;
                        }
                    }
                }
            }
        } else {// 所有位置都满了
            int[][] ttt = new int[4][4];
            switch (1) {
                case 1: {
                    fuzhi(a, ttt);
                    up();
                    if (myequals(ttt)) {
                        break;
                    }
                }
                case 2: {
                    fuzhi(a, ttt);
                    down();
                    if (myequals(ttt)) {
                        break;
                    }
                }
                case 3: {
                    fuzhi(a, ttt);
                    left();
                    if (myequals(ttt)) {
                        break;
                    }
                }
                case 4: {
                    fuzhi(a, ttt);
                    right();
                    if (myequals(ttt)) {
                        break;
                    } else {
                        flag = false;
                        flag1 = false;
                        System.out.println("游戏结束,最终得分:" + score);
                    }
                }
            }
            // true表示数组不想等
        }
        // 打印数组
        if (flag1) {
            pp();
        }
    }

    public static void fuzhi(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    // 判断两个数组是否相等
    public static boolean myequals(int[][] ttt) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (ttt[i][j] != a[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 打印输出结果
    public static void pp() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (a[i][j] != 0) {
                    System.out.print(a[i][j]);
                    if (a[i][j] >= 1000) {
                        System.out.print(" ");
                    } else if (a[i][j] >= 100) {
                        System.out.print("  ");
                    } else if (a[i][j] >= 10) {
                        System.out.print("   ");
                    } else if (a[i][j] >= 1) {
                        System.out.print("    ");
                    }
                } else {
                    System.out.print("     ");
                }
            }
            if (i == 3) {
                System.out.println(" " + "socre:" + score);
            } else {
                System.out.println();
            }
        }
    }
}