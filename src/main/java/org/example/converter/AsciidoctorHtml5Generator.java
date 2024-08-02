package org.example.converter;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;

import java.io.File;

public class AsciidoctorHtml5Generator {

    final static String swaggerPath = "D:/workspaces/documents/";

    public static void main(String[] args) {

        // Output HTML file path
        String outputFilePath = swaggerPath + "/asciidoc/index.html";

        // Configure Asciidoctor instance
        try (Asciidoctor asciidoctor = Asciidoctor.Factory.create()) {
            // Convert AsciiDoc to HTML5
            asciidoctor.convertFile(new File(swaggerPath + "/asciidoc/index.adoc"),
                    Options.builder().mkDirs(true)
                            .backend("html5")
                            .toFile(new File(outputFilePath))
                            .safe(SafeMode.UNSAFE) //allowed to generate pdf inside this directory only.
                            .build());
            System.out.println("HTML file generated successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
