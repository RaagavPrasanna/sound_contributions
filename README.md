# sound_contributions


## Name
Music Contributors Manager

## Description
This application communicates with an Oracle database to allow the user to manage contributions made to songs. The user must however have access to this database and provide their username and password to utilize this application.

## Visuals
![image](https://user-images.githubusercontent.com/93137749/154872763-1e743e9a-4d08-4c68-bfd8-4edeb3dc99a2.png)


## Installation
1. First open the project, preferably in the VsCode IDE.
2. Next click on the SongDBJavaFX.java file.
3. After that, click on the Run and Debug button to create a launch.json file
4. Following the line that says "project name", add a comma and then on another line add vmArgs to point to the javaFx location.
5. In the end, it should look something like this:
        {
            "type": "java",
            "name": "Launch SongDBJavaFX",
            "request": "launch",
            "mainClass": "Application.SongDBJavaFX",
            "projectName": "sound_contributions_c0494a5",
            "vmArgs": [
                "--module-path",
                "absolute path to /lib folder given in the javafx library included in the project, (Don't forget to add \\ when writing the path)",
                "--add-modules=javafx.controls"
             ]
        }
6. Finally run the project by going into the SongDBJavaFX.java file, and clicking on the run button above main method. (Traditional ctrl 5 to run will most likely not work)
7. Make sure you are connected to the Dawson College VPN or else the application will crash when any button is pressed.

## Authors and acknowledgment
https://github.com/RaagavPrasanna

https://github.com/DallaliRim

## Project status
Completed but may go back to it in the future for improvements.

