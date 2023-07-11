import java.util.Scanner;

public class Main {
    static int countBuy = 0;
    static int places = 0;
    static int currentIncome = 0;
    static int totalIncome = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows:\n> ");
        int rows = sc.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        int row = sc.nextInt();
        String[][] seats = new String[rows+1][row+1];
        total(rows, row);
        initialization(seats);
        places = (row)*(rows);
        boolean flag = true;
        while (flag){
            System.out.print("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                >\s""");
            int answer = sc.nextInt();
            if (answer == 1){
                showTheSeats(seats);
            }else if (answer == 2){
                buyTicket(rows, row, seats);
                countBuy++;
            }else if(answer == 3){
                statistic();
            }else{
                flag = false;
            }
        }
    }
    public static void showTheSeats(String[][] seats){
        System.out.println("Cinema: ");
        for (String[] seat : seats) {
            for (String s : seat) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

    }
    public static void initialization(String[][] seats){
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(i == 0 && j == 0){
                    seats[i][j] = " ";
                }else if(i == 0){
                    seats[i][j] = Integer.toString(j);
                }else if(j==0){
                    seats[i][j] = Integer.toString(i);
                }else{
                    seats[i][j] = "S";
                }
            }
        }

    }

    public static void buyTicket(int rows, int row, String[][] seats){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.print("Enter a row number:\n> ");
            int roW = sc.nextInt();
            System.out.print("Enter a seat number in that row:\n> ");
            int rowS = sc.nextInt();
            if (roW > rows || rowS > row){
                System.out.println("Wrong input!");
            }else if (seats[roW][rowS].equals("B")){
                System.out.println("That ticket has already been purchased!");
            }else{
                int ticket;
                if(rows * row <= 60){
                    ticket = 10;
                }else{
                    if(roW <= row / 2){
                        ticket = 10;
                    }else{
                        ticket = 8;
                    }
                }
                System.out.println("Ticket price: $"+ticket);
                seats[roW][rowS] = "B";
                currentIncome += ticket;
                flag = false;
            }
        }
    }
    public static void statistic(){
        double percentage = (double) countBuy / places * 100;

        System.out.printf("""
                
                Number of purchased tickets: %d
                Percentage: %.2f%%
                Current income: $%d
                Total income: $%d
                """, countBuy, percentage, currentIncome, totalIncome);
    }
    public static void total(int rows, int row){
        if (rows * row <= 60){
            totalIncome = rows * row * 10;
        }else{
            totalIncome = row / 2 * rows * 10 + (row - row / 2) * rows * 8;
        }
    }
}