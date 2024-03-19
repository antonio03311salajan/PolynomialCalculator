import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.tuc.bussineslogic.Operation;
import ro.tuc.datamodels.Polynomial;

import static org.junit.Assert.assertEquals;

public class OperationTest {
    @Test
    @DisplayName("Addition test 1")
    public void addTest1(){
        Polynomial p1=new Polynomial("x^3+x^2+1");
        Polynomial p2=new Polynomial("-x^3+3x^2-5");
        assertEquals("4x^2 -4",Operation.add(p1,p2).toString());
    }

    @Test
    @DisplayName("Addition test 2")
    public void addTest2(){
        Polynomial p1=new Polynomial("-x^3+x^2+17");
        Polynomial p2=new Polynomial("x^3-x^2-17");
        assertEquals("0",Operation.add(p1,p2).toString());
    }

    @Test
    @DisplayName("Substraction test 1")
    public void substarctTest1(){
        Polynomial p1=new Polynomial("-x^3+x^2+17");
        Polynomial p2=new Polynomial("x^3+x^2+5");
        assertEquals("-2x^3 +12",Operation.substarct(p1,p2).toString());
    }

    @Test
    @DisplayName("Substraction test 2")
    public void substractTest2(){
        Polynomial p1=new Polynomial("-x^4+x+17");
        Polynomial p2=new Polynomial("-x^3+x^2");
        assertEquals("-x^4 +x^3 -x^2 +x +17",Operation.substarct(p1,p2).toString());
    }

    @Test
    @DisplayName("Multiplication test 1")
    public void multiplyTest1(){
        Polynomial p1=new Polynomial("x^3+x^2+x-1");
        Polynomial p2=new Polynomial("2x^3");
        assertEquals("2x^6 +2x^5 +2x^4 -2x^3",Operation.multiplication(p1,p2).toString());
    }

    @Test
    @DisplayName("Multiplicatyon 2")
    public void multiplyTest2(){
        Polynomial p1=new Polynomial("x^3+2x");
        Polynomial p2=new Polynomial("3x^3 -x -2");
        assertEquals("3x^6 +5x^4 -2x^3 -2x^2 -4x",Operation.multiplication(p1,p2).toString());
    }

    @Test
    @DisplayName("Division test 1")
    public void devideTest1(){
        Polynomial p1=new Polynomial("6x^4+x^3-3x^2-4x");
        Polynomial p2=new Polynomial("x^3-x^2+x+3");
        assertEquals("6x +7",Operation.division(p1,p2)[0].toString());
        assertEquals("-2x^2 -29x -21",Operation.division(p1,p2)[1].toString());
    }

    @Test
    @DisplayName("Division test 2")
    public void divideTest2(){
        Polynomial p1=new Polynomial("3x^4 -61x^2");
        Polynomial p2=new Polynomial("-x^4+3x^2-x+2");
        assertEquals("-3",Operation.division(p1,p2)[0].toString());
        assertEquals("-52x^2 -3x +6",Operation.division(p1,p2)[1].toString());
    }

    @Test
    @DisplayName("Derivative test 1")
    public void derivativeTest1(){
        Polynomial p1=new Polynomial("-2x^4+4x^3+2x+1");
        assertEquals("-8x^3 +12x^2 +2",Operation.derivative(p1).toString());
    }

    @Test
    @DisplayName("Derivative test 2")
    public void derivativeTest2(){
        Polynomial p1=new Polynomial("-9x^3-3x^2-x+1");
        assertEquals("-27x^2 -6x -1",Operation.derivative(p1).toString());
    }

    @Test
    @DisplayName("Integration test 1")
    public void integralTest1(){
        Polynomial p1=new Polynomial("-4x^3-3x^2-x+1");
        assertEquals("-x^4 -x^3 -0.5x^2 +x",Operation.integral(p1).toString());
    }

    @Test
    @DisplayName("Integration test 2")
    public void integralTest2(){
        Polynomial p1=new Polynomial("6x^2-4x+6");
        assertEquals("2x^3 -2x^2 +6x",Operation.integral(p1).toString());
    }

    @Test
    @DisplayName("toString Method test")
    public void toStringTest(){
        Polynomial p1=new Polynomial("8x^7+8x^6+10x^5+x^4-8x^3-x^2-3x-2");
        assertEquals("8x^7 +8x^6 +10x^5 +x^4 -8x^3 -x^2 -3x -2",p1.toString());
    }
}

