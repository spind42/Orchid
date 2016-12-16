package com.eden.orchid.compiler.impl;

import com.eden.orchid.compiler.AssetCompiler;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

public class CompilePages implements AssetCompiler {

    @Override
    public String getKey() {
        return "pages";
    }

    @Override
    public String[] getSourceExtensions() {
        return new String[]{"md", "txt"};
    }

    @Override
    public String getDestExtension() {
        return "html";
    }

    @Override
    public String getSourceDir() {
        return "assets/pages";
    }

    @Override
    public String getDestDir() {
        return "pages";
    }

    @Override
    public String compile(String extension, String input) {
        if(extension.equals("md")) {
            Node document = Parser.builder().build().parse(input);
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            return renderer.render(document);
        }
        else {
            return input;
        }
    }
}