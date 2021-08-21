package ventus.rggwheel.utils;

import ventus.rggwheel.model.PrizeEnum;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class WheelUtils {

    private static final double WHEEL_ANGLE = 360;

    private static final EnumSet<PrizeEnum> prizeSet = EnumSet.allOf(PrizeEnum.class);
    private static final int numberOfPrizes = prizeSet.size();
    private static final ArrayList<PrizeEnum> prizesList = new ArrayList<>(prizeSet);

    private static final double angleOfPrizeCenter = numberOfPrizes / 2.00;

    public static int wheelMultiplier = 1;

    private static final Random rng = new SecureRandom();

    public static Double getAngle() {
        return WHEEL_ANGLE / numberOfPrizes;
    }

    public static Double getMiddlePositionAngle() {
        return WHEEL_ANGLE / numberOfPrizes / 2;
    }

    public static int indicatedPrize(double wheelAngleRotation) {
        double absoluteRotation = wheelAngleRotation % 360;
        for (int i = 1; i <= numberOfPrizes; i++) {
            if (absoluteRotation < getAngle() * i) {
                return numberOfPrizes - i;
            }
        }
        return 0;
    }

    public static double getRandomAngle(double currentRotation, int spinTime) {
        double randomAngle;
        double currentPlacement;
        do{
            randomAngle = rng.nextDouble()
                    + rng.nextInt(360)
                    + (180 - rng.nextInt(180) - rng.nextDouble())
                    + (rng.nextInt(5) + 1) * spinTime * 360
                    + spinTime * (1+rng.nextDouble());
            currentPlacement = (currentRotation + randomAngle) % 360 % 20;
        } while (currentPlacement > 19.5 || currentPlacement < 0.5);
        return randomAngle;
    }

    public static int getRandomNumberOfPrize() {
        return rng.nextInt(numberOfPrizes) + 1;
    }

    public static int getRandomTime() {
        return rng.nextInt(89) + 10;
    }

    public static boolean getRandomBoolean() {
        return rng.nextBoolean();
    }

    public static int getRandomInt(int max) {
        return rng.nextInt(max);
    }
}
