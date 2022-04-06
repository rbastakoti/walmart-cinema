import cinema.SeatManager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.naming.directory.SearchControls;

public class CinemaTest {
    SeatManager seatManager = new SeatManager();
    @Test
    public void singleReservationStart(){
        String[] seatData = new String[]{"R002", "4"};
        seatManager.reservation(seatData);
        Assertions.assertEquals("E1", seatManager.getReciept().get("R002").get(0));

    }

    @Test
    public void allocationTest(){
        int allocationDone = seatManager.allocate("R005", 20);
//        Assertions.assertEquals(0, allocationDone);
        Assertions.assertEquals(20, seatManager.getReciept().get("R005").size());
    }

    @Test
    public void maxReservation(){
        String[] seatData = new String[]{"R001", "19"};
        int load =seatManager.reservation(seatData);
        int max = seatManager.getReciept().size();
        Assertions.assertEquals(1, max);
    }


}
