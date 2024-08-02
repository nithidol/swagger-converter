package org.example.converter;


import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;
import org.asciidoctor.jruby.internal.JRubyAsciidoctor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SwaggerToPDF {

    final static String swaggerPath = "D:/workspaces/swagger-converter/documents/";
    final static String json = "api-docs.json";

    public static void main(String[] args) {

        //create asciidoc file
        OpenApiToAsciidoc asciidoc = new OpenApiToAsciidoc();
        asciidoc.jsonToAsciidoc(swaggerPath, json);

        // Initialize Asciidoctor instance
        Asciidoctor asciidoctor = JRubyAsciidoctor.create();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        //output path to a pdf file.
        File pdf = new File(swaggerPath + "/pdf/user-manual-openapi_" + df.format(new Date()) + ".pdf");
        System.out.println("Start generating pdf with Asciidoctor: index.adoc to " + pdf.getAbsolutePath());

        // Convert AsciiDoc to PDF
        asciidoctor.convertFile(new File(swaggerPath + "/asciidoc/index.adoc"),
                Options.builder().mkDirs(true)
                        .backend("pdf")
                        .toFile(pdf)
                        .safe(SafeMode.SAFE) //allowed to generate pdf inside this directory only.
                        .build());

        System.out.println("PDF is created!");
    }
}
