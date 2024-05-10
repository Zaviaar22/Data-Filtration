/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sampleproject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.Folder;

/**
 *
 * @author
 */
public class remote {

    public static void main(String[] args) {
        String servicePrincipalKey = "Z-t--V7LgbmlaHBRIWhM";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiNDI0ZjIzYTUtMTYxYy00NTU5LTk2OWMtNGI4NjA4ZDNiNjkxIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogIkc0bjhfTVFKd1lQa0l0dHJ0MmJfbFVYSnJIenFVb2szMXVBaFN2eWVFeEUiLAoJCSJ4IjogInJSd3Nkd2VmU3VCd2E0YThrd2VtR05ZaWZ5VXBGMzcxeHF4LU55SmQ1VGsiLAoJCSJ5IjogIk4yVUpTWFBmcUptUUI2YTRReTEtcGEzcTcxXzhYY2dhT3ZLczN3d0Vvd2siLAoJCSJkIjogIjNkSm9hSHc5YjE2bnc4TENacThfdDJlbmVkZmlJcE1Oa1B4c0x3bVlMNUEiLAoJCSJpYXQiOiAxNjc3Mjk3OTA5Cgl9Cn0";
        String repositoryId = "r-0001d410ba56";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);

        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);

        // Get information about the ROOT entry, i.e. entry with ID=1
        int rootEntryId = 1;
        Entry entry = client.getEntriesClient()
                .getEntry(repositoryId, rootEntryId, null).join();

        System.out.println(
                String.format("Entry ID: %d, Name: %s, EntryType: %s, FullPath: %s",
                        entry.getId(), entry.getName(), entry.getEntryType(), entry.getFullPath()));
        // // Get information about the child entries of the Root entry
        ODataValueContextOfIListOfEntry result = client
                .getEntriesClient()
                .getEntryListing(repositoryId, rootEntryId, true, null, null, null, null, null, "name", null, null,
                        null)
                .join();
        List<Entry> entries = result.getValue();
        for (Entry childEntry : entries) {
            System.out.println(
                    String.format("Child Entry ID: %d, Name: %s, EntryType: %s, FullPath: %s",
                            childEntry.getId(), childEntry.getName(), childEntry.getEntryType(),
                            childEntry.getFullPath()));
        }
        // Download an antry
        int entryIdToDownload = 7;
        File deleteFile = new File("Project\\remoteFile.txt");
        deleteFile.delete();
        final String FILE_NAME = "Project\\remoteFile.txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length == -1) {
                        break;
                    }
                    outputStream.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        client.getEntriesClient()
                .exportDocument(repositoryId, entryIdToDownload, null, consumer)
                .join();

        client.close();
    }
}
