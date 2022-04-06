package cinema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        SeatManager seatManager = new SeatManager();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the path to the file");
        String inputPath = scanner.nextLine();

        try {
            File myObj = new File(inputPath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] seatData = data.split(" ");
                seatManager.reservation(seatData);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String result = String.valueOf(seatManager.getReservation());

        try{
            FileWriter booking = new FileWriter(new File("reservation.txt"));
            booking.write(result);
            booking.flush();;
            booking.close();
        } catch (IOException e){
            System.out.print("File Writing error");
        }

    }

//    public static int total(ArrayList<String[]> data){
//        int sum = 0;
//        for (String[] seat : data){
//            sum = sum + Integer.parseInt(seat[1]);
//        }
//        return sum;
//    }


}
