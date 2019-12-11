package ru.job4j.pojo;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Pratsun Dmitry Viktotrovych");
        student.setGroup("KS-021");
        student.setReceiptDate("01.09.2002");
        System.out.println("FIO: " + student.getFio());
        System.out.println("Group: " + student.getGroup());
        System.out.println("Receipt Date: " + student.getReceiptDate());
    }
}
