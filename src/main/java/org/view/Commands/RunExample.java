package org.view.Commands;

import org.controller.Controller;
import org.exceptions.DeclarationError;
import org.exceptions.EmptyCollectionError;
import org.exceptions.ImproperTypeError;
import org.exceptions.ZeroDivisionError;
import org.model.PrgState;

import java.io.IOException;

public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String desc, Controller ctr)
    {
        super(key,desc);
        this.ctr = ctr;

    }
    void displayPrgState(PrgState state)
    {
        System.out.println(state.toString());
    }
    @Override
    public void execute() {
        try{
            ctr.allStep();
        }
        catch (EmptyCollectionError | ZeroDivisionError | DeclarationError | ImproperTypeError | IOException | InterruptedException e)
        {
            System.out.println(e.getMessage());

        }

    }
}
