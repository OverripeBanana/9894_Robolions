package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DriveTrainTest extends LinearOpMode {
    public void runOpMode() {
        Hardware.startHardware(hardwareMap);
        Drivetrain.initDrivetrain();
        boolean shouldReset = true;

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0) {
                Drivetrain.turn = gamepad1.right_trigger - gamepad1.left_trigger;
                shouldReset = true;
            }
            else {
                if (shouldReset) {
                    Drivetrain.resetEncoders();
                    Drivetrain.turn = 0;
                    shouldReset = false;
                }
                Drivetrain.correctHeading();
            }
            Drivetrain.driveWithInput(gamepad1.left_stick_x, gamepad1.left_stick_y);
        }
    }
}
