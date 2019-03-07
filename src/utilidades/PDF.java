package utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.joda.time.DateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Aluno;

public class PDF {
	
	public void gerarComprovanteCadastro(Aluno a) {
		String nomeArquivo = "ComprovanteCadastro"+a.getId()+".pdf";
		
		Document d = new Document();
		
		try {
			Font f = new Font();

			PdfWriter.getInstance(d, new FileOutputStream(nomeArquivo));
			d.open();

			f.setSize(10);

			Paragraph paragrafo = new Paragraph("MEIA-PASSAGEM ESCOLAR - VALE ESTUDANTE\nCOMPROVANTE DE CADASTRO - "
					+ new DateTime().getYear() + "\n\n");
			paragrafo.setAlignment(1);
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
			paragrafo.add("Id: " + a.getStringId() + "         RG: " + a.getRg() + "         Idade: " + a.getIdade()
					+ " anos         Limite Mensal: 50 vales\n");
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.add("Nome: " + a.getNome() + " " + "                                Bairro: " + a.getBairro()
					+ "\n" + "Escola: " + a.getEscola().getNome() + "  na localidade " + a.getEscola().getBairro());
			d.add(paragrafo);

			paragrafo = new Paragraph();

			paragrafo.setAlignment(Element.ALIGN_CENTER);
			paragrafo.add("TERMO DE UTILIZA��O");
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.setAlignment(Element.ALIGN_JUSTIFIED);
			paragrafo.add("- Estudantes que n�o ter�o direito ao benef�cio da Meia Passagem Escolar - Vale Estudante:\n"
					+ "I. Estudantes de p�s-gradua��o, pr�-vestibulares e cursos n�o regulamentados pelo MEC;\n"
					+ "II. Estudantes que j� gozem da gratuidade do Sistema de Transporte Ferrovi�rio;\n"
					+ "II. Estudantes menores que 06 (seis) anos;\n"
					+ "Situa��es que implicam na suspens�o do benef�cio:\n"
					+ "I. Uso por terceiros, inclusive os seus acompanhantes; II. Uso por estudantes n�o cadastrados; III. Comercializa��o.\n"
					+ "Os benefici�rios do sistema de Meia Passagem Escolar - Vale Estudante dever�o apresentar o Cart�o de identifica��o, fornecido no ato do cadastramento, no momento em que for utilizar o seu benef�cio.");
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			paragrafo.add(
					"- PERDA DO CART�O - No caso de perda ou roubo do cart�o de identifica��o, solicitamos o Registro em Delegacia e posterior apresenta��o do Registro de Ocorr�ncia");
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
			DateTime hoje = new DateTime();
			paragrafo.add(
					"- Aceito os termos de utiliza��o da Meia Passagem Escolar - Vale Estudante, inclusive os itens que implicam na suspens�o do benef�cio e atesto o recebimento do Cart�o de Meia Passagem Escolar.\n"
							+ "Data: " + hoje.getDayOfMonth() + "/" + hoje.getMonthOfYear() + "/" + hoje.getYear() + " "
							+ hoje.getHourOfDay() + ":" + hoje.getMinuteOfHour() + ":" + hoje.getSecondOfMinute() + ""
							+ "     Assinatura:_________________________________________________________\n");
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			paragrafo.add("          ______________________________________________________________\n"
					+ "                        Respons�vel pelo cadastro\n\n");
			d.add(paragrafo);
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} finally {
			d.close();
		}

		try {
			Desktop.getDesktop().open(new File(nomeArquivo));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
