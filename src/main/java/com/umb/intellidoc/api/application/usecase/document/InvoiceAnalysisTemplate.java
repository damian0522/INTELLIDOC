package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.domain.model.AnalysisField;
import com.umb.intellidoc.api.domain.model.AnalysisTemplate;
import com.umb.intellidoc.api.domain.model.DocumentType;

import java.util.List;

public final class InvoiceAnalysisTemplate {

    private InvoiceAnalysisTemplate() {
    }

    public static AnalysisTemplate create() {

        return new AnalysisTemplate(
                "FACTURA_TRIBUTARIA",
                DocumentType.INVOICE,
                "Extraer información comercial y tributaria de una factura.",
                List.of(

                        new AnalysisField(
                                "invoiceNumber",
                                "Número o identificador de la factura",
                                "STRING",
                                true,
                                null
                        ),

                        new AnalysisField(
                                "issueDate",
                                "Fecha de emisión de la factura",
                                "DATE",
                                true,
                                null
                        ),

                        new AnalysisField(
                                "supplier",
                                "Nombre o razón social del proveedor",
                                "STRING",
                                true,
                                null
                        ),

                        new AnalysisField(
                                "supplierNit",
                                "NIT o identificación tributaria del proveedor",
                                "STRING",
                                false,
                                "\\d{9}-\\d"
                        ),

                        new AnalysisField(
                                "subtotal",
                                "Valor subtotal antes de impuestos",
                                "NUMBER",
                                false,
                                null
                        ),

                        new AnalysisField(
                                "tax",
                                "Valor total de impuestos",
                                "NUMBER",
                                false,
                                null
                        ),

                        new AnalysisField(
                                "total",
                                "Valor total final de la factura",
                                "NUMBER",
                                true,
                                null
                        )
                )
        );
    }
}