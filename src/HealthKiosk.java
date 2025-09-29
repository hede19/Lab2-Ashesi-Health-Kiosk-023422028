import java.util.*;
public class HealthKiosk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to Ashesi Health Kiosk!");


        // TASK 1 = SERVICE ROUTER

        System.out.print("enter service code (P/L/T/C): ");   // PROMPT FOR THE USER
        char serviceCode = input.next().toUpperCase().charAt(0);   // CONVERT INPUT FROM USER INTO UPPERCASE

          // USING SWITCH CASE TO PROCESS USER INPUT
        String service ;
        switch (serviceCode) {
            case 'P':
                service = "PHARMACY";
                System.out.println("Go to: Pharmacy Desk");
                break;

            case 'L':
                service = "LAB";
                System.out.println("Go to: Lab Desk");
                break;

            case 'T':
                service = "TRIAGE";
                System.out.println("Go to: Triage Desk");
                break;

            case 'C':
                service = "COUNSELING";
                System.out.println("Go to: Counseling Desk");
                break;

            default:
                System.out.println("Invalid Service Code");
                return;
        }

        //TASK 2 =MINI HEALTH MATRIX
        double metricValue = 0 ;
        double originalBMI = 0 ;

        if (service.equals("TRIAGE")) {
            System.out.println("Choose metric: 1=BMI, 2=Dosage round-up, 3=Trig helper");
            int metricChoice = input.nextInt();

            if (metricChoice == 1) {
                System.out.print("Enter weight(kg): ");
                double weight = input.nextDouble();
                System.out.print("Enter height(m): ");
                double height = input.nextDouble();

                double bmi = weight / Math.pow(height, 2);
                originalBMI = Math.round(bmi * 10) / 10.0;
                metricValue = Math.round(bmi);

                String category ;
                if (bmi < 18.5) category = "Underweight";
                else if (bmi < 25) category = "Normal";
                else if (bmi < 30) category = "Overweight";
                else category = "obese";

                System.out.println("BMI: " + originalBMI + "  Category: " + category);

            } else if (metricChoice == 2) {
                    System.out.print("Enter required dosage (mg): ");
                    double dosage = input.nextDouble();
                    int tablets = (int) Math.ceil(dosage / 250);
                    metricValue = tablets;
                    System.out.println("Number of tablets: " + tablets);
            }

             else if (metricChoice == 3) {
                System.out.print("Enter angle in degrees: ");
                double angle = input.nextDouble();
                double rad = Math.toRadians(angle);

                double sinVal = Math.round(Math.sin(rad) * 1000) / 1000.0;
                double cosVal = Math.round(Math.cos(rad) * 1000) / 1000.0;

                metricValue = Math.round(Math.sin(rad) * 100);
                System.out.println("sin=" + sinVal + " cos=" + cosVal);
            }

        }

        //TASK 3 = ID SANITY CHECK

        char letter = (char) ('A' + rand.nextInt(26));
        StringBuilder sb = new StringBuilder();
        sb.append(letter);
        for (int i = 0; i < 4; i++) {
            int digit = 3 + rand.nextInt(7); // 3–9 inclusive
            sb.append(digit);
        }
        String studentID = sb.toString();

        boolean valid = true;
        if (studentID.length() != 5) {
            System.out.println("Invalid length");
            valid = false;
        } else if (!Character.isLetter(studentID.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
            valid = false;
        } else {
            for (int i = 1; i < 5; i++) {
                if (!Character.isDigit(studentID.charAt(i))) {
                    System.out.println("Invalid: last 4 must be digits");
                    valid = false;
                }
            }
        }
        if (valid) {
            System.out.println("ID OK: " + studentID);
        }

        // TASK 4 = SECURE DISPLAY CODE

        System.out.print("enter your first name: ");
        String name = input.next();
        char base = Character.toUpperCase(name.charAt(0));
        char shifted = (char)('A' +(base -'A' + 2) % 26);

        String lastTwo = studentID.substring(studentID.length()  - 2 );
        String displayCode = shifted + lastTwo + "-" + (int) metricValue;
        System.out.println("Display Code: " + displayCode);


        // TASK 5 = SPACING BEFORE SUMMARY

        System.out.println();
        switch (serviceCode) {
            case 'P':
                System.out.println("Summary: PHARMACY | ID=" + studentID + " | Code=" + displayCode);
                break;

            case 'L':
                System.out.println("Summary: LAB | ID=" + studentID + " | Code=" + displayCode);
                break;

            case 'T':
                System.out.println("Summary: TRIAGE | ID=" + studentID + " | BMI=" + originalBMI + " | Code=" + displayCode);
                break;

            case 'C':
                System.out.println("Summary: COUNSELING | ID=" + studentID + " | Code=" + displayCode);
                break;
        }


    }
}
