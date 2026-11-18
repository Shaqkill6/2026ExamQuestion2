import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ProductCalculationsTest {
@Test
    public void GetVat_WithValidData_ReturnsCorrectValue(){
    //Price 3500
    ProductCalculations logic = new ProductCalculations(3500.0);

    assertEquals(525.0, logic.GetVat(),0.01);
}
@Test
    public void GetTotal_WIthValidData_Returns_CorrectValue(){
    ProductCalculations logic = new ProductCalculations(3500.0);

    assertEquals(4200.0,logic.GetTotal(),0.01);
}
}