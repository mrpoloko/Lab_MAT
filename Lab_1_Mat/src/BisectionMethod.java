
import java.math.BigDecimal;
import java.math.MathContext;

    public class BisectionMethod {
        public static BigDecimal expNegX(BigDecimal x, MathContext mc) {
            // Используем разложение в ряд Тейлора для exp(-x)
            BigDecimal result = BigDecimal.ONE;  // Начинаем с 1 (exp(0) = 1)
            BigDecimal factor = BigDecimal.ONE;  // Начинаем с 1 / 0! = 1

            for (int n = 1; n < 50; n++) {
                factor = factor.multiply(x.negate()).divide(new BigDecimal(n), mc);  // Вычисляем следующий множитель (x^n / n!)
                result = result.add(factor);
            }

            return result.round(mc);
        }
        // Функция f(x) = e^(-x) - x^3
        private static BigDecimal f(BigDecimal x) {
            MathContext mc = MathContext.DECIMAL128;
            BigDecimal expNeg = expNegX(x, mc);
            BigDecimal xCubed = x.pow(3, mc);
            return expNeg.subtract(xCubed, mc);
        }

        public static BigDecimal BISEC(BigDecimal a, BigDecimal b, BigDecimal eps) {
            MathContext mc = MathContext.DECIMAL128;
            BigDecimal mid = a; // Точка середины отрезка
            int iterations = 0; // Счетчик итераций
            BigDecimal eps2 = eps.multiply(new BigDecimal(2));
            byte aSig = (byte) f(a).signum(); // f(a) < 0 -> -1, f(a) > 0 -> 1, f(a) -> 0

            while (b.subtract(a).compareTo(eps2) > 0) {
                mid = a.add(b, mc

                ).divide(BigDecimal.valueOf(2), mc); //(a+b)/2
                BigDecimal fMid = f(mid);

                if (fMid.compareTo(BigDecimal.ZERO) == 0) { // Если найден точный корень
                    break;
                }

                if (aSig != fMid.signum()) { // Сравниваем знак f(a) и f(fMid)
                    b = mid; // Корень лежит в [a, mid]
                } else {
                    a = mid; // Корень лежит в [mid, b]
                }

                iterations++;
            }

            System.out.println("Количество итераций: " + iterations);
            return mid;
        }
}
