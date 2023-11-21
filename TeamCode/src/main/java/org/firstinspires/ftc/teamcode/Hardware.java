package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.CRServo;


public class Hardware {
    public static Servo airplane;
    public static DcMotorEx arm;
    public static Servo claw;
    public static DcMotor lift;
    public static DcMotor m1;//Top Left
    public static DcMotor m2;//Bottom Left
    public static DcMotor m3;//Top Right
    public static DcMotor m4;// Bottom Right
    public static Servo owl;
    public static Servo wrist;

    public static void startHardware(HardwareMap hardwareMap)
    {
        m1 = hardwareMap.get(DcMotor.class, "m1");
        m2 = hardwareMap.get(DcMotor.class, "m2");
        m3 = hardwareMap.get(DcMotor.class, "m3");
        m4 = hardwareMap.get(DcMotor.class, "m4");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        lift = hardwareMap.get(DcMotor.class, "lift");
        airplane = hardwareMap.get(Servo.class, "airplane");
        claw = hardwareMap.get(Servo.class, "claw");
        owl = hardwareMap.get(Servo.class, "owl");
        wrist = hardwareMap.get(Servo.class, "wrist");
    }
}
