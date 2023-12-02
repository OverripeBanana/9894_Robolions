package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain extends Hardware {
    private static final double Kp = 0.0007;
    public static final double SPEED_REDUCTION = 0.4;
    public static double verticalTurn;
    public static double horizontalTurn;

    public static void forward()
    {
        Hardware.m1.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m2.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m3.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m4.setDirection(DcMotor.Direction.REVERSE);
    }
    public static void backward()
    {
        Hardware.m1.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m2.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m3.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m4.setDirection(DcMotor.Direction.FORWARD);
    }
    public static void right()
    {
        Hardware.m1.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m2.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m3.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m4.setDirection(DcMotor.Direction.REVERSE);
    }
    public static void left()
    {
        Hardware.m1.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m2.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m3.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m4.setDirection(DcMotor.Direction.FORWARD);
    }
    public static void turnRight()
    {
        Hardware.m1.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m2.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m3.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m4.setDirection(DcMotor.Direction.FORWARD);
    }
    public static void turnLeft()
    {
        Hardware.m1.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m2.setDirection(DcMotor.Direction.REVERSE);
        Hardware.m3.setDirection(DcMotor.Direction.FORWARD);
        Hardware.m4.setDirection(DcMotor.Direction.REVERSE);
    }
    public static void SetModeToPosition()
    {
        Hardware.m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public static void SetModeToEncoders()
    {
        Hardware.m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Hardware.m1.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        Hardware.m2.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        Hardware.m3.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        Hardware.m4.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);

    }
    public static boolean IsBusy()
    {
        return Hardware.m1.isBusy() && Hardware.m2.isBusy() && Hardware.m3.isBusy() && Hardware.m4.isBusy();
    }
    public static void MoveChassisWithPower(double powerOne, double powerTwo,double powerThree, double powerFour)
    {
        Hardware.m1.setPower(powerOne);
        Hardware.m2.setPower(powerTwo);
        Hardware.m3.setPower(powerThree);
        Hardware.m4.setPower(powerFour);
    }
    public static void SetTargetPosition(int target)
    {
        Hardware.m3.setTargetPosition(target);
        Hardware.m1.setTargetPosition(target);
        Hardware.m2.setTargetPosition(target);
        Hardware.m4.setTargetPosition(target);
    }

    public static void initDrivetrain()
    {
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
        m1.setDirection(DcMotor.Direction.FORWARD);
        m2.setDirection(DcMotor.Direction.REVERSE);
        m3.setDirection(DcMotor.Direction.REVERSE);
        m4.setDirection(DcMotor.Direction.REVERSE);
    }
    public static void resetEncoders() {
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public static void runWithEncoders() {
        m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        m4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void driveWithInput(double x, double y)
    {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(verticalTurn), 1);
        double power1 = (y + x + verticalTurn) * SPEED_REDUCTION;
        double power2 = (y - x - verticalTurn) * SPEED_REDUCTION;
        double power3 = (y - x + verticalTurn) * SPEED_REDUCTION;
        double power4 = (y + x - verticalTurn) * SPEED_REDUCTION;
        power1 = Range.clip(power1, -1.0, 1.0);
        power2 = Range.clip(power2, -1.0, 1.0);
        power3 = Range.clip(power3, -1.0, 1.0);
        power4 = Range.clip(power4, -1.0, 1.0);
        m1.setPower(power1);
        m2.setPower(power2);
        m3.setPower(power3);
        m4.setPower(power4);
    }

    public static void correctHeading() {
        int leftPos = m1.getCurrentPosition() + m3.getCurrentPosition();
        int rightPos = m2.getCurrentPosition() + m4.getCurrentPosition();

        if (leftPos > rightPos) {
            Drivetrain.verticalTurn = -PID.doPIDStuff(leftPos, rightPos, Drivetrain.Kp);
        }
        else if (rightPos > leftPos) {
            Drivetrain.verticalTurn = PID.doPIDStuff(rightPos, leftPos, Drivetrain.Kp);
        }
    }
}
