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
            telemetry.addData("Left Pos", Hardware.m1.getCurrentPosition() + Hardware.m3.getCurrentPosition());
            telemetry.addData("Right Pos", Hardware.m2.getCurrentPosition() + Hardware.m4.getCurrentPosition());
            telemetry.addData("Back Pos", Hardware.m3.getCurrentPosition() + Hardware.m4.getCurrentPosition());
            telemetry.addData("Front Pos", Hardware.m2.getCurrentPosition() + Hardware.m4.getCurrentPosition());
            telemetry.addData("M1", Hardware.m1.getPower());
            telemetry.addData("M2", Hardware.m2.getPower());
            telemetry.addData("M3", Hardware.m3.getPower());
            telemetry.addData("M4", Hardware.m4.getPower());
            telemetry.addData("Turn", Drivetrain.verticalTurn);

            boolean isPressingTriggers = gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0;
            boolean isPressingJoystick = gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0;

            if (isPressingTriggers) {
                Drivetrain.verticalTurn = (gamepad1.right_trigger - gamepad1.left_trigger) * 0.3;
            }
            else if (!isPressingTriggers && isPressingJoystick) {
                Drivetrain.correctHeading();
            }
            else if (!isPressingTriggers && !isPressingJoystick) {
                if (Hardware.m1.getCurrentPosition() + Hardware.m3.getCurrentPosition() != 0 || Hardware.m2.getCurrentPosition() + Hardware.m4.getCurrentPosition() != 0) {
                    Drivetrain.resetEncoders();
                    Drivetrain.runWithEncoders();
                }
                Drivetrain.verticalTurn = 0;
            }

            Drivetrain.driveWithInput(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}
