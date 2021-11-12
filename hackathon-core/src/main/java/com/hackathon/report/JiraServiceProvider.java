package com.hackathon.report;

import lombok.extern.slf4j.Slf4j;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class JiraServiceProvider
{
    private final JiraClient jira;
    private final String project;

    public JiraServiceProvider()
    {
        BasicCredentials creds = new BasicCredentials("testhackathon", "testhackathon");
        jira = new JiraClient("http://jira:8080/", creds);
        this.project = "TH";
    }

    public void createJiraIssue(String issueType, String summary, String description, String reporterName, String base64Screenshot)
    {
        try
        {
            Issue.FluentCreate newIssueFluentCreate = jira.createIssue(project, issueType);
            newIssueFluentCreate.field(Field.SUMMARY, summary);
            newIssueFluentCreate.field(Field.DESCRIPTION, description);
            newIssueFluentCreate.field(Field.REPORTER, reporterName);
            Issue newIssue = newIssueFluentCreate.execute();
            newIssue.addAttachment(getImageFileFromBase64(base64Screenshot));
            log.info("New issue created. Jira ID : " + newIssue);

        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    private File getImageFileFromBase64(String base64)
    {
        File outputfile = null;
        try
        {
            byte[] decodedBytes = Base64.decodeBase64(base64);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            Path folderPath = Paths.get(System.getProperty("user.dir") + "/screenshots");
            if (!Files.exists(folderPath))
            {
                Files.createDirectory(folderPath);
            }
            String path = FilenameUtils.normalize(System.getProperty("user.dir") + "/screenshots/ss-" + (System.currentTimeMillis() / 1000L + ".png"));
            outputfile = new File(path);
            ImageIO.write(ImageIO.read(bis), "png", outputfile);
            bis.close();
            return outputfile;
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return outputfile;
    }
}
