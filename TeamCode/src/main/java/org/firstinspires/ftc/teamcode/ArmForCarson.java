package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Hardware;
public class ArmForCarson extends LinearOpMode {
    private int UP_POS = 210;
    private int DOWN_POS = 0;
    private double ARM_SPEED = 0.6;
    private double ARM_IDLE = 0.2;
    //remember to edit hardware class to make arm a DcMotorEx
    public void runOpMode()
    {
        Hardware.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.startHardware(hardwareMap);
        waitForStart();

        while(opModeIsActive())
        {
            if (gamepad1.y)
            {
                armUp(ARM_SPEED, ARM_IDLE);
            }
            else if (gamepad1.a)
            {
                armDown(ARM_SPEED);
            }
        }
    }

    private void armUp(double upPower, double idlePower)
    {
        Hardware.arm.setTargetPosition(UP_POS);
        Hardware.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.arm.setPower(upPower);
        if (Hardware.arm.getCurrentPosition() >= UP_POS)
        {
            Hardware.arm.setTargetPosition(Hardware.arm.getCurrentPosition());
            Hardware.arm.setPower(idlePower);
        }
    }
    private void armDown(double power)
    {
        Hardware.arm.setTargetPosition((DOWN_POS));
        Hardware.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.arm.setPower(power);
        if (Hardware.arm.getCurrentPosition() <= DOWN_POS)
        {
            Hardware.arm.setPower(0);
        }
    }

}