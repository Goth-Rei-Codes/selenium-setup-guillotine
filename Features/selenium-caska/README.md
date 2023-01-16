# Selenium Caska
### Say a big "No, no" to the monotony of writing mocks, with Caska!
This Selenium script will help you with Swift mocks for your tests. <br>
We know how it feels to check your test cases on Qase and face a stack of mocks to be applied, that's why Caska is here to rescue you!
<br><br>
| POV: YOU WRITE MOCKS BY YOURSELF|
| ------------- |
| <img align="block" src="https://github.com/Goth-Rei-Codes/selenium-setup-guillotine/blob/main/Resources/Images/Caska/tired_casca.png" width="350"> <img align="block" src="https://github.com/Goth-Rei-Codes/selenium-setup-guillotine/blob/main/Resources/Images/Caska/sad_casca.jpg" height="328"> |
    
# How is she gonna help you?
In this repository you will find a Selenium script that conveniently converts the mocks provided by a suite of test-cases on [Qase](https://qase.io/) into Swift's network requests
<br><br>
<p align="center">
<img style="margin-top:90px" src="https://i.ytimg.com/vi/2H-el9jEDXc/maxresdefault.jpg" width="70%">
</p>
<br>
Let's assume you need to create network requests for the mockups of this test case: <br><br>
<img style="margin-top:90px" src="https://github.com/Goth-Rei-Codes/selenium-setup-guillotine/blob/main/Resources/Images/Caska/test%20qase.png" width="110%">

Caska will generate as outcome a txt file that contains Swift instructions to execute network requests with mocks contained in the test case
```swift
  // CSK-CMD001
  func test_CSKCMD001() {
    mockServer.requestsHandler = RequestsHandler {
      $0.addMockedRequests(
        NetworkRequest(remotePath: "/server/api/authenticate", localPath: "prodifile.json", status: .ok, serveOnce: true),
        NetworkRequest(remotePath: "/server/api/updates", localPath: "updates_no_signed.json", status: .ok),
        NetworkRequest(remotePath: "/server/api/authenticate", localPath: "authenticate_maid.json", status: .internalServerError)
        )
    }
    // Do something
  }
```
|<h1>Doing so, your lazy ass will be happy! <img style="margin-top:90px" src="https://github.com/Goth-Rei-Codes/selenium-setup-guillotine/blob/main/Resources/Images/Caska/smiling_casca.png" width="70%"><br> :))</h1>|
| --------------------------------------------------------------------------- |

> **WARNING**: Once the script is run, you'll find the result in the "mockedTests.txt" file

# "This is amazing, but i don't know how to use Selenium :(("
What an idiot
<br><br>
Now it's the time! You can use [this repository](https://github.com/Goth-Rei-Codes/selenium-setup-guillotine) to setup your Selenium development environment.
Stop crying over yourself, and feel the power of Selenium, join Caska and change the world!
<h1>Reject simpness, embrace Caska and become a sigma</h1>
<p align="center">
<img style="margin-top:90px" src="https://github.com/Goth-Rei-Codes/selenium-setup-guillotine/blob/main/Resources/Images/Caska/playboi_casca.jpg" width="70%">
<br><br>YOU WILL BEG GIRLS STOP GIVING YOU ATTENTIONS
</p>
<h1>DON'T MISS YOUR OPPORTUNITIES</h1>
<p align="center">
<img style="margin-top:90px" src="https://github.com/Goth-Rei-Codes/selenium-setup-guillotine/blob/main/Resources/Images/Caska/this_could_be_caska.png" width="50%">
</p>
