## Attack Method ##

This attack proceeds under the assumption that the target file is called flag.txt and is located on the Desktop. The file responsible for doing this is Coordinate.java.

The key to this challenge is getting someone on the inside to run my code. I decided the best approach would be to solve a different challenge and to package the penetration attack along with the other challenge. Once the final package was complete, wrapping it all up in a jar file meant that the source code would not be immediately visible. Thus the attack is hidden inside the bubbles challenge.

When the bubbles challenge is launched, the attack  writes and runs a script file to copy flag.txt and move it into ./sources. Then, using java FTP and an outside server, the program exfiltrates flag.txt. After this, the program deletes the created script and copied flag.txt files.

I did not successfully recover the file. I imagine one of two things happened. Either 1) Matt Weeks never ran the file on his computer thus there wasn't a flag.txt to steal or 2) there is a firewall in place that stops out-of-the-ordinary communications like mine.
