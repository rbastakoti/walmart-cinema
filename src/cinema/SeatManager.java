package cinema;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SeatManager {
    boolean buffer = false;
    int bufferSeat = 3;
    int noOfCustomers = 0;
    int totalSeats = 200;
    String[] rowNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    int[] column = new int[20];
    String[][] seats = new String[10][20];
    int[] remainingSeats = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20};


    public StringBuilder getReservation()  {
        StringBuilder recordLine = new StringBuilder();
        for (String key: reciept.keySet()) {
            String seatLine = "";
            for(String seatNumber: reciept.get(key)){
                if(seatLine==""){
                    seatLine= seatLine + seatNumber;
                }else {
                    seatLine = seatLine + "," + seatNumber;
                }
            }
            recordLine.append(key);
            recordLine.append(" ");
            recordLine.append(seatLine);
            recordLine.append(System.getProperty("line.separator"));
        }
        return recordLine;
    }

    public HashMap<String, ArrayList<String>> getReciept() {
        return reciept;
    }

    HashMap<String, ArrayList<String>> reciept = new HashMap<>();

    public int reservation(String[] seatData) {
        int reserve;
        String reservationId = seatData[0];
        int requiredSeats = Integer.parseInt(seatData[1]);
        int seatsForGroup = requiredSeats;

        if (requiredSeats > 0) {
            if (totalSeats >= requiredSeats) {
                noOfCustomers = noOfCustomers + requiredSeats;
                if (seatsForGroup > column.length) {
                    while (seatsForGroup > column.length) {
                        reserve = allocate(reservationId, column.length);
                        seatsForGroup = seatsForGroup - 20;
                    }
                    buffer= true;
                    reserve = allocate(reservationId, seatsForGroup);

                } else {
                    reserve = allocate(reservationId, seatsForGroup);
                }
                return reserve;
            } else {
                return 0;
            }
        } else {
            return 1;}

    }





    public int allocate(String reservationId, int allocatedNumber) {
        int counter = 1;
//        int bestStart = 1;
        boolean check = true;
        int bestPlace = (rowNames.length / 2) - 1;
        while (bestPlace >= 0 && bestPlace < rowNames.length) {
            if (remainingSeats[bestPlace] >= allocatedNumber) {
                for (int i = 0; i < column.length && allocatedNumber > 0; i++) {

                    if (seats[bestPlace][i] == null) {
                        seats[bestPlace][i] = "C";

                        if (reciept.containsKey(reservationId)) {

                            reciept.get(reservationId).add(rowNames[bestPlace] + Integer.toString(i + 1));

                            while(allocatedNumber==1 && i< column.length && bufferSeat>=0){
                                if(seats[bestPlace][i]==null){
                                    seats[bestPlace][i]= "buffer";
                                }
                                bufferSeat--;
                                i++;

                            }
                            bufferSeat = 3;

                        } else {
                            ArrayList<String> seatPlan = new ArrayList<>();
                            seatPlan.add(rowNames[bestPlace] + Integer.toString(i + 1));
                            reciept.put(reservationId, seatPlan);
                        }
                        remainingSeats[bestPlace] = remainingSeats[bestPlace] - 1;
                        allocatedNumber--;
                        totalSeats--;

                    }
                }
            }
            if (check) {
                bestPlace = bestPlace + counter;
                counter++;
                check = false;
            } else {
                bestPlace = bestPlace - counter;
                counter++;
                check = true;
            }
        }
        return 0;

    }





//    public int availableSeats(){
//        int remaining = 0;
//        for(int rowValue: this.remainingSeats){
//            remaining = remaining+ rowValue;
//        }
//        return remaining;
//    }

}
