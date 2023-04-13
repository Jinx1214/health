import org.junit.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@SpringJUnitConfig
public class test {

    @Test
    public void test() throws ParseException {
        Date date = new Date(2000 + "/" + 12 + "/" + 1);
        System.out.println(date);
    }
}
