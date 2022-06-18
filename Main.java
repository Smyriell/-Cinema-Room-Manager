import java.util.Scanner;

public class Main {

    private static void printMainMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }

    private static void printRoomHead(int seatsPerRow) {
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

    private static int countTotalIncome(int rows, int seatsPerRow) {
        int sumSeats = rows * seatsPerRow;
        final int priceFront = 10;
        final int priceBack = 8;
        if (sumSeats <= 60) {
            return priceFront * sumSeats;
        } else {
            return seatsPerRow * ((rows / 2) * priceFront + ((rows - rows / 2) * priceBack));
        }
    }

    private static int countSoldTickets(char[][] room) {
        int soldTickets = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == 'B') {
                    ++soldTickets;
                }
            }
        }
        return soldTickets;
    }

    private static int countCurrentIncome(char[][] room, int rows, int seatsPerRow) {
        int soldTicketsHighPrice = 0;
        int soldTicketsLowPrice = 0;
        final int priceFront = 10;
        final int priceBack = 8;

        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == 'B' && i < (rows / 2)) {
                    ++soldTicketsHighPrice;
                }
                if (room[i][j] == 'B' && i >= (rows / 2)) {
                    ++soldTicketsLowPrice;
                }
            }
        }
        return soldTicketsHighPrice * priceFront + soldTicketsLowPrice * priceBack;
    }

    private static void printStatistics(int rows, int seatsPerRow, char[][] room) {
        int soldTickets = countSoldTickets(room);
        int totalIncome = countTotalIncome(rows, seatsPerRow);
        float percentage = (float)soldTickets * 100 / (rows * seatsPerRow);
        int currentIncome = rows * seatsPerRow <= 60 ? soldTickets * 10 : countCurrentIncome(room, rows, seatsPerRow);

        System.out.printf("%nNumber of purchased tickets: %d%nPercentage: %.2f%%%n" +
                "Current income: $%d%nTotal income: $%d%n%n", soldTickets, percentage, currentIncome, totalIncome);

    }

    private static void buyTicket(Scanner scanner, int rows, int seatsPerRow, char[][] room) {
        int choosenRow;
        int choosenSeat;
        do {
            System.out.println("Enter a row number:");
            choosenRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            choosenSeat = scanner.nextInt();
            if (choosenSeat > seatsPerRow || choosenRow > rows) {
                System.out.println("Wrong number!\n");
            }
            if (choosenSeat <= seatsPerRow && choosenRow <= rows && room[choosenRow - 1][choosenSeat - 1] == 'B') {
                System.out.println("\nThat ticket has already been purchased!\n");
            }
        } while (choosenSeat > seatsPerRow || choosenRow > rows || room[choosenRow - 1][choosenSeat - 1] == 'B');
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

        printRoomHead(seatsPerRow);// printing number of seats
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

        printRoomHead(seatsPerRow);// printing number of seats
        printRoomSeats(rows, seatsPerRow, room);// printing array of seats

        while (true) {
            printMainMenu();
            switch (scanner.nextInt()) {
                case 1:
                    printRoomHead(seatsPerRow);
                    printRoomSeats(rows, seatsPerRow, room);
                    break;
                case 2:
                    buyTicket(scanner, rows, seatsPerRow, room);
                    break;
                case 3:
                    printStatistics(rows, seatsPerRow, room);
                    break;
                case 0:
                    return;
            }
        }
    }
}