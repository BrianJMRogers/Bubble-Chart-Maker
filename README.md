## Execution ##

You can execute this .jar file from the command line. Move into the directory and run <./bubbleChallenge.jar>. If you don't see bubbles appearing, stop the program with ctrl+c and run the previous command again. In Linux, you may need to try multiple times in order for it to work.

On MacOS, the file is also runnable by double clicking the .jar file. However, on Linux it appears that you must use the command line. I have not been able to test the file on Windows.


## How it works ##
1. The program first reads in all the radii. For each radius, a Circle object is created with the specified radius and unknown locations. These Circles are then stored in an array for later reference.

2. The first Circle is accessed from the array and drawn to the center of the screen. This circle is the first circle to be called circleToDrawFrom

3. The algorithm begins at 0 degrees and checks if the next circle can be drawn directly beside the circleToDrawFrom at 0 degrees. It checks if the position is available by checking the potential coordinates vs the coordinates of all other circles. If the algorithm does not detect any overlaps, then the area is deemed valid and the circle is drawn and its position added to the Circle class for future reference.

4. The algorithm moves on to the next Circle in the array and checks to see if it can draw that Circle at 1 degrees from the circleToDrawFrom. This checking/drawing process continues until it reaches 360 degrees (once here, no more Circles can be draw around the circleToDrawFrom)

5. Next, the circleToDrawFrom is reassigned to circle drawn directly after the circle which was previously assigned as the circleToDrawFrom (in the case of the first iteration, the Circle which was drawn second now becomes the circleToDrawFrom).

6. Thus steps 3-5 are performed repeatedly until all the Circles in the array have been drawn.
