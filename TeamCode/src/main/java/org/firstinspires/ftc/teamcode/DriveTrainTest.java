package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DriveTrainTest extends LinearOpMode {
    public void runOpMode() {
        Hardware.startHardware(hardwareMap);
        Drivetrain.initDrivetrain();

        waitForStart();
        while (opModeIsActive()) {
            boolean isMoving = Hardware.m1.isBusy() || Hardware.m2.isBusy() || Hardware.m3.isBusy() || Hardware.m4.isBusy();
            if (!isMoving) {
                Drivetrain.resetEncoders();
            }

            if (gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0) {
                Drivetrain.turn = gamepad1.right_trigger - gamepad1.left_trigger;
            }
            else {
                Drivetrain.correctHeading();
            }
            Drivetrain.driveWithInput(gamepad1.left_stick_x, gamepad1.left_stick_y);
        }
    }
}
