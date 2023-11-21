package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware;

public class Drivetrain extends Hardware {
    private static double power1;
    private static double power2;
    private static double power3;
    private static double power4;
    private static double Kp = 0.0008;
    public static double turn;
    public static void initDrivetrain() {
        //reset
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //mode
        m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //brake
        m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //direction
        m1.setDirection(DcMotor.Direction.REVERSE);
        m2.setDirection(DcMotor.Direction.FORWARD);
        m3.setDirection(DcMotor.Direction.FORWARD);
        m4.setDirection(DcMotor.Direction.FORWARD);
    }
    public static void driveWithInput(double x, double y) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(Drivetrain.turn), 1);
        power1 = (y + x + Drivetrain.turn) / denominator;
        power2 = (y - x - Drivetrain.turn) / denominator;
        power3 = (y - x + Drivetrain.turn) / denominator;
        power4 = (y + x - Drivetrain.turn) / denominator;
        m1.setPower(power1);
        m2.setPower(power2);
        m3.setPower(power3);
        m4.setPower(power4);
    }
    public static void correctHeading() {
        int leftPos = m1.getCurrentPosition() + m3.getCurrentPosition();
        int rightPos = m2.getCurrentPosition() + m4.getCurrentPosition();

        if (leftPos > rightPos) {
            Drivetrain.turn = PID.doPIDStuff(leftPos, rightPos, -Drivetrain.Kp);
        }
        else if (rightPos > leftPos) {
            Drivetrain.turn = PID.doPIDStuff(rightPos, leftPos, Drivetrain.Kp);
        }
    }
}