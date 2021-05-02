package application;

import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.Worker;
import entities.enums.WorkerLevel;

public class App {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter department's name: ");
        String departmentName = sc.nextLine();

        System.out.println("Enter worker data: ");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine();
        System.out.print("Base salary: ");
        Double baseSalary = sc.nextDouble();

        Worker worker = new Worker( workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        worker.menu();

        sc.close();
    }
}
