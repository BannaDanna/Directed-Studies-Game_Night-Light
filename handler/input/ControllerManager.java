package handler.input;

import com.studiohartman.jamepad.ControllerState;

public class ControllerManager extends com.studiohartman.jamepad.ControllerManager {

    com.studiohartman.jamepad.ControllerManager controllers;
    private float[] joystick1, joystick2;
    public float xMovement, yMovement, xAttack, yAttack;

    private boolean[] actionButtons, dpad, joystickPress, joystickJustPressed;

    public boolean square, triangle, circle, X, lJoystick, rJoystick, lJoystickJust, rJoystickJust, share, options, connected;
    public ControllerManager()
    {
        controllers = new com.studiohartman.jamepad.ControllerManager();
        controllers.initSDLGamepad();
        joystick1 = new float[2];
        joystick2 = new float[2];
        actionButtons = new boolean[4];
        joystickPress = new boolean[2];
        joystickJustPressed = new boolean[2];
        joystick1[0] = xMovement;
        joystick1[1] = yMovement;
        joystick2[0] = xAttack;
        joystick2[1] = yAttack;
        actionButtons[0] = square;
        actionButtons[1] = triangle;
        actionButtons[2] = circle;
        actionButtons[3] = X;
        joystickPress[0] = lJoystick;
        joystickPress[1] = rJoystick;
        joystickJustPressed[0] = lJoystickJust;
        joystickJustPressed[1] = rJoystickJust;

    }

    public void tick()
    {
            ControllerState currState = controllers.getState(0);
            if(!currState.isConnected) {
                return;
            }
            xMovement = currState.leftStickX;
            yMovement = currState.leftStickY;
            xAttack = currState.rightStickX;
            yAttack = currState.rightStickY;
            square = currState.x;
            triangle = currState.y;
            circle = currState.b;
            X = currState.a;
            lJoystick = currState.leftStickClick;
            lJoystickJust = currState.leftStickJustClicked;
            rJoystick = currState.rightStickClick;
            share = currState.back;
            options = currState.start;
            connected = currState.isConnected;
//            System.out.println(xMovement + ", " + yMovement);
    }

    public void vibrate()
    {
        controllers.doVibration(0,1,1, 100000);
    }
}
