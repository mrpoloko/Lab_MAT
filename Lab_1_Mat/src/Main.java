import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        BigDecimal a = BigDecimal.valueOf(0);
        BigDecimal b = BigDecimal.valueOf(1);
        BigDecimal eps = BigDecimal.valueOf(1e-18);

        BigDecimal root = BisectionMethod.BISEC(a, b, eps);
        System.out.printf("Приближенное значение корня: %.17f\n", root);
    }
}