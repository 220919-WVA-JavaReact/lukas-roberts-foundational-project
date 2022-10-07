import com.revature.models.Request;
import org.junit.*;

public class RequestTest {
    // order of functions does not matter in this class
    @BeforeClass
    public static void doBeforeClass() {
        System.out.println("This is the first thing that runs in this class. Only runs one time.");
    }
    @AfterClass
    public static void doAfterClass() {
        System.out.println("This is the last thing that runs in this class. Only runs one time. ");
    }
    @Before
    public void beforeEach() {
        System.out.println("This runs before each @test method in this class.");
    }
    @After
    public void afterEach() {
        System.out.println("This runs before each @test method in this class.");
    }
    //-----------------------------------------------------------------------------------------------

    @Test
    public void posCreateRequest() {

    }

    @Test
    public void negCreateResult() {

    }

}
