import com.revature.dao.EmployeeDAO;
import com.revature.service.EmployeeService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class EmployeeServiceTest {
//    @BeforeClass
//    public static void doBeforeClass() {
//        System.out.println("This is the first thing that runs in this class. Only runs one time.");
//    }
//    @AfterClass
//    public static void doAfterClass() {
//        System.out.println("This is the last thing that runs in this class. Only runs one time. ");
//    }
//    @Before
//    public void beforeEach() {
//        System.out.println("This runs before each @test method in this class.");
//    }
//    @After
//    public void afterEach() {
//        System.out.println("This runs before each @test method in this class.");
//    }

    @Mock
    static EmployeeDAO ed;

    @InjectMocks
    private static EmployeeService es;

    @Before
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }





}
