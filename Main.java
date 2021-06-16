package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        float[] a = null;
        boolean isRunning = true;
        do {
            int Selection = ShowMenu();
            switch (Selection) {
                case 1:
                    ImportData();
                    break;
                case 2:
                    a = OutputData();
                    PrintOut(a);
                    break;
                case 3:
                    if (a != null) {
                        BubbleSort(a);
                    }
                    break;
                case 4:
                    if (a != null) {
                        SelectionSort(a);
                    }
                    break;
                case 5:
                    if (a != null) {
                        InsertionSort(a);
                    }
                    break;
                case 6:
                    if (a != null) {
                        SearchLinear(a);
                    }
                    break;
                case 7:
                    if (a != null) {
                        searchBinary(a);
                    }
                    break;
                case 8:
                    isRunning = false;
                    Exit();
                    break;
            }
        } while (isRunning);
    }

    public static int ShowMenu() {
        Scanner Input = new Scanner(System.in);
        System.out.println(" 1.Input");
        System.out.println(" 2.Output");
        System.out.println(" 3.Bubble sort");
        System.out.println(" 4.Selection sort");
        System.out.println(" 5.Insertion sort");
        System.out.println(" 6.Search > value ");
        System.out.println(" 7.Search = value");
        System.out.println(" 8.Exit ");
        return Input.nextInt();
    }

    //    Hàm nhập dữ liệu N
    public static void ImportData() throws FileNotFoundException {
        Scanner InputFloat = new Scanner(System.in);
        int n;
        do {
            System.out.println("Nhap mot so n <= 20:");
            n = InputFloat.nextInt();
        } while (n > 20);
        float[] arr = new float[n];
        System.out.print("Nhập các phần tử của mảng: \n");
        for (int i = 0; i < n; i++) {
            System.out.printf("a[%d] = ", i);
            arr[i] = InputFloat.nextFloat();
        }

        PrintFile("input.txt", arr);
    }

    //    Đọc dữ liệu
    public static float[] OutputData() {
        FileInputStream in = null;
        try {
            in = new FileInputStream("input.txt");
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (in.read(buf) != -1) {
                sb.append(new String(buf, StandardCharsets.UTF_8).trim());
            }
            String[] data = sb.toString().split(" ");
            float[] res = new float[data.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = Float.parseFloat(data[i]);
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new float[]{};
    }

    // In du lieu
    public static void PrintOut(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //    Ham ghi du lieu
    public static void PrintFile(String fileName, float[] arr) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            StringBuilder sb = new StringBuilder();
            for (float ai : arr) {
                sb.append(ai).append(" ");
            }
            out.write(sb.toString().getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void PrintFile(String fileName, int x) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            out.write((x + "").getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ham sap xep tu be den lon
    public static void BubbleSort(float[] a) {
        long start = System.currentTimeMillis();
        float[] arr = new float[a.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = a[i];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    float temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            PrintOut(arr);
        }
        PrintFile("OUTPUT1.txt", arr);
        long end = System.currentTimeMillis();
        long t = end - start;
        System.out.println("Tổng thời gian: " + t + " millisecond");
    }

    public static void SelectionSort(float[] a) {
        long start = System.currentTimeMillis();
        float[] arr = new float[a.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = a[i];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = (int) arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
            PrintOut(arr);
        }
        PrintFile("OUTPUT2.txt", arr);
        long end = System.currentTimeMillis();
        long t = end - start;
        System.out.println("Tổng thời gian: " + t + " millisecond");
    }

    public static void InsertionSort(float[] a) {
        long start = System.currentTimeMillis();
        int n = a.length;
        for (int i = 1; i < n; ++i) {
            int key = (int) a[i];
            int j = i - 1;

            // Di chuyển các phần tử của arr [0 ... i - 1], lớn hơn key
            // đến một vị trí trước vị trí hiện tại của chúng
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;
            PrintOut(a);
        }
        PrintFile("OUTPUT3.txt", a);
        long end = System.currentTimeMillis();
        long t = end - start;
        System.out.println("Tổng thời gian: " + t + " millisecond");
    }

    /**
     * Tim kiem tuyen tinh thi khong can sap xep truoc.
     */
    public static void SearchLinear(float[] a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hay nhap vao 1 so:");
        Float x = sc.nextFloat();

        // [5, 3, 8, 9, 1]
        // gia su x = 10;
        // i = 0, thi a[i] = 5; else in ra 1 dong
        // i = 1, thi a[i] = 3; else in ra 1 dong
        // i = 2, a[i] = 8; else in ra 1 dong
        // i = 3; a[i] = 9; else in ra 1 dong
        // i = 4; a[i] = 1 else in ra 1 dong

        for (int i = 0; i < a.length; i++) {
            if (a[i] > x) {
                System.out.println(i);
                PrintFile("OUTPUT4.txt", i);

            }

        }
    }

    /**
     * Tim kiem nhi phan thi bat buoc phai duoc sap xep.
     *
     * @return
     */
    public  static void searchBinary(float[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    float temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Hay nhap vao 1 so:");
        Float x = sc.nextFloat();
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // kiểm tra xem x có ở chính giữa không
            if (a[m] == x){
                System.out.println(m);
                return;
            }


            // Nếu x lớn hơn, bỏ qua nửa bên trái
            if (a[m] < x)
                l = m + 1;

                // Nếu x nhỏ hơn, bỏ qua nửa bên phải
            else
                r = m - 1;
        }

        // phần tử không tồn tại
        System.out.println(-1);
    }



    public static void Exit() {
        System.exit(0);
    }
}

