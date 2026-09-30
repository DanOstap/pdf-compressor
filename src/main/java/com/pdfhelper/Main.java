package com.pdfhelper;

import Service.Service;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Service service = new Service();
        System.out.println("Select File:");
        Scanner input_data = new Scanner(System.in);
        String path_input = input_data.nextLine();
        try {
            service.Service(path_input);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
