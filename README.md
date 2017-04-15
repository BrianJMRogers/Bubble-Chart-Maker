## Execution ##

You can execute this .jar file from the command line. Move into the directory and run <./bubbleChallenge.jar>. If you don't see bubbles appearing, stop the program with ctrl+c and run the previous command again. In Linux, you may need to try multiple times in order for it to work.

On MacOS, the file is also runnable by double clicking the .jar file. However, on Linux it appears that you must use the command line. I have not been able to test the file on Windows.

## Modification ##
You can run the <createRadii.java> program in the <sources> folder to make your own radii. In the <createRadii.java> file, you can change the maximum radii size as well as change the number of radii produced.

Be sure to remove the existing <radii.txt> file before running <createRadii.java> or it will simply extend the <radii.txt> file.


## How it works ##
1. The program first reads in all the radii from radii.txt. For each radius, a Circle object is created with the specified radius and an unknown location. These Circles are then stored in an array for later reference.

2. The first Circle is accessed from the array and drawn to the center of the screen. This circle is the first circle to be called circleToDrawFrom.

3. The algorithm begins and checks if the next circle in the array can be drawn directly beside the circleToDrawFrom at 0 degrees. It checks if the position is available by checking the potential coordinates vs the coordinates of all other Circles. If the algorithm does not detect any overlaps then the coordinates are deemed valid and the Circle is drawn and its position updated for future reference.

4. The algorithm moves on to the next Circle in the array and checks to see if it can draw that Circle at 1 degree from the circleToDrawFrom. This checking/drawing process continues until it reaches 360 degrees (once here, no more Circles can be draw around the circleToDrawFrom)

5. Next, the circleToDrawFrom is reassigned to Circle drawn directly after the current circleToDrawFrom (in the case of the first iteration, the Circle which was drawn second now becomes the circleToDrawFrom).

6. Thus steps 3-5 are performed repeatedly until all the Circles in the array have been drawn.

## Notes ##
You may notice there are some circles that are a few pixels away from touching each other. This is a side effect of drawing the circles with color. Drawn only in black and white, all the circles touch each other exactly. 
