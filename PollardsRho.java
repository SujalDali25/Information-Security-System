package MTE;
import java.math.BigInteger;
import java.util.Random;

class PollardsRho {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    public static BigInteger pollardsRho(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return BigInteger.TWO;

        Random rand = new Random();
        BigInteger x = new BigInteger(n.bitLength(), rand).mod(n);
        BigInteger y = x;
        BigInteger c = new BigInteger(n.bitLength(), rand).mod(n);
        BigInteger d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE)) {
            x = x.multiply(x).add(c).mod(n);
            y = y.multiply(y).add(c).mod(n);
            y = y.multiply(y).add(c).mod(n);
            d = gcd(x.subtract(y).abs(), n);
        }

        return d;
    }

    public static void main(String[] args) {
        BigInteger n = new BigInteger("10403"); // Example semi-prime (101 * 103)
        BigInteger factor = pollardsRho(n);
        System.out.println("Non-trivial Factor: " + factor);
    }
}
