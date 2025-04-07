package MTE;
import java.math.BigInteger;
import java.security.SecureRandom;

class MITM {
    private static final BigInteger P = new BigInteger("23"); // Prime number
    private static final BigInteger G = new BigInteger("5");  // Generator

    public static void main(String[] args) {
        SecureRandom rand = new SecureRandom();

        
        BigInteger alicePrivateKey = new BigInteger(5, rand);
        BigInteger bobPrivateKey = new BigInteger(5, rand);

       
        BigInteger alicePublic = G.modPow(alicePrivateKey, P);
        BigInteger bobPublic = G.modPow(bobPrivateKey, P);

        
        BigInteger attackerPrivateKey = new BigInteger(5, rand);
        BigInteger attackerPublic = G.modPow(attackerPrivateKey, P);

        
        BigInteger fakeAlicePublic = attackerPublic;
        BigInteger fakeBobPublic = attackerPublic;

        
        BigInteger aliceSharedSecret = fakeBobPublic.modPow(alicePrivateKey, P);
        BigInteger bobSharedSecret = fakeAlicePublic.modPow(bobPrivateKey, P);
        BigInteger attackerSharedSecret = alicePublic.modPow(attackerPrivateKey, P); // Same for Bob

        System.out.println("Alice's Secret: " + aliceSharedSecret);
        System.out.println("Bob's Secret: " + bobSharedSecret);
        System.out.println("Attacker's Secret: " + attackerSharedSecret);
    }
}
