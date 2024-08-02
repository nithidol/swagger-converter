package org.example.converter;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;
import org.asciidoctor.jruby.internal.JRubyAsciidoctor;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SwaggerToPDFWithCustomFont {
    final static String swaggerPath = "D:/workspaces/swagger-converter/documents/";
    final static String json = "api-docs.json";

    public static void main(String[] args) {

        //create asciidoc file
        OpenApiToAsciidoc asciidoc = new OpenApiToAsciidoc();
        asciidoc.jsonToAsciidoc(swaggerPath, json);

        // Initialize Asciidoctor instance
        Asciidoctor asciidoctor = JRubyAsciidoctor.create();

        // Set UTF-8 encoding
        Attributes attributes = Attributes.builder()
                .attribute("pdf-fontsdir", swaggerPath + File.separator + "fonts;GEM_FONTS_DIR")
                .attribute("pdf-theme", swaggerPath + File.separator + "themes/custom-theme.yml")
                .attribute("theme", "custom-theme")
                .attribute("Encoding", "UTF-8")
                .attribute("charset", StandardCharsets.UTF_8)
                .build();


        System.out.printf("file.encoding: %s%n", Charset.defaultCharset().displayName());
        System.out.printf("defaultCharset: %s%n", Charset.defaultCharset().name());

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        File pdf = new File(swaggerPath + "/pdf/user-manual-openapi_" + df.format(new Date()) + ".pdf");

        System.out.println("Start generating pdf with Asciidoctor: index.adoc to " + pdf.getAbsolutePath());

        // Convert AsciiDoc to PDF
        asciidoctor.convertFile(new File(swaggerPath + "/asciidoc/index.adoc"),
                Options.builder().mkDirs(true)
                        .backend("pdf")
                        .toFile(pdf)
                        .safe(SafeMode.SAFE) //allowed to generate pdf inside this directory only.
                        .attributes(attributes)
                        .build());

        System.out.println("PDF is created!");
    }
}
