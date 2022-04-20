package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProgramStart {

    public static void main(String[] args) {

    GuiCreator gC = new GuiCreator();
    MeetFormatScreen myMeetFormatScreen = new MeetFormatScreen("startScreen", gC);

    }
}
