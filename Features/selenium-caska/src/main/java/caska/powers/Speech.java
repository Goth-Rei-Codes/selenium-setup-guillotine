package caska.powers;

import caska.Mock;
import caska.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Goth-Rei-Codes
 */

public class Speech {
    private final ChromeDriver driver;
    HashMap<String, String> statusCodes = new HashMap<String, String>();

    public Speech(ChromeDriver driver) { this.driver = driver; }

    public void populateStatusCodeMap(){
        statusCodes.put("200", ".ok");
        statusCodes.put("201", ".created");
        statusCodes.put("202", ".accepted");
        statusCodes.put("204", ".noContent");
        statusCodes.put("301", ".movedPermanently");
        statusCodes.put("400", ".badRequest");
        statusCodes.put("401", ".unauthorized");
        statusCodes.put("403", ".forbidden");
        statusCodes.put("404", ".notFound");
        statusCodes.put("405", ".methodNotAllowed");
        statusCodes.put("500", ".internalServerError");
        statusCodes.put("503", ".custom(503)");
        statusCodes.put("999", ".unavailableNetwork");
    }

    public void translate(List<Test> suite) {
        populateStatusCodeMap();
        suite.forEach(test -> {
            System.out.println("- - - - - - - - - - - - - - Test: " + test.getName());
            System.out.println("\nMocks: ");
            List<String> remotePaths = new ArrayList<>();
            List<String> finalRemotePaths = remotePaths;
            test.getMocks().forEach(mock -> {
                int remotePathOccurrences = 0;
                for(int x = 0; x<test.getMocks().size(); x++){
                    if(test.getMocks().get(x).getRemotePath().equals(mock.getRemotePath())){
                        remotePathOccurrences++;
                    }
                }
                finalRemotePaths.add(mock.getRemotePath());
                mock.setServeOnce(remotePathOccurrences > 1);
            });
            remotePaths = remotePaths.stream().distinct()
                    .collect(Collectors.toList());
            List<Mock> reversedMocks = test.getMocks().subList(0, test.getMocks().size());
            Collections.reverse(reversedMocks);
            for(int x=0; x<remotePaths.size(); x++){
                for(int y=0; y<reversedMocks.size(); y++){
                    if(reversedMocks.get(y).getRemotePath().equals(remotePaths.get(x)) && reversedMocks.get(y).isServeOnce()){
                        reversedMocks.get(y).setServeOnce(false);
                        break;
                    }
                }
            }
            Collections.reverse(reversedMocks);
            test.setMocks(reversedMocks);
            test.getMocks().forEach(mock -> {
                mock.setStatusCode(statusCodes.get(mock.getStatusCode()));
                System.out.println("Remote Path: " + mock.getRemotePath()
                        + "\nLocal Path: " + mock.getLocalPath()
                        + "\nStatus Code: " + mock.getStatusCode()
                        + "\nIgnoreQueryParams: " + mock.isIgnoreQueryParams()
                        + "\nServeOnce: " + mock.isServeOnce()
                        + "\n\n"
                );
            });
        });
        publish(suite);
    }

    public void publish(List<Test> suite) {
        try {
            File file = new File("mockedTests.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("mockedTests.txt");

            suite.forEach(test -> {
                List<Mock> testMocks = test.getMocks();
                String mockedTest = "  // " + test.getName() + "\n" +
                        "  func test_" + test.getName().replace("-", "").replace("/", "_") +
                        "() {\n    mockServer.requestsHandler = RequestsHandler {\n" +
                        "      $0.addMockedRequests(\n";
                for(int x = 0; x< testMocks.size(); x++) {
                    Mock mock = testMocks.get(x);
                    String networkRequest = "        NetworkRequest(" +
                            "remotePath: \""+ mock.getRemotePath() +"\", " +
                            "localPath: \""+ mock.getLocalPath() +"\", " +
                            "status: "+ mock.getStatusCode();
                    if(mock.isServeOnce()){
                        networkRequest += ", serveOnce: true";
                    }
                    if(mock.isIgnoreQueryParams()) {
                        networkRequest += ", ignoreQueryParams: true";
                    }
                    if(x < testMocks.size() -1) {
                        networkRequest += "),\n";
                    } else {
                        networkRequest += ")\n";
                    }
                    mockedTest += networkRequest;
                }
                mockedTest += "        )\n    }\n  " +
                        "    // Do something\n  }\n\n";
                System.out.println(mockedTest);
                try {
                    myWriter.write(mockedTest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
