/* 
 * Classe usada para calcular a dist�ncia entre data
 * usada para saber quantos dias faltam para o vencimento do contrato 
 * funciona tanto com uma data espec�fica no momento da chamada do m�todo quanto passando uma data na instacia��o do objeto
 */

package utilidades;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CalcularData {
	private DateTime hoje = new DateTime(),
			 outra = hoje;
	
	public CalcularData(DateTime proxima) {
		//usada para setar a pr�xima data para refer�ncia 
		this.outra = proxima;
	}
	
	//retorna a quantidade de dias entre a data inserida e a data de hoje
	public int diasEntre(DateTime data) {
		//+ 1 pois o getDays vai at� um dia antes ao dia escolhido 
		return Days.daysBetween(hoje, data).getDays() + 1;
	}
	
	public int diasEntre() {
		return Days.daysBetween(hoje, outra).getDays() + 1;
	}
	
	public int anosEntre() {
		return Years.yearsBetween(outra, hoje).getYears();
	}
}
