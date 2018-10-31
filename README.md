# danfe

Exemplo de uso:
```java
String xmlNota ="<nfeProc...";
NFDanfeReport danfe = new NFDanfeReport(xmlNota);
byte[] logo = null;
if (modelo.equals("65"))
	JasperPrint jasper = danfe.createJasperPrintNFCe(
    			logo,	
    			nota.getNota().getInfo().getIdentificacao().getAmbiente()==DFAmbiente.HOMOLOGACAO ? nota.getNota().getInfo().getIdentificacao().getUf().getQrCodeHomologacao() :
    			nota.getNota().getInfo().getIdentificacao().getUf().getQrCodeProducao(),
    			nota.getNota().getInfoSuplementar().getQrCode(),    				
    				
    				nfe.getChave(), 
    				nota.getNota().getInfo().getIdentificacao().getAmbiente()==DFAmbiente.HOMOLOGACAO,
    				"",
    				false,
    				pgtos);
else
	JasperPrint jasper = danfe.createJasperPrintNFe(logo);
byte[] byteArray = JasperExportManager.exportReportToPdf(jasper);
```
