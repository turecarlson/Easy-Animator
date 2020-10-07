Part 1:

We designed our model based on the few details that we had from part 1. Knowing that we will
likely need to take in input in the form of shapes and commands, that both the initial states of
the shapes and all the commands fed to the model in either a dynamic or static context will need to
be stored and then output, we thought that the most flexible way to accommodate that would be for
an animation model to store both a list of shapes and the list of commands given to act on those
shapes. Similarly we used an interface and abstract class to standardize the input and shared
functionality of both the commands and the shapes that the animation represents- move, scale,
change color, and rectangle and oval respectively. In both cases the concrete classes rely heavily
on their super(abstract) classes to make sure that as much code as possible is centralized and that
in the future when we will have a better idea of the changes we need to make (or not make) to the
objects stored in the model, we will be able to add new methods to act on each shape without having
to worry too much about specifying class specific operations.

Another consideration that we put a lot of thought into was preserving the flexibility of colors
fed into our model.We employed java's built in color class while also allowing the user to input
rgb floats so that we could take advantage of the flexibility of the color class, ease development
(a note by Dr. Bagley), and store the color in one variable instead of 3. Any Object methods that
directly involve an object's color (including concrete constructors) are designed to take either a
Color Object or ints representing RGB values. Those RGB values are then used to construct
anew Color Object.

Part 2:

For part 2 we expanded on the progress we made for week one including adding all three views,
textual, visual, and SVG, as well expanding our model with a builder and view factory to make the
animation run. Beginning with command line arguments to the jar file, our program parses and the
specified in file with the starter code, feeds it into a builder located within our model, and then
into a factory of views that creates the specified view accordingly. If invalid arguments are
passed, an error message is generated and the program quits.

For our views we did the following:

Text: We used the output description that we generated from part 1 and output it to stdout or a
provided out file.

SVG: Expanding on the idea that in the beginning our shapes and commands mainly existed to provide
a string representation of themselves, we delegated the work of generating the SVG code to each
shape and command. While causing us to need to somewhat refactor the parameters of our shapes and
commands we found this was a very effective tool.

Visual: We rendered the output in swing using a timer and action listener. With each iteration of
the timer, corresponding to a tick, all shapes that exist in the given frame are rendered, and then
updated using the tweening process explained in the spec. While it is not good mvc architecture to
have the view update the model, when we are able to make a proper controller we will shift this
functionality there so that we do not run into this issue.

Finally, several changes were made throughout the model to adapt from week 1 where we dealt mainly
with floats to this week where all input was integer based.

Part 3:

For this week we made several important changes to the structure of the project to improve design
take action on feedback, and improve efficiency.

Views:

As required for the assignment we both reused and refactored our visual view for use with the
playback view. We did this by improving our design for the visual view by separating the frame and
panel into separate classes, such that both current views and future views can use the panel.
Furthermore, we abstracted almost the entirety of visual into an abstract swing view that again
would allow for the current needs and future expansion. Next, because we were finally able to
implement a controller we refactored the way the swing view and model interact with each other.
While originally we opted to update the states of each shape in real time for each tick, this week
we improved our design by creating a list of shapes by tick in advance for each view to read.
Throughout the week we also spent a lot of time discussing tradeoffs in speed, memory, and
preserving the integrity of the original list vs. creating a copy- finally settling on our final
implementation that preserves the original list at the expense of memory but with a run time that
is much faster than our previous implementation.

Model:

Similarly, we also focused on ensuring the integrity of our model and the separation of concerns
between our model and views by creating a separate read only model interface. As discussed above,
we also created an instance variable for the model that holds a list of lists that contains the
value of each shape at each tick in the animation. For the sake of efficiency, it is not created
for each type of view but can be expanded to accomplish future requirements with about 5 lines of
code should the need arise. We also added new getters and setters to improve access and
expandability at the suggestion of our grader from our first assignment.

Controller:
Finally, as touched upon above we also have created a controller to serve as an intermediary
between our swing views and model. While it it does not work for either our text or SVG views, as
they do not require user interaction, it can be integrated with any type visual views that will
require users to interact with the animation in the future.