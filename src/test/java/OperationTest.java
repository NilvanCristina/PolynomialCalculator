import org.junit.*;
import static org.junit.Assert.assertEquals;

public class OperationTest {
    private static int noPerformedTests = 0;
    private static int noSuccessfulTests = 0;

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println(noPerformedTests + " were performed, " + noSuccessfulTests + " of which were successful!");
    }

    @Before
    public void setUp() throws Exception {
        noPerformedTests++;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test " + noPerformedTests +" is over!");
    }

    @Test
    public void addTest() {
        Operation operation = new Operation();
        Polinom firstPolynomial = new Polinom();
        Polinom secondPolynomial = new Polinom();

        firstPolynomial.getPolynomialFromString("2x^2-3x^1+2x^0");
        secondPolynomial.getPolynomialFromString("1x^3+2x^1");
        Polinom resultPolynomial = operation.add(firstPolynomial, secondPolynomial);

        String expected = "+1.0x^3+2.0x^2-1.0x^1+2.0x^0";
        String actual = resultPolynomial.generateStringFromPolynomial();

        assertEquals(expected, actual);
        noSuccessfulTests++;
    }

    @Test
    public void subtractTest() {
        Operation operation = new Operation();
        Polinom firstPolynomial = new Polinom();
        Polinom secondPolynomial = new Polinom();

        firstPolynomial.getPolynomialFromString("2x^2-3x^1+2x^0");
        secondPolynomial.getPolynomialFromString("1x^3+2x^1");
        Polinom resultPolynomial = operation.subtract(firstPolynomial, secondPolynomial);

        String expected = "-1.0x^3+2.0x^2-5.0x^1+2.0x^0";
        String actual = resultPolynomial.generateStringFromPolynomial();

        assertEquals(expected, actual);
        noSuccessfulTests++;
    }

    @Test
    public void multiplyTest() {
        Operation operation = new Operation();
        Polinom firstPolynomial = new Polinom();
        Polinom secondPolynomial = new Polinom();

        firstPolynomial.getPolynomialFromString("2x^2-3x^1+2x^0");
        secondPolynomial.getPolynomialFromString("1x^3+2x^1");
        Polinom resultPolynomial = operation.multiply(firstPolynomial, secondPolynomial);

        String expected = "+2.0x^5-3.0x^4+6.0x^3-6.0x^2+4.0x^1";
        String actual = resultPolynomial.generateStringFromPolynomial();

        assertEquals(expected, actual);
        noSuccessfulTests++;
    }

    @Test
    public void divideTest() {
        Operation operation = new Operation();
        Polinom firstPolynomial = new Polinom();
        Polinom secondPolynomial = new Polinom();

        firstPolynomial.getPolynomialFromString("2x^3-4x^1+5x^0");
        secondPolynomial.getPolynomialFromString("1x^2+1x^0");
        DivisionPair resultPolynomial = operation.divide(firstPolynomial, secondPolynomial);

        String expectedRemainder = "+2.0x^1";
        String actualRemainder  = resultPolynomial.getRemainder().generateStringFromPolynomial();

        String expectedQuotient = "-6.0x^1+5.0x^0";
        String actualQuotient  = resultPolynomial.getQuotient().generateStringFromPolynomial();

        assertEquals(expectedRemainder, actualRemainder);
        assertEquals(expectedQuotient, actualQuotient);
        noSuccessfulTests++;
    }

    @Test
    public void deriveTest() {
        Operation operation = new Operation();
        Polinom polynomial = new Polinom();

        polynomial.getPolynomialFromString("1x^2+1x^0");
        Polinom resultPolynomial = operation.derive(polynomial);

        String expected = "+2.0x^1";
        String actual = resultPolynomial.generateStringFromPolynomial();

        assertEquals(expected, actual);
        noSuccessfulTests++;
    }

    @Test
    public void integrateTest() {
        Operation operation = new Operation();
        Polinom polynomial = new Polinom();

        polynomial.getPolynomialFromString("3x^2+2x^1");
        Polinom resultPolynomial = operation.integrate(polynomial);

        String expected = "+1.0x^3+1.0x^2";
        String actual = resultPolynomial.generateStringFromPolynomial();

        assertEquals(expected, actual);
        noSuccessfulTests++;
    }
}
