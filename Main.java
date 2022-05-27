import java.util.Scanner;

public class Main {

    private static void printMainMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n0. Exit");
    }

    private static void printRoomHeader(int seatsPerRow) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void printRoomSeats(int rows, int seatsPerRow, char[][] room) {
        for (int y = 0; y < rows; y++) {
            System.out.print(y + 1 + " ");
            for (int x = 0; x < seatsPerRow; x++) {
                System.out.print(room[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void buyTicket(Scanner scanner, int rows, int seatsPerRow, char[][] room) {
        System.out.println("Enter a row number:");
        final int choosenRow = scanner.nextInt();
        if (choosenRow > rows) {
            System.out.println("Invalid number!");
            return ;
        }
        System.out.println("Enter a seat number in that row:");
        final int choosenSeat = scanner.nextInt();
        if (choosenSeat > seatsPerRow) {
            System.out.println("Invalid number!");
            return ;
        }
        int sumSeats = rows * seatsPerRow; // count ticket price
        final int priceFront = 10;
        final int priceBack = 8;
        int price;
        if (sumSeats <= 60) {
            price = priceFront;
        } else {
            price = (rows / 2) >= choosenRow ? priceFront : priceBack;
        }
        System.out.println("\nTicket price: $" + price);

        room[choosenRow - 1][choosenSeat - 1] = 'B';

        printRoomHeader(seatsPerRow);// printing number of seats
        printRoomSeats(rows, seatsPerRow, room);// printing array of seats with choosen one
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seatsPerRow = scanner.nextInt();
        char[][] room = new char[rows][seatsPerRow];

        for (int y = 0; y < rows; y++) { // making an array of avaliable seats
            for (int x = 0; x < seatsPerRow; x++) {
                room[y][x] = 'S';
            }
        }

        printRoomHeader(seatsPerRow);// printing number of seats
        printRoomSeats(rows, seatsPerRow, room);// printing array of seats

        while (true) {
            printMainMenu();

            switch (scanner.nextInt()) {
                case 1:
                    printRoomHeader(seatsPerRow);
                    printRoomSeats(rows, seatsPerRow, room);
                    break;
                case 2:
                    buyTicket(scanner, rows, seatsPerRow, room);
                    break;
                case 0:
                    return;
            }

        }
    }
}