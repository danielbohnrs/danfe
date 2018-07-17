# danfe

Exemplo de uso:

String xmlNota ="<nfeProc...";

NFDanfeReport danfe = new NFDanfeReport(xmlNota);

byte[] logo = null;

JasperPrint jasper = danfe.createJasperPrintNFe(logo);

byte[] byteArray = JasperExportManager.exportReportToPdf(jasper);
