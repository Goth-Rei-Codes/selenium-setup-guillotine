# Selenium Gatsu
### Tired of Jenkins?
Feel depressed by comparing builds on Jenkins?
Do you feel tired, irritated, annihilated by Jenkins' monotony and heaviness?
<br><br>
<div>
<img align="left" src="https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine/blob/main/Resources/Images/Gatsu/sad_gatsu.jpg" width="350">
    <p style="display:inline-block">Damn bro, look at your face LMFAOOO<br>
    Don't you think you need help?</p>
</div>
<pre>
<h1>Gatsu is here to help you!</h1>
<img align="right" src="https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine/blob/main/Resources/Images/Gatsu/kitty_gatsu.jpg" width="350">
    <div align="right"><p style="padding-left:20px;">Reject Jenkins' boredom,<br> embrace Gatsu's masculinity</p></div>
</div>
</pre>

### How is he gonna help you?
In this repository you will find a Selenium script that conveniently processes the test results of Jenkins builds, and generates files with the differences of the failed tests between one build and another.
<br><br>
<p align="center">
The generated files will include all the failed tests that are listed in the build A but not in the build B (Left Join) and vice versa (Right Join)
<br><br>
<img style="margin-top:90px" src="https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine/blob/main/Resources/Images/Gatsu/build1.png" width="200px">
<img style="margin-top:90px" src="https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine/blob/main/Resources/Images/Gatsu/build2.png" width="200px">
</p>
<br><br>

> **WARNING**: All you have to do is download as an HTML file the test results pages of the builds you want to compare and paste their paths in the Main class! (search "BUILD1 PATH" etc..)

<br><br>

Sounds dope, right? Now give me a nice smile ^w^
<pre>
<p align="center">
<img  src="https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine/blob/main/Resources/Images/Gatsu/happy_gatsu.jpg" width="70%">
<p align="center">That's better :))</p>
</p>
</pre>
  
### "This is amazing, but i don't know how to use Selenium :(("
Poor kid.
<br><br>
Now it's the time! You can use [this repository](https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine) to setup your Selenium development environment.
Stop crying over yourself, and feel the power of Selenium, join Gatsu and change the world!

<p align="center">
<br><br>
<img style="margin-top:90px" src="https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine/blob/main/Resources/Images/Gatsu/thumbs_up_gatsu.jpg" width="500px">
</p>

# Updates
*  2/9/2022
    * Code improvements
    * It is now possible to choose between 2 type of builds' analysis:
       ### Analysis with no failed
        The method generateAnalysisNoFailed() generates 3 files:
        * Build1 unique tests list
        * Build2 unique tests list
        * Build1 and Build2 common tests list
        
        <br>
        
        > **WARNING**: There's no need to execute a single build, this is useful before builds' execution
        
        ### Analysis with failed
        The method generateAnalysisWithFailed() generates 4 files:
        * Build1 unique tests list
        * Build2 unique tests list
        * Build1 and Build2 common tests list
        * Build1 and Build2 common failed tests list
        
        <br>
        
        > **WARNING**: There are some requirements for this: <br>
            * Build1 must have been executed before this analysis <br>
            * A list of all Build1 failed test is required <br>
            * The list of Build1 failed must be pasted in _real_failed.txt_ file, or change the file path in the gatsu.readfile() method calls <br>
            This is useful post Build1 execution, or even both builds execution
