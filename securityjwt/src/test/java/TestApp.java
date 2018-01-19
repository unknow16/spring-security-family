import com.fuyi.jwt.BootstrapApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/1/19 0019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootstrapApplication.class})
public class TestApp {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoder() throws Exception {
        String encode = passwordEncoder.encode("1234");
        System.out.println("--------------------------" + encode);
    }
}
