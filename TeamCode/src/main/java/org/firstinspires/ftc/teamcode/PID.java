package org.firstinspires.ftc.teamcode;

public class PID {
    public static double doPIDStuff(int reference, int currentState, double Kp) {
        double error = reference - currentState;
        return Kp  * error;
    }
}
