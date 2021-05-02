package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.enums.WorkerLevel;

public class Worker {
    Scanner sc = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String name;
    private WorkerLevel level;
    private Double baseSalary;

    private Department department;
    private List<HourContract> contracts = new ArrayList<>();

    public Worker() {
    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }

    public void addContract(HourContract contract){
        contracts.add(contract);
    }
    public void addNewContract() throws ParseException {
        System.out.print("Date (DD/MM/YYYY): ");
        Date contractDate = sdf.parse(sc.next()); // Trabalho com datas simples;
        System.out.print("Value per hour: ");
        double valuePerHour = sc.nextDouble();
        System.out.print("Duration (hours): ");
        int hours =  sc.nextInt();
        contracts.add(new HourContract(contractDate, valuePerHour, hours));

        menu();
    }


    public void removeContract(HourContract contract) {
        contracts.remove(contract);
    }

    public Double income (int year, int month){
        double sum = baseSalary;
        Calendar cal = Calendar.getInstance();

        for (HourContract c : contracts) {
            cal.setTime(c.getDate());

            int c_year = cal.get(Calendar.YEAR);
            int c_month = 1 + cal.get(Calendar.MONTH);// month no calendar começa com 0;
            if( year == c_year && month ==  c_month ){
                sum += c.totalValue();
            }
        }

        return sum;
    }

    public void calculateIncome() throws ParseException {
        System.out.println();
        System.out.println("Enter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));

        System.out.println("Name: " + getName());
        System.out.println("Department: " + getDepartment().getName());
        System.out.println("Income for " + monthAndYear +": R$ " + String.format("%.2f", income(year, month)));

        menu();
    }

    public void menu() throws ParseException {
        try {
            int menu = 0;
            System.out.println("----------menu----------");
            System.out.println("Escolha uma opção");
            System.out.println("1 - Add a contract: ");
            System.out.println("2 - Calculate income: ");
            System.out.println("3 - Exit");

            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    addNewContract();
                    break;
                case 2:
                    calculateIncome();
                    break;
                case 3:
                System.exit(0);
                break;
                default:
                    System.exit(0);
            }
        } finally {
            sc.close();
        }
    }

}
