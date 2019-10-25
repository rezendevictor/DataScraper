import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class scraper {

	public static void main(String[] args) throws IOException {
		//Variaveis usadas
		Scanner in = new Scanner(System.in);
		ArrayList<String> nomes = new ArrayList<String>();
		FileWriter file = new FileWriter("mercadolivre.csv");
		
		System.out.println("Insira a url desejada:");
		final String url = in.next();
		
		
		///final String url = "https://celulares.mercadolivre.com.br/acessorios/";
		
		try {
			//Pegar os dados
			final Document doc = Jsoup.connect(url).get();
			for(Element row : doc.select("section.results.stack")) {
				for(Element title : doc.select("span.main-title")) {
					nomes.add(title.select("span.main-title").text());
				}
				
				String preco = row.select("div.price__container").text().replace("R$","\nRS");
				String[] prices = preco.split("\n");
				
				int i = 1;
				
				//imprimir na tela os dados obtidos
				System.out.println("NOME PRODUTO                                                                |       PRECO     E  DESCONTO");
				for(String produto : nomes) {
					file.write("Produto ;Preco \n "); 
					String output = String.format("%s", produto, 180);
					System.out.print(output);
					System.out.print("     | ");
					System.out.println(prices[i]);
					file.write(produto +";");
					file.write(prices[i]+'\n');
					
					
					i++;
					
				}
				
				//Geracao de um arquivo csv para manipulacao no excel
				System.out.println("Foi gerado um arquivo .csv!");
				file.close();	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
