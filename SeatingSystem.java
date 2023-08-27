import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class SeatingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("EXAM SEATING ARRANGEMENT SYSTEM");
        System.out.println("-------------------------------");

        System.out.println("\nCLASSROOM INFORMATION");
        System.out.print("Enter the classroom number: ");
        int classroomNumber = scanner.nextInt();

        System.out.print("Enter the number of rows: ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int numCols = scanner.nextInt();

        System.out.println("\nSTUDENT BATCH INFORMATION");
        scanner.nextLine(); // Consume newline
        System.out.print("Which year and degree are the students from?: ");
        String studentBatch = scanner.nextLine();
        
        System.out.print("Starting Roll No.: ");
        int rollStart = scanner.nextInt();

        System.out.print("Ending Roll No.: ");
        int rollEnd = scanner.nextInt();
        int numStudents = rollEnd - rollStart + 1;

        int numSeats = numRows * numCols;

        if (numStudents > numSeats) {
            System.out.println("Insufficient seats for all students!");
            return;
        } else {
            System.out.println("Total students: " + numStudents);
        }

        int[][] seatingArrangement = new int[numRows][numCols];

        int studentNumber = rollStart;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (studentNumber <= rollEnd) {
                    seatingArrangement[i][j] = studentNumber;
                    studentNumber++;
                }
            }
        }

        System.out.println("\nSeating Arrangement for Classroom " + classroomNumber + ":");
        System.out.println("    <- Facing this way <-");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (seatingArrangement[i][j] == 0) {
                    System.out.print("       ");
                } else {
                    System.out.printf("%6d", seatingArrangement[i][j]);
                }
            }
            System.out.println();
        }

        try {
            FileWriter writer = new FileWriter("seating_arrangement_" + studentBatch + "_in_" + classroomNumber + ".txt");
            writer.write("Seating Arrangement for " + studentBatch + " in Classroom " + classroomNumber + ":\n");
            writer.write("Rows: " + numRows + ", Columns: " + numCols + ", Total Seats: " + numSeats + "\n");
            writer.write("Total students: " + numStudents + "\n");
            writer.write("    <- Facing this way <-\n");

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (seatingArrangement[i][j] == 0) {
                        writer.write("       ");
                    } else {
                        writer.write(String.format("%6d", seatingArrangement[i][j]));
                    }
                }
                writer.write("\n");
            }

            writer.write("Vacant seats: " + (numSeats - numStudents) + "\n");
            writer.close();
            System.out.println("Seating arrangement saved in file: seating_arrangement_" + studentBatch + "_in_" + classroomNumber + ".txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
