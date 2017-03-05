## Execution ##

You can execute this .jar file from the command line. Move into the directory and run <./bubbleChallenge.jar>. If you don't see bubbles appearing, stop the program with ctrl+c and run the previous command again. In Linux, you may need to try multiple times in order for it to work.

On MacOS, the file is also runnable by double clicking the .jar file. However, on Linux it appears that you must use the command line. I have not been able to test the file on Windows.


## How it works ##
1. The program first reads in all the radii from radii.txt. For each radius, a Circle object is created with the specified radius and an unknown location. These Circles are then stored in an array for later reference.

2. The first Circle is accessed from the array and drawn to the center of the screen. This circle is the first circle to be called circleToDrawFrom.

3. The algorithm begins and checks if the next circle in the array can be drawn directly beside the circleToDrawFrom at 0 degrees. It checks if the position is available by checking the potential coordinates vs the coordinates of all other Circles. If the algorithm does not detect any overlaps then the coordinates are deemed valid and the Circle is drawn and its position updated for future reference.

4. The algorithm moves on to the next Circle in the array and checks to see if it can draw that Circle at 1 degree from the circleToDrawFrom. This checking/drawing process continues until it reaches 360 degrees (once here, no more Circles can be draw around the circleToDrawFrom)

5. Next, the circleToDrawFrom is reassigned to Circle drawn directly after the current circleToDrawFrom (in the case of the first iteration, the Circle which was drawn second now becomes the circleToDrawFrom).

6. Thus steps 3-5 are performed repeatedly until all the Circles in the array have been drawn.

## Attack Method ##

This attack proceeds under the assumption that the target file is called flag.txt and is located on the Desktop. The file responsible for doing this is Coordinate.java.

The key to this challenge is getting someone on the inside to run my code. I decided the best approach would be to solve a different challenge and to package the penetration attack along with the other challenge. Once the final package was complete, wrapping it all up in a jar file meant that the source code would not be immediately visible. Thus the attack is hidden inside the bubbles challenge.

When the bubbles challenge is launched, the attack  writes and runs a script file to copy flag.txt and move it into ./sources. Then, using java FTP and an outside server, the program exfiltrates flag.txt. After this, the program deletes the created script and copied flag.txt files.

I did not successfully recover the file. I imagine one of two things happened. Either 1) Matt Weeks never ran the file on his computer thus there wasn't a flag.txt to steal or 2) there is a firewall in place that stops out-of-the-ordinary communications like mine.
