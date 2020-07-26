package com.javarush.task.task13.task1328;

public abstract class AbstractRobot implements Attackable, Defensable {

    private static int hitCount;

    public BodyPart attack() {
        BodyPart attackedBodyPart = null;
        AbstractRobot.hitCount = AbstractRobot.hitCount + 1;

        if (AbstractRobot.hitCount == 1) {
            attackedBodyPart = BodyPart.ARM;
        } else if (AbstractRobot.hitCount == 2) {
            attackedBodyPart = BodyPart.HEAD;
        } else if (AbstractRobot.hitCount == 3) {
            attackedBodyPart = BodyPart.LEG;
        } else if (AbstractRobot.hitCount == 4) {
            //AbstractRobot.hitCount = 0;
            attackedBodyPart = BodyPart.CHEST;
        }
        return attackedBodyPart;
    }

    public BodyPart defense() {
        BodyPart defencedBodyPart = null;
        //AbstractRobot.hitCount = AbstractRobot.hitCount + 1;

        if (AbstractRobot.hitCount == 1) {
            defencedBodyPart = BodyPart.HEAD;
        } else if (AbstractRobot.hitCount == 2) {
            defencedBodyPart = BodyPart.LEG;
        } else if (AbstractRobot.hitCount == 3) {
            defencedBodyPart = BodyPart.ARM;
        } else if (AbstractRobot.hitCount == 4) {
            AbstractRobot.hitCount = 0;
            defencedBodyPart = BodyPart.CHEST;
        }
        return defencedBodyPart;
    }

    abstract public String getName();
}
