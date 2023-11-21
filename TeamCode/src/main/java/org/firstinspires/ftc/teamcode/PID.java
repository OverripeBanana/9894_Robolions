package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;

public class PID {
    public static double doPIDStuff(int reference, int currentState, double Kp) {
        //left = 500, right = 200
        double error = reference - currentState; //error = 300
        double output = Kp * error; //output = 0.24
        return output;
    }
}
