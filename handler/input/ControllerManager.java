package handler.input;

import com.studiohartman.jamepad.ControllerState;

public class ControllerManager extends com.studiohartman.jamepad.ControllerManager {

    com.studiohartman.jamepad.ControllerManager controllers = new com.studiohartman.jamepad.ControllerManager();
    private float[] joystick1, joystick2;
    private float xMovement, yMovement, xAttack, yAttack;
    public ControllerManager()
    {
        controllers.initSDLGamepad();
        joystick1 = new float[2];
        joystick2 = new float[2];

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
    }
}
