package documento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class GeradorDeDocumento {

	private static final String ARQUIVO_TEMPLATE="C:/Eclipse projects kepler/Centralizacao_Execucao/src/template/Template CERTIDAO simplificado.docx";
	 
	 public void preencheTemplate(String arquivoDeSaida,Map<String, String> propriedade) throws Exception{
		 FileInputStream in = new FileInputStream(ARQUIVO_TEMPLATE);
		 GeradorDeDocumento cd=new GeradorDeDocumento();
		 
		 XWPFDocument document = new XWPFDocument(in);	 
		 cd.substituiParagrafos(document.getParagraphs(),propriedade); 
		 FileOutputStream out = new FileOutputStream(new File(arquivoDeSaida));
		 document.write(out);
		 
		 in.close();
		 out.close();
		 System.out.print("PRONTO!");
	 }

	 private void substituiParagrafos(List<XWPFParagraph> paragraphs,Map<String, String> properties) {
		 for (XWPFParagraph paragraph : paragraphs) {
			 List<XWPFRun> runs = paragraph.getRuns();

			 for (XWPFRun run : runs) {
				 String textRun = run.getText(run.getTextPosition());
				 for (Entry<String, String> entry : properties.entrySet()) {
					 if (textRun != null && textRun.contains(entry.getKey())) {
						 String newText = textRun.replace(entry.getKey(), String.valueOf(properties.get(entry.getKey())));
						 run.setText(newText, 0);
						 break;
					 }
				 }

			 }
		 }
	 }
}