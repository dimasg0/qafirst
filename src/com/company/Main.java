package com.company;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String date_user;

        System.out.print("Your date: ");
        date_user = scanner.next();

        String result = getNextMonday(date_user);
        System.out.print(result);
    }

    public static String getNextMonday(String date_user) throws ParseException {
        if (date_user.equals("0") || (date_user.equals("00.00.00")) ||
                (date_user.equals("00.00.0000")) || (date_user.equals("00.00"))) {
            writeMessage("Date consists of zeros");
        }

        try {
            String[] dateParts = date_user.split("\\.");
            String day = dateParts[0];
            String month = dateParts[1];
            String year = dateParts[2];


            if ((Integer.parseInt(year) % 4 != 0) && (Integer.parseInt(month) == 2)) {
                if (Integer.parseInt(day) > 28) { writeMessage("No such date exists");}
            } else if ((Integer.parseInt(year) % 4 == 0) && (Integer.parseInt(month) == 2)) {
                if (Integer.parseInt(day) > 29) { writeMessage("No such date exists");}
            }

            if (Integer.parseInt(day) > 31 || (Integer.parseInt(month) > 12) ||
                    (Integer.parseInt(year) < 0) || (Integer.parseInt(day) < 0) ||
                    (Integer.parseInt(month) < 0) || (Integer.parseInt(year) > 9999) || Integer.parseInt(month) < 1000) {
                writeMessage("Date entered incorrectly");
            }

            LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            LocalDate globalDate = LocalDate.parse(date.toString());
            LocalDate supportDate = null;

            if (Objects.equals(dayOfWeek.toString(), "MONDAY")) {
                System.out.print("The entered date is Monday: " + "\n");
                return date_user;
            }
            else
            {
                System.out.print("The date entered is " + dayOfWeek + "\n");
                System.out.print("Next Monday: ");

                switch (dayOfWeek.toString()) {
                    case "TUESDAY"      -> { supportDate = globalDate.plusDays(6);}
                    case "WEDNESDAY"    -> { supportDate = globalDate.plusDays(5);}
                    case "THURSDAY"     -> { supportDate = globalDate.plusDays(4);}
                    case "FRIDAY"       -> { supportDate = globalDate.plusDays(3);}
                    case "SATURDAY"     -> { supportDate = globalDate.plusDays(2);}
                    case "SUNDAY"       -> { supportDate = globalDate.plusDays(1);}
                }
            }

            assert supportDate != null;
            return supportDate.getDayOfMonth() + "." + supportDate.getMonthValue() + "." + supportDate.getYear();

        } catch (Exception e) { writeMessage("Date entered incorrectly");}

        return date_user;
    }

    public static void writeMessage(String text){
        System.out.print(text);
        System.exit(0);
    }
}
