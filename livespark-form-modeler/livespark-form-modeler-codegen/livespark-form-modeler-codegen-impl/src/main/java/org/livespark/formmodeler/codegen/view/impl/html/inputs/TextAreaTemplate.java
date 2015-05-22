package org.livespark.formmodeler.codegen.view.impl.html.inputs;

import java.io.InputStream;

import org.livespark.formmodeler.codegen.view.impl.html.InputTemplateProvider;
import org.livespark.formmodeler.model.impl.TextAreaFieldDefinition;

/**
 * Created by pefernan on 4/29/15.
 */
public class TextAreaTemplate implements InputTemplateProvider {

    @Override
    public String getSupportedFieldType() {
        return TextAreaFieldDefinition.class.getName();
    }

    @Override
    public InputStream getTemplateInputStream() {
        return getClass().getResourceAsStream( "/org/livespark/formmodeler/codegen/view/impl/html/templates/textarea.mv" );
    }
}