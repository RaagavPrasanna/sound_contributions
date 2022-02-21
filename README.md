# sound_contributions


## Name
Music Contributors Manager

## Description
- This application communicates with an Oracle database to allow the user to manage contributions made to songs. The user must however have access to this database and provide their username and password to utilize this application. This project was initially a GitLab project, but was manually transferred over. 


## Visuals
![image](https://user-images.githubusercontent.com/93137749/154872763-1e743e9a-4d08-4c68-bfd8-4edeb3dc99a2.png)

## Functionality
Logging In:
                
- The application simply will not work if the user does not login. Before using any of the application's functionalities, please assure that you are connected to the Dawson College VPN and login via the application to your Oracle database account.      

Retrieving Data:
- There are 3 categories of data that can be retrieved:
1. The first are the roles of the contributors. These can be searched by the RecordingID of the song or the name of the Compilation. The Database will then return all the roles for that contributor in that specific recording or Compilation.
2. The second are the details of a recording or compilation. This can be searched by inputting a recordingID or compilation name. The application will then return details such as the duration, date of release, etc.
3. Finally we have two queries that will return the roles of an artist, and all of the songs that they have contributed to. The first button will simply return all of the roles that they have ever done, whereas the second one will return all of the details of all of the songs that they have ever been a contributor in. 

Updating Data:
- The update functionality allows us to update any of the contributions data we would like to 
- For example if we would like to changeteh name of the specific compilation, we can do it.
![image](https://user-images.githubusercontent.com/93137749/154876049-0f3cd5fe-4cff-4998-85b0-600caf9c5910.png)
<img width="751" alt="image" src="https://user-images.githubusercontent.com/93137749/154876110-780a6df1-1d93-44f8-89fa-0582b5f7d50f.png">



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

