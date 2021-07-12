import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {

        String date = "05.03.2021";
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String ndate = new SimpleDateFormat("yyyy-MM-dd").format(formatter.parse(date));
        System.out.println(LocalDate.parse(ndate));

    }
}
